package com.example.app_mobile_lena;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private DotsIndicator dotsIndicator;
    private ImageAdapter imageAdapter;
    private int defaultQuantity = 1;
    private ArrayList<Object> cart;
    private String name;

    private String key;
    private String category;
    private String image;
    private Double price;
    private Double sale_price;
    private long quantity;
    private String description;
    private ArrayList<String> slider = new ArrayList<>();
    private Double rate;
    private User user = null;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        Button btnShowDescription = findViewById(R.id.btnShowDescription);
        Button btnShowReview = findViewById(R.id.btnShowReviews);
        ImageButton btnMinus = findViewById(R.id.btnIncrement);
        ImageButton btnAdd = findViewById(R.id.btnDecrement);
        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnGoToCart = findViewById(R.id.btnGoToCart);
        ImageButton btnAddToCart = findViewById(R.id.btnAddToCart);
        TextView tvQuantity = findViewById(R.id.tvQuantity);
        TextView txtName = findViewById(R.id.txtName);
        TextView txtPrice = findViewById(R.id.tvProductPrice);

        Intent intent = getIntent();
        Item item = (Item) intent.getSerializableExtra("item");
        SharedPreferences userPref = getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
        String userObject = userPref.getString("userObject","");
        Gson gson = new Gson();
        if(!userObject.isEmpty()){
            user = gson.fromJson(userObject,User.class);
            Log.d("I AM LOGGIN AS ", user.toString());
        }

        if (item != null) {
            key = item.getKey();
            name = item.getName();
            category = item.getCategory();
            image = item.getImage();
            price = item.getPrice();
            sale_price = item.getSale_price();
            description = item.getDescription();
            slider.addAll(item.getSlider());
            quantity = item.getQuantity();
            rate = item.getRate();
            Log.d("TAG","SLIDER: " + slider.get(0));
            Log.d("TAG",description);
            txtName.setText(item.getName());
            txtPrice.setText(addThousandSeparator(item.getPrice())+" VND");
        }

        viewPager = findViewById(R.id.view_pager);
        imageAdapter = new ImageAdapter(this,slider);
        viewPager.setAdapter(imageAdapter);
        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
        dotsIndicator.attachTo(viewPager);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(rate.floatValue());

        initDescriptionFragment();

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CartActivity.class);
                startActivity(intent);
            }
        });

         btnBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
             }
         });
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

                fragmentTransaction.replace(R.id.flFragment, new ProductDescription_Fragment());
                fragmentTransaction.commit();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvQuantity.setText(String.valueOf(increaseQuantity()));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvQuantity.setText(String.valueOf(decreaseQuantity()));
            }
        });
        User finalUser = user;
        btnAddToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("I AM IN IF STATEMENT", "IN IF STATEMENT");
                long addedQuantity = Long.parseLong(tvQuantity.getText().toString());
                ArrayList<CartItems> currentUserCart = (ArrayList<CartItems>) user.getCart().clone();
                boolean ITEM_EXISTED = false;
                if(item.getQuantity() > addedQuantity){
                    Log.d("SET BACK QUANTITY", currentUserCart.toString());

                    Log.d("I AM IN IF STATEMENT", "IN IF STATEMENT");
                    // Duyet qua tung item trong cart
                    for(CartItems cartItems : currentUserCart){
                            if(cartItems.getProductId() != null){
                                Log.d("SET BACK QUANTITY", cartItems.toString());

                                // Neu co item them vao trung voi item trong cart
                                if(cartItems.getProductId().equals(item.getKey())){
                                    Log.d("SET BACK QUANTITY", cartItems.toString());

                                    // Item ton tai
                                    ITEM_EXISTED = true;
                                    // Lay so luong cua item trong cart hien tai
                                    long itemQuantityInCart = cartItems.getQuantity();
                                    // set so luong item trong cart = sl hien tai + sl them vao
                                    cartItems.setQuantity(itemQuantityInCart+addedQuantity);
                                    Log.d("SET BACK QUANTITY", cartItems.toString());

                                }
                            }
                    }
                    ArrayList<CartItems> updatedCart = (ArrayList<CartItems>)currentUserCart.clone();

                    if(ITEM_EXISTED == false) {
                        CartItems addedItem = new CartItems(item.getKey(),item.getName(),item.getCategory(), item.getImage(),addedQuantity,item.getPrice());
                        updatedCart.add(addedItem);
                    }
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("cart", updatedCart);
                    user.setCart(updatedCart);
                    Log.d("DONT PUT String", "IN IF STATEMENT");
                    db.collection("users").document(user.getID())
                            .set(docData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "DocumentSnapshot successfully written!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error writing document", e);
                                }
                            });

                }
            }
        });

    }
    private List<ProductImage> getxListPhoto() {
        List<ProductImage> list = new ArrayList<>();
        list.add(new ProductImage(R.drawable.image_1));
        list.add(new ProductImage(R.drawable.image_2));
        list.add(new ProductImage(R.drawable.image_3));
        list.add(new ProductImage(R.drawable.image_4));

        return list;
    }

    public String addThousandSeparator(Double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    private int increaseQuantity(){
        defaultQuantity++;
        return defaultQuantity;

    }
    private int decreaseQuantity(){
        if(defaultQuantity == 0){
            // Do nothing
        }
        else{
            defaultQuantity--;
        }
        return defaultQuantity;

    }
    private void initDescriptionFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, new ProductDescription_Fragment(description));
        fragmentTransaction.commit();
    }



}