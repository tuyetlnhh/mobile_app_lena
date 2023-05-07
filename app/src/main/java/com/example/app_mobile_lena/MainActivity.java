package com.example.app_mobile_lena;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.ColorStateList;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.google.android.material.tabs.TabLayout;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private DotsIndicator dotsIndicator;
    private ImageAdapter imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        Button btnShowDescription = findViewById(R.id.btnShowDescription);
        Button btnShowReview = findViewById(R.id.btnShowReviews);
        viewPager = findViewById(R.id.view_pager);
        imageAdapter = new ImageAdapter(this,getListPhoto());
        viewPager.setAdapter(imageAdapter);
        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
        dotsIndicator.attachTo(viewPager);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(5f);

        btnShowReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnShowDescription.setBackgroundTintList(ColorStateList.valueOf(R.drawable.custon_button_rounded_selected_state));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flFragment, new ProductReviews_Fragment());
                fragmentTransaction.commit();
            }
        });
        btnShowDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnShowReview.setBackgroundTintList(ColorStateList.valueOf(R.drawable.custom_button_rouded));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flFragment, new ProductReviews_Fragment());
                fragmentTransaction.commit();
            }
        });


    }
    private List<ProductImage> getListPhoto(){
        List<ProductImage> list = new ArrayList<>();
        list.add(new ProductImage(R.drawable.image_1));
        list.add(new ProductImage(R.drawable.image_2));
        list.add(new ProductImage(R.drawable.image_3));
        list.add(new ProductImage(R.drawable.image_4));

        return list;

    }
}