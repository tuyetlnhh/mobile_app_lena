package com.example.app_mobile_lena.Cart_section;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_mobile_lena.Payment_section.PaymentActivity;
import com.example.app_mobile_lena.R;
import com.example.app_mobile_lena.model.User;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {


    public static TextView txtTotal;
    public static cartItemAdapter adapter;

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

        Gson gson = new Gson();
        SharedPreferences userPref = getSharedPreferences("CURRENT_USER",MODE_PRIVATE);
        User user = gson.fromJson(userPref.getString("userObject",null),User.class);
        ArrayList<CartItems> cart = (ArrayList<CartItems>) user.getCart().clone();

        Button btnGoToPayment = findViewById(R.id.btnPay);
        LinearLayout ll = findViewById(R.id.llCoupon);
        ImageButton btnBack = findViewById(R.id.btnGoBack);
        ListView listView = findViewById(R.id.itemList);
        txtTotal = findViewById(R.id.textTotalOrderPrice2);
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> cate = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<String> img = new ArrayList<>();
        ArrayList<Long> quantity = new ArrayList<>();

        for(CartItems item : cart){
            name.add(item.getName());
            cate.add(item.getCategory());
            price.add(item.getPrice());
            img.add(item.getImg());
            quantity.add(item.getQuantity());
        }

        adapter = new cartItemAdapter(this,name, img, cate, price,quantity);
        listView.setAdapter(adapter);
        justifyListViewHeightBasedOnChildren(listView);
        txtTotal.setText(addThousandSeparator(Double.valueOf(getTotal(cart))));
        btnGoToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CouponActivity.class);
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

    private double getTotal(ArrayList<CartItems> cart){
        double total = 0;
        for(CartItems item : cart){
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public String addThousandSeparator(Double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number) + " VND";
    }
}
