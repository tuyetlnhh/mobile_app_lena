package com.example.app_mobile_lena;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import androidx.fragment.app.Fragment;

import androidx.viewpager2.widget.ViewPager2;

import android.content.res.ColorStateList;
import android.media.Image;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;
import java.util.List;


import com.google.android.material.tabs.TabLayout;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_detail);

        Button btnShowDescription = findViewById(R.id.btnShowDescription);
        Button btnShowReview = findViewById(R.id.btnShowReviews);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(5f);

        setContentView(R.layout.activity_main);

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home_tab, R.drawable.home, R.color.white);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.category_tab, R.drawable.list, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.qr_tab, R.drawable.qr, R.color.white);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.liked_tab, R.drawable.heart, R.color.white);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.profile_tab, R.drawable.user, R.color.white);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new home_frament());
        fragments.add(new category_fragment());
        fragments.add(new qr_fragment());
        fragments.add(new favourite_fragment());
        fragments.add(new account_fragment());
        ViewPager2 viewPager = findViewById(R.id.view);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {

            viewPager.setCurrentItem(position);
            return true;
        });



    }
}