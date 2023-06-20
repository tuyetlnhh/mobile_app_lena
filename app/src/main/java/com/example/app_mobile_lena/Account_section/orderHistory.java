package com.example.app_mobile_lena.Account_section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.app_mobile_lena.Cart_section.CartItems;
import com.example.app_mobile_lena.MyCallback;
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

public class orderHistory extends AppCompatActivity implements MyCallback {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private User user = null;
    private ArrayList<Order> orderList = new ArrayList<>();

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
                                ArrayList<CartItems> cart =  (ArrayList<CartItems>) document.get("cart");
                                order.setCartItems(cart);
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
        ArrayList<CartItems> cartItems = new ArrayList<>();
        ArrayList<String> total = new ArrayList<>();
        readData(new MyCallback() {
            @Override
            public void orderCallback(ArrayList<Order> eventList) {
                Gson gson = new Gson();

                Log.d("TAG", "evetList size: " + Integer.toString(eventList.size()));
                orderList = (ArrayList<Order>) eventList.clone();
                Log.d("TAG", "Order list: " + eventList.toString());
                for(Order i:orderList){
                    id.add(i.getId());
                    status.add(i.getStatus());
                    total.add(i.getTotal());
                    Log.d("TAG",orderList.toString());
//                    cartItems.add(i.getCartItems().get(0));

                }

                HistoryAdapter adapter = new HistoryAdapter(orderHistory.this,id, status,cartItems,total);
                itemList.setAdapter(adapter);
            }
        });

//        HistoryAdapter adapter = new HistoryAdapter(orderHistory.this,id, status, name, img,cate, price);
//        itemList.setAdapter(adapter);
//        justifyListViewHeightBasedOnChildren(itemList);
    }

}