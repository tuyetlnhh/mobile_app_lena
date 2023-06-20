package com.example.app_mobile_lena.Account_section;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.app_mobile_lena.R;
import com.example.app_mobile_lena.navbar_fragment.home_frament;

public class orderDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        ImageButton btnBack = findViewById(R.id.btnBack);
        Button btnBackHome = findViewById(R.id.btnBackToHome);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(orderDetail.this, home_frament.class);
                startActivity(intent);
            }
        });
    }
}