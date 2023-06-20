package com.example.app_mobile_lena.navbar_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.app_mobile_lena.Account_section.orderDetail;
import com.example.app_mobile_lena.Account_section.orderHistory;
import com.example.app_mobile_lena.Cart_section.CartActivity;
import com.example.app_mobile_lena.R;
import com.example.app_mobile_lena.Account_section.pre_login;
import com.example.app_mobile_lena.adapter.HistoryAdapter;
import com.example.app_mobile_lena.adapter.WishlistAdapter;
import com.example.app_mobile_lena.model.User;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favourite_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favourite_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private  ListView itemList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public favourite_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favourite_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static favourite_fragment newInstance(String param1, String param2) {
        favourite_fragment fragment = new favourite_fragment();
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
    public static void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences userPref = getContext().getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();
        String userObject = userPref.getString("userObject","");
        Gson gson = new Gson();
        User user = null;
        if(!userObject.isEmpty()){
            user = gson.fromJson(userObject,User.class);
            Log.d("I AM LOGGIN AS ", user.toString());
            WishlistAdapter adapter = new WishlistAdapter(getContext(),user.getWishlist());
            itemList.setAdapter(adapter);

        }else if(userObject.isEmpty()){
            Intent intent = new Intent(getContext(), pre_login.class);
            startActivity(intent);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        ImageButton btnCart = view.findViewById(R.id.btnCart);
        itemList = view.findViewById(R.id.list_view);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getContext().getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
                if(sharedPref.getString("userObject",null) == null){
                    Intent intent = new Intent(getActivity(), pre_login.class);
                    startActivity(intent);
                    justifyListViewHeightBasedOnChildren (itemList);
                }
                else{
                    Intent intent = new Intent(getActivity(), CartActivity.class);
                    startActivity(intent);
                }

            }


        });
        SharedPreferences userPref = getContext().getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();
        String userObject = userPref.getString("userObject","");
        Gson gson = new Gson();
        User user = null;
        if(!userObject.isEmpty()){
            user = gson.fromJson(userObject,User.class);
            Log.d("I AM LOGGIN AS ", user.toString());
            WishlistAdapter adapter = new WishlistAdapter(getContext(),user.getWishlist());
            itemList.setAdapter(adapter);
            justifyListViewHeightBasedOnChildren (itemList);

        }else if(userObject.isEmpty()){
            Intent intent = new Intent(getContext(), pre_login.class);
            startActivity(intent);
        }


        return view;
    }
}