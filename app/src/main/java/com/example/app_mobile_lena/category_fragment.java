package com.example.app_mobile_lena;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link category_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class category_fragment extends Fragment   {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    categoryAdapter adapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    private String[] titles = new String[] {"Sản Phẩm Từ Len", "Dụng Cụ Len"};
    public category_fragment() {
        // Required empty public constructor
    }
    protected void finalize() throws Throwable {
        try {
            // perform some cleanup operations or any other necessary tasks here
        } finally {
            super.finalize();
        }
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment category_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static category_fragment newInstance(String param1, String param2) {
        category_fragment fragment = new category_fragment();
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
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        viewPager2 = view.findViewById(R.id.view_pager_category);
        tabLayout = view.findViewById(R.id.tab_layout);
        Context context = getContext();
        adapter = new categoryAdapter((FragmentActivity) context);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2,((tab, position)->tab.setText(titles[position]))).attach();


        return view;
    }


}