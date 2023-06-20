package com.example.app_mobile_lena.Payment_section;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_mobile_lena.R;
import com.example.app_mobile_lena.navbar_fragment.home_frament;

public class finishPayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_payment);

        Button btnHome = findViewById(R.id.btnHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finishPayment.this, home_frament.class);
                startActivity(intent);
            }
        });
    }
}