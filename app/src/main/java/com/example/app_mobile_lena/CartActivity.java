package com.example.app_mobile_lena;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        Button btnGoToPayment = findViewById(R.id.btnPay);
        LinearLayout ll = findViewById(R.id.llCoupon);
        ImageButton btnBack = findViewById(R.id.btnGoBack);
        ListView listView = findViewById(R.id.itemList);
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> cate = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<String> img = new ArrayList<>();
        name.add("chó");
        name.add("gấu");
        name.add("mèo");
        cate.add("thú bông");
        cate.add("thú bông");
        cate.add("thú bông");
        price.add(150000.0);
        price.add(230000.0);
        price.add(250000.0);
        img.add("1123");
        img.add("12312312");
        img.add("123123");

        cartItemAdapter adapter = new cartItemAdapter(this,name, img, cate, price);
        listView.setAdapter(adapter);
        justifyListViewHeightBasedOnChildren(listView);

        btnGoToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),PaymentActivity.class);
                startActivity(intent);
            }
        });

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),CouponActivity.class);
                startActivity(intent);

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
