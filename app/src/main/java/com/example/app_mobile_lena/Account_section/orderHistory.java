package com.example.app_mobile_lena.Account_section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.app_mobile_lena.Cart_section.CartItems;
import com.example.app_mobile_lena.MyCallback;
import com.example.app_mobile_lena.ProductDetail_section.ProductDetailActivity;
import com.example.app_mobile_lena.R;
import com.example.app_mobile_lena.adapter.GridAdapter;
import com.example.app_mobile_lena.adapter.HistoryAdapter;
import com.example.app_mobile_lena.model.Item;
import com.example.app_mobile_lena.model.Order;
import com.example.app_mobile_lena.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class orderHistory extends AppCompatActivity implements MyCallback {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private User user = null;
    private ArrayList<Order> orderList = new ArrayList<>();
    private ArrayList<CartItems> cartItems = new ArrayList<>();

    public static void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    void readData(MyCallback callback){
        db.collection("orders")
                .whereEqualTo("email", user.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<Order> orders = new ArrayList<>() ;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                Order order = new Order();
                                order.setAddress(document.getData().get("address").toString());
                                order.setEmail(document.getData().get("email").toString());
                                order.setPhone(document.getData().get("phone").toString());
                                order.setTotal(document.getData().get("total").toString());
                                order.setStatus(document.getData().get("status").toString());
                                order.setId(document.getId());

                                ArrayList<CartItems> resultCart = new ArrayList<>();
                                ArrayList<HashMap<String, Object>> cartDataList = (ArrayList<HashMap<String, Object>>) document.getData().get("cart");

                                if (cartDataList != null) {
                                    for (HashMap<String, Object> cartItemData : cartDataList) {
                                        CartItems cartItem = new CartItems();
                                        cartItem.setName(cartItemData.get("name").toString());
                                        cartItem.setPrice(Double.parseDouble(cartItemData.get("price").toString()));
                                        cartItem.setCategory(cartItemData.get("category").toString());
                                        cartItem.setImg(cartItemData.get("img").toString());
                                        cartItem.setProductId(cartItemData.get("productId").toString());
                                        cartItem.setQuantity(Long.valueOf(cartItemData.get("quantity").toString()));
                                        // Thiết lập các thuộc tính khác của cartItem

                                        resultCart.add(cartItem);
                                    }
                                }
                                order.setCartItems(resultCart);
                                Log.d("TAG", order.toString());
                                orders.add(order);
                            }
                            callback.orderCallback(orders);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        SharedPreferences userPref = getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();
        String userObject = userPref.getString("userObject","");
        Gson gson = new Gson();
        if(!userObject.isEmpty()){
            user = gson.fromJson(userObject, User.class);
            Log.d("I AM LOGGIN AS ", user.toString());
        }

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ListView itemList = findViewById(R.id.itemList);
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> img = new ArrayList<>();
        ArrayList<String> cate = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> status = new ArrayList<>();

        ArrayList<String> total = new ArrayList<>();
        readData(new MyCallback() {
            @Override
            public void orderCallback(ArrayList<Order> eventList) {

                Log.d("TAG", "evetList size: " + Integer.toString(eventList.size()));
                orderList.addAll(eventList);
                Log.d("TAG", "Order list: " + eventList.toString());
                for(Order i:orderList){
                    id.add(i.getId());
                    status.add(i.getStatus());
                    total.add(i.getTotal());
                    cartItems.add(i.getCartItems().get(0));
                    Log.d("TAG", "Cart item length: " + Integer.toString(cartItems.size()));

                }

                HistoryAdapter adapter = new HistoryAdapter(orderHistory.this,id, status,cartItems,total);
                itemList.setAdapter(adapter);
                justifyListViewHeightBasedOnChildren(itemList);
            }
        });
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(orderHistory.this, orderDetail.class);
                intent.putExtra("order", orderList.get(position));
                startActivity(intent);
            }
        });

    }

}