package com.example.app_mobile_lena;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.app_mobile_lena.Category_section.wool_product_fragment;
import com.example.app_mobile_lena.Category_section.wool_tool_fragment;
import com.example.app_mobile_lena.adapter.MyAdapter;
import com.example.app_mobile_lena.model.User;
import com.example.app_mobile_lena.navbar_fragment.account_fragment;
import com.example.app_mobile_lena.navbar_fragment.category_fragment;
import com.example.app_mobile_lena.navbar_fragment.favourite_fragment;
import com.example.app_mobile_lena.navbar_fragment.home_frament;
import com.example.app_mobile_lena.navbar_fragment.product_list_fragment;
import com.example.app_mobile_lena.navbar_fragment.qr_fragment;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

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


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Fragment[] mainView = {homeFragment, cateFrag, qrFrag, favFrag, accFrag};
    private final Fragment[] stackFragment = {};
    @Override
    public void onListItemClick(int position, String titles, String category) {
//         Change the fragment
        List<Fragment> fragments = new ArrayList<>(Arrays.asList(mainView));
        product_list_fragment listFrag = new product_list_fragment(titles, category);
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences userPref = getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        // khai bao bien islogin de check user loggin hay chua
        boolean Islogin = prefs.getBoolean("Islogin", false);
        String userObject = userPref.getString("userObject","");
        Gson gson = new Gson();
        User user = null;

        if(!userObject.isEmpty()){
            Islogin = true;
            user = gson.fromJson(userObject,User.class);
            editor.putBoolean("Islogin",Islogin).commit();

            Log.d("I AM LOGGIN AS ", user.toString());

        }
        Log.d("I AM LOGGIN AS ", String.valueOf(Islogin));



            // and get whatever type user account id is
        // Create a new user with a first and last name

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
