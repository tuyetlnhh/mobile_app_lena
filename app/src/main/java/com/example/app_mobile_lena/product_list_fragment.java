package com.example.app_mobile_lena;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.window.SplashScreen;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link product_list_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class product_list_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GridView gview;


    public product_list_fragment() {
        // Required empty public constructor
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }

    private OnButtonClickListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonClickListener) {
            mListener = (OnButtonClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnButtonClickListener");
        }
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment product_list.
     */
    // TODO: Rename and change types and number of parameters
    public static product_list_fragment newInstance(String param1, String param2) {
        product_list_fragment fragment = new product_list_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        gview = view.findViewById(R.id.grid_view);
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                startActivity(intent);
            }
        });
        String[] name = {"chó","mèo","cáo","chồn","Hổ"};
        Context context = getContext();
        int[] img = {R.drawable.image_1,R.drawable.image_2,R.drawable.image_3,R.drawable.image_4,R.drawable.image_4};
        int[] price_sale = {150000, 200000, 120000, 60000, 430000};
        int[] price = {150000, 200000, 120000, 60000, 430000};
        GridAdapter grid = new GridAdapter(context, name, img, price_sale, price);
        gview.setAdapter(grid);
        ImageButton backButton = view.findViewById(R.id.backButton);
        ImageButton btnCart = view.findViewById(R.id.btnCart);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClick();
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}