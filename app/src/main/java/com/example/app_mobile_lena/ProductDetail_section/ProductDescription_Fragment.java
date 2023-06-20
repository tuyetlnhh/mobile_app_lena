package com.example.app_mobile_lena.ProductDetail_section;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app_mobile_lena.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDescription_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDescription_Fragment extends Fragment {
    private String description;
    public ProductDescription_Fragment() {
        // Required empty public constructor

    }
    public ProductDescription_Fragment(String description) {
        // Required empty public constructor
        this.description = description;
    }
    public static ProductDescription_Fragment newInstance(String param1, String param2) {
        ProductDescription_Fragment fragment = new ProductDescription_Fragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_description_, container, false);
        Log.d("TAG","Description: " + this.description);
        TextView description = view.findViewById(R.id.tvProductDescription);
        description.setText(this.description);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}