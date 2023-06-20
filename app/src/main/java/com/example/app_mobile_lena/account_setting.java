package com.example.app_mobile_lena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class account_setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        ImageButton btnBack = findViewById(R.id.btBack);
        Button btnLogOut = (Button) findViewById(R.id.btnLogout);
        Button btnEditAcc = findViewById(R.id.btnEditAcc);
        Button btnChangePass = findViewById(R.id.btnChangePass);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), pre_login.class);
                startActivity(intent);
                SharedPreferences sharedPref = getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("userObject",null);
                editor.commit();
                finish();
            }
        });
    }
}