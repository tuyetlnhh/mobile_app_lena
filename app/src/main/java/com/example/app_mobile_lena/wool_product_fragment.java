package com.example.app_mobile_lena;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link wool_product_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class wool_product_fragment extends Fragment {
    public interface OnListItemClickListener {
        void onListItemClick(int position, String titles, String category);
    }
    private OnListItemClickListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnListItemClickListener) {
            Log.d("TAG", "hello");
            mListener = (OnListItemClickListener) context;
        } else {
            Log.d("TAG", "must implement OnListItemClickListener");
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
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public wool_product_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment wool_product_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static wool_product_fragment newInstance(String param1, String param2) {
        wool_product_fragment fragment = new wool_product_fragment();
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
        Log.d("TAG", "product_resume");
        View view = getView();
        if(view != null){
            ListView listView = view.findViewById(R.id.list_view);
            String[] items = {"Áo", "Gấu Bông", "Móc Khóa", "Phụ Kiện", "Hoa"};
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
                        Log.d("TAG", "Im at product");
                        mListener.onListItemClick(position,titles, "product");
                    }

                }
            });
        }


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_wool_product, container, false);
        view.requestFocus();
        ListView listView = view.findViewById(R.id.list_view);


// Create an array of data to display
        String[] items = {"Áo", "Gấu Bông", "Móc Khóa", "Phụ Kiện", "Hoa"};
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
                    mListener.onListItemClick(position, titles, "product");
                }

            }
        });


        return view;
    }


}