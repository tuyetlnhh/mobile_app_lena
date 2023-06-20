package com.example.app_mobile_lena;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.app_mobile_lena.model.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public OrderRepository() {

    }

    public void add(Order order) {
        Map<String, Object> docData = new HashMap<>();
        // Set lại cart mới cho user
        // Thêm lại user vào db cùng với cart mới
        docData.put("cart", order.getCartItems());
        docData.put("email", order.getEmail());
        docData.put("total", order.getTotal());
        docData.put("phone", order.getPhone());
        docData.put("address", order.getAddress());
        docData.put("status",order.getStatus());

        Log.d("DONT PUT String", "IN IF STATEMENT");
        db.collection("orders").document(order.getId())
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

    }

    public ArrayList<Order> getOrderByUser(String userEmail) {
        ArrayList<Order> carts = new ArrayList<>();

        db.collection("orders")
                .whereEqualTo("email", userEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
        return carts;
    }
}
