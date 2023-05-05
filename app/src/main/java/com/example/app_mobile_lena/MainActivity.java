package com.example.app_mobile_lena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        RatingBar ratingbar = findViewById(R.id.ratingBar);
        ratingbar.setRating(3.67f);
    }
}