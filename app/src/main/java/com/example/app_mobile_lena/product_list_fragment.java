package com.example.app_mobile_lena;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Layout;
import android.util.Log;
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
import android.widget.TextView;
import android.window.SplashScreen;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


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
    private String titles;
    private String category;
    private GridView gview;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public product_list_fragment() {
        // Required empty public constructor
    }

    public product_list_fragment(String titles, String category){

        this.titles = titles;
        this.category = category;
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
    public void finalize() throws Throwable {
        try {
            // perform some cleanup operations or any other necessary tasks here
        } finally {
            super.finalize();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<items> itemList = new ArrayList<>();
        Context context = getContext();
        Log.d("TAG", "category: " + this.category);
        // Inflate the layout for this fragment
        db.collection("items")
                .whereEqualTo("category",this.category)
                .whereArrayContains("tags",this.titles)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> name = new ArrayList<>();
                            ArrayList<Double> price = new ArrayList<>();
                            ArrayList<Double> price_sale = new ArrayList<>();
                            ArrayList<String> img = new ArrayList<>();


                            for (QueryDocumentSnapshot document : task.getResult()) {
                                items item =  new items();
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                name.add(document.getData().get("name").toString());
                                item.setName(document.getData().get("name").toString());
                                price.add(Double.valueOf(document.getData().get("price").toString()));
                                item.setPrice(Double.valueOf(document.getData().get("price").toString()));
                                price_sale.add(Double.valueOf(document.getData().get("sale_price").toString()));
                                item.setSale_price(Double.valueOf(document.getData().get("sale_price").toString()));
                                img.add(document.getData().get("image").toString());
                                item.setImage(document.getData().get("image").toString());
                                item.setDescription(document.getData().get("description").toString());
                                itemList.add(item);

                            }

                            GridAdapter grid = new GridAdapter(getContext(),name, img, price_sale,price );
                            gview.setAdapter(grid);

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        gview = view.findViewById(R.id.grid_view);
        TextView txtTitles = view.findViewById(R.id.txtTitle);
        txtTitles.setText(this.titles);





        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);

                intent.putExtra("item", itemList.get(position));
                startActivity(intent);
            }
        });


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