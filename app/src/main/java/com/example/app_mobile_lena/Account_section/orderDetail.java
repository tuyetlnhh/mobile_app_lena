package com.example.app_mobile_lena.Account_section;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app_mobile_lena.R;
import com.example.app_mobile_lena.adapter.ItemListAdapter;
import com.example.app_mobile_lena.model.Item;
import com.example.app_mobile_lena.model.Order;
import com.example.app_mobile_lena.navbar_fragment.home_frament;

public class orderDetail extends AppCompatActivity {
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
        setContentView(R.layout.order_detail);

        ImageButton btnBack = findViewById(R.id.btnBack);

        ListView itemList = findViewById(R.id.itemList);
        TextView txtOrderID = findViewById(R.id.txtOrderID);
        TextView txtPhone = findViewById(R.id.txtPhone);
        TextView txtAddress = findViewById(R.id.txtAddress);
        TextView txtTotal = findViewById(R.id.txtTotal);
        Intent intent = getIntent();
        Order order = (Order) intent.getSerializableExtra("order");

        txtOrderID.setText(order.getId());
        txtAddress.setText("Địa chỉ: " + order.getAddress());
        txtPhone.setText("Số điện thoại: " + order.getPhone());
        txtTotal.setText(order.getTotal());
        ItemListAdapter adapter = new ItemListAdapter(orderDetail.this, order.getCartItems());
        itemList.setAdapter(adapter);
        justifyListViewHeightBasedOnChildren(itemList);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}