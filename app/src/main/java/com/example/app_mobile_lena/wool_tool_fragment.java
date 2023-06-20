package com.example.app_mobile_lena;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link wool_tool_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class wool_tool_fragment extends Fragment {
    public interface OnListItemClickListener {
        void onListItemClick(int position, String titles, String category);
    }
    private wool_product_fragment.OnListItemClickListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof wool_product_fragment.OnListItemClickListener) {
            mListener = (wool_product_fragment.OnListItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListItemClickListener");
        }
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String titles;
    private String category;

    public wool_tool_fragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment wool_tool_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static wool_tool_fragment newInstance(String param1, String param2) {
        wool_tool_fragment fragment = new wool_tool_fragment();
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
    public void onResume() {
        super.onResume();
        Log.d("TAG", "tool resume");
        View view = getView();
        if(view != null){
            ListView listView = view.findViewById(R.id.list_view);
            String[] items = {"Len", "Dụng Cụ", "Phụ Kiện", "Khác"};
            Context context = getContext();

// Create an adapter to populate the ListView with the data
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, items);

// Set the adapter for the ListView
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String titles = items[position];
                    if (mListener != null) {
                        mListener.onListItemClick(position,titles,"tool");
                    }

                }
            });
        }


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wool_tool, container, false);
        view.requestFocus();
        ListView listView = view.findViewById(R.id.list_view);

// Create an array of data to display
        String[] items = {"Len", "Dụng Cụ", "Phụ Kiện", "Khác"};
        Context context = getContext();

// Create an adapter to populate the ListView with the data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, items);

// Set the adapter for the ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String titles = items[position];
                Log.d("TAG", "titles: "+titles);
                if (mListener != null) {
                    mListener.onListItemClick(position, titles, "tool");
                }

            }
        });


        return view;
    }
}