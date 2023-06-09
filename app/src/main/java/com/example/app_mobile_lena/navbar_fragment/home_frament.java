package com.example.app_mobile_lena.navbar_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.example.app_mobile_lena.Cart_section.CartActivity;
import com.example.app_mobile_lena.ProductDetail_section.ProductDetailActivity;
import com.example.app_mobile_lena.R;
import com.example.app_mobile_lena.adapter.SliderAdapter;
import com.example.app_mobile_lena.Account_section.pre_login;
import com.example.app_mobile_lena.model.Item;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_frament#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_frament extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Item> items = new ArrayList<>();

    public home_frament() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home_frament newInstance(String param1, String param2) {
        home_frament fragment = new home_frament();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SliderView sliderView;
        int[] images = { R.drawable.slider0,
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3
            };
        sliderView = view.findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images,getContext());

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
        sliderView.startAutoCycle();
        ImageButton buttonNext = view.findViewById(R.id.btnRight);
        ImageButton buttonPre = view.findViewById(R.id.btnLeft);
        ImageButton btnCart = view.findViewById(R.id.btnCart);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliderView.slideToNextPosition();
            }
        });
        buttonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliderView.slideToPreviousPosition();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getContext().getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
                if(sharedPref.getString("userObject",null) == null){
                    Intent intent = new Intent(getActivity(), pre_login.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), CartActivity.class);
                    startActivity(intent);
                }

            }

        });

        return view;
    }

}