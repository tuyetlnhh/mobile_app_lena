package com.example.app_mobile_lena.navbar_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.app_mobile_lena.R;
import com.example.app_mobile_lena.Account_section.account_setting;
import com.example.app_mobile_lena.Account_section.orderHistory;
import com.example.app_mobile_lena.Account_section.pre_login;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link account_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class account_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public account_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static account_fragment newInstance(String param1, String param2) {
        account_fragment fragment = new account_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    Button btnAccount;
    Gson gson = new Gson();


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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        btnAccount = (Button) view.findViewById(R.id.btnAccount);
        Button btnOrderHis = view.findViewById(R.id.btnOrderHis);
        Button btnOrderTracker = view.findViewById(R.id.btnOrderTracker);

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getContext().getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
                if(sharedPref.getString("userObject",null) == null){
                    Intent intent = new Intent(getActivity(), pre_login.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), account_setting.class);
                    startActivity(intent);
                }
            }
        });

        btnOrderHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getContext().getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE);
                if(sharedPref.getString("userObject",null) == null){

                    Intent intent = new Intent(getContext(), pre_login.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), orderHistory.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}