package com.example.app_mobile_lena;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;

public class orderHistory extends AppCompatActivity {
    private User user = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences userPref = getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();
        String userObject = userPref.getString("userObject","");
        Gson gson = new Gson();
        if(!userObject.isEmpty()){
            user = gson.fromJson(userObject,User.class);
            Log.d("I AM LOGGIN AS ", user.toString());
        }
        db.collection("orders")
                .whereEqualTo("mail",user.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<orderHistoryItem> itemList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                orderHistoryItem itemHis =  new orderHistoryItem();
                                Object item;
                                //add item
                                itemHis.setId(document.getData().get("id").toString());
                                item = document.getData().get("items");
                                Log.d("TAG", "Item: " + item.toString());

                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }

                    }
                });
    }
}