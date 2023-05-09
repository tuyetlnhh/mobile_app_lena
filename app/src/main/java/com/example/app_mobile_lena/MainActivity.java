package com.example.app_mobile_lena;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;



import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements wool_product_fragment.OnListItemClickListener, wool_tool_fragment.OnListItemClickListener, product_list_fragment.OnButtonClickListener {
    private ViewPager2 view;
    private home_frament homeFragment= new home_frament();
    private category_fragment cateFrag = new category_fragment();
    private qr_fragment qrFrag = new qr_fragment();
    private favourite_fragment favFrag = new favourite_fragment();
    private account_fragment accFrag = new account_fragment();
    private product_list_fragment listFrag = new product_list_fragment();

    private Fragment[] mainView = {homeFragment, cateFrag, qrFrag, favFrag, accFrag};
    private final Fragment[] stackFragment = {};
    @Override
    public void onListItemClick(int position) {
        // Change the fragment


        List<Fragment> fragments = new ArrayList<>(Arrays.asList(mainView));
        fragments.set(1, listFrag);
        ViewPager2 viewPager = findViewById(R.id.home_view);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

    }
    @Override
    public void onButtonClick() {

        List<Fragment> fragments = new ArrayList<>(Arrays.asList(mainView));
        fragments.set(1, cateFrag);
        ViewPager2 viewPager = findViewById(R.id.home_view);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new home_frament());
//        fragments.add(new category_fragment());
//        fragments.add(new qr_fragment());
//        fragments.add(new favourite_fragment());
//        fragments.add(new account_fragment());
        List<Fragment> fragments = new ArrayList<>(Arrays.asList(mainView));



        ViewPager2 viewPager = findViewById(R.id.home_view);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            viewPager.setCurrentItem(position);
            return true;
        });

    }


}
