package com.example.app_mobile_lena.Cart_section;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.app_mobile_lena.R;
import com.example.app_mobile_lena.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class cartItemAdapter  extends BaseAdapter {
    Context context;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> img = new ArrayList<>();
    ArrayList<String> cate = new ArrayList<>();
    ArrayList<Double> price = new ArrayList<>();
    ArrayList<Long> quantity = new ArrayList<>();

    LayoutInflater inflater;
    public cartItemAdapter(Context context, ArrayList<String> name, ArrayList<String> img, ArrayList<String> cate, ArrayList<Double> price,ArrayList<Long> quantity){
        Log.d("TAG","size = "+Integer.toString(name.size()));

        this.context = context;
        this.name.addAll(name);
        this.img.addAll(img) ;
        this.cate.addAll(cate);
        this.price.addAll(price);
        this.quantity.addAll(quantity);
        Log.d("TAG","this name size =" + Integer.toString(this.name.size()));

    }


    @Override
    public int getCount(){
        return name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String addThousandSeparator(Double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        }


        ImageView imgView = convertView.findViewById(R.id.itemImage);
        TextView txtName = convertView.findViewById(R.id.itemName);
        TextView txtPrice = convertView.findViewById(R.id.itemPrice);
        TextView txtCate = convertView.findViewById(R.id.itemCate);
        TextView txtQuantity = convertView.findViewById(R.id.itemQuantity);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);
        Button btnMinus = convertView.findViewById(R.id.btnMinus);
        Button btnAdd = convertView.findViewById(R.id.btnAdd);
        SharedPreferences userPref = context.getSharedPreferences("CURRENT_USER",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();
        txtName.setText(name.get(position));
        txtPrice.setText(addThousandSeparator(Double.valueOf(price.get(position)))+"VND");
        txtCate.setText(cate.get(position));
        txtQuantity.setText(quantity.get(position).toString());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                User user = gson.fromJson(userPref.getString("userObject",null).toString(),User.class);
                ArrayList<CartItems> clone_cart = (ArrayList<CartItems>) user.getCart().clone();
                long quantity = Integer.parseInt(txtQuantity.getText().toString());
                quantity++;
                clone_cart.get(position).setQuantity(quantity);
                user.setCart(clone_cart);
                Log.d("TAG123",user.getCart().toString());
                updateData(user);
                txtQuantity.setText(Integer.toString((int)user.getCart().get(position).getQuantity()));

                String txtTotal1 = addThousandSeparator(Double.valueOf(getTotal(user.getCart())));
                CartActivity.txtTotal.setText(txtTotal1);

                String userStr = gson.toJson(user);
                editor.putString("userObject", userStr);
                editor.commit();



            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                User user = gson.fromJson(userPref.getString("userObject",null).toString(),User.class);
                ArrayList<CartItems> clone_cart = (ArrayList<CartItems>) user.getCart().clone();
                long quantity = Integer.parseInt(txtQuantity.getText().toString());
                quantity--;
                clone_cart.get(position).setQuantity(quantity);
                user.setCart(clone_cart);
                updateData(user);
                txtQuantity.setText(Integer.toString((int)user.getCart().get(position).getQuantity()));


                String txtTotal1 = addThousandSeparator(Double.valueOf(getTotal(user.getCart())));
                CartActivity.txtTotal.setText(txtTotal1);
                String userStr = gson.toJson(user);

                editor.putString("userObject", userStr);
                editor.commit();
                // Update xong thì cập nhật lại User
                updateUser(user);


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                User user = gson.fromJson(userPref.getString("userObject",null).toString(),User.class);
                ArrayList<CartItems> clone_cart = (ArrayList<CartItems>) user.getCart().clone();
                AlertDialog.Builder adb=new AlertDialog.Builder(context);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        clone_cart.remove(positionToRemove);
                        user.setCart(clone_cart);
                        updateData(user);
                        String userStr = gson.toJson(user);
                        editor.putString("userObject", userStr);
                        editor.commit();
                        // Update xong thì cập nhật lại User
                        updateUser(user);


                        name.remove(positionToRemove);
                        img.remove(positionToRemove);
                        cate.remove(positionToRemove);
                        price.remove(positionToRemove);

                        CartActivity.adapter.notifyDataSetChanged();
                    }});
                adb.show();



            }
        });

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading_icon) // (tùy chọn) hình ảnh hiển thị trước khi tải
                .error(R.drawable.no_image);
        Glide.with(convertView)
                .load(img.get(position))
                .apply(requestOptions)
                .into(imgView);


        return convertView;
    }

    private double getTotal(ArrayList<CartItems> user_cart){
        double total = 0;
        for(CartItems item: user_cart){
            total += item.getPrice()*item.getQuantity();
        }
        return total;
    }

//    private void setTotal(){
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View otherLayout = inflater.inflate(R.layout.cart, null);
//        TextView txtTotal = otherLayout.findViewById(R.id.textTotalOrderPrice2);
//        txtTotal.setText(getTotal());
//    }
    private User updateUser(User user){
        SharedPreferences userPref = context.getSharedPreferences("CURRENT_USER",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();
        Gson gson = new Gson();
        user = gson.fromJson(userPref.getString("userObject",null).toString(),User.class);

        return user;
    }

    private void updateData(User user){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> docData = new HashMap<>();
        // Set lại cart mới cho user
        // Thêm lại user vào db cùng với cart mới
        docData.put("cart", user.getCart());
        docData.put("email", user.getEmail());
        docData.put("password", user.getPassword());
        db.collection("users").document(user.getID())
                .set(docData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });

        Gson gson = new Gson();
        SharedPreferences userPref = context.getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);

    }
}
