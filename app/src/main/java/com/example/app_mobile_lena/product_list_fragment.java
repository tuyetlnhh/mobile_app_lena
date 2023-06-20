package com.example.app_mobile_lena;

import android.content.Context;
import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.common.value.qual.IntRangeFromGTENegativeOne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link product_list_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class product_list_fragment extends Fragment implements MyCallback{

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
    boolean sortName = true;

    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<Double> price = new ArrayList<>();
    private ArrayList<Double> price_sale = new ArrayList<>();
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private  GridAdapter grid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public product_list_fragment() {
        // Required empty public constructor
    }

    public product_list_fragment(String titles, String category){

        this.titles = titles;
        this.category = category;
    }

    @Override
    public void onCallback(ArrayList<Item> eventList) {

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


    public void readData( MyCallback myCallback) {
        db.collection("items")
                .whereEqualTo("category",this.category)
                .whereArrayContains("tags",this.titles)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                           ArrayList<Item> itemList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Item item =  new Item();

                                //add item
                                item.setKey(document.getId());
                                item.setSale_price(Double.valueOf(document.getData().get("sale_price").toString()));
                                item.setPrice(Double.valueOf(document.getData().get("price").toString()));
                                item.setName(document.getData().get("name").toString());
                                item.setImage(document.getData().get("image").toString());
                                item.setDescription(document.getData().get("description").toString());
                                item.setSlider((ArrayList<String>) document.getData().get("slider"));
                                item.setQuantity((Long) document.getData().get("quantity"));
                                item.setRate(Double.valueOf(document.getData().get("rate").toString()));
                                itemList.add(item);
                            }
                            myCallback.onCallback(itemList);


                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }

                    }
                });
    }
    void clearData(){
        name.clear();
        price.clear();
        price_sale.clear();
        img.clear();
        items.clear();
        Log.d("TAG", "Name size: " + Integer.toString(name.size()));
        Log.d("TAG", "Price size: " + Integer.toString(price.size()));
        Log.d("TAG", "Price_sale size: " + Integer.toString(price_sale.size()));
        Log.d("TAG", "Img size: " + Integer.toString(img.size()));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Context context = getContext();
        Log.d("TAG", "category: " + this.category);
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        gview = view.findViewById(R.id.grid_view);
        TextView txtTitles = view.findViewById(R.id.txtTitle);
        txtTitles.setText(this.titles);
        ImageButton sortBtn = view.findViewById(R.id.btnSort);
        readData(new MyCallback() {
            @Override
            public void onCallback(ArrayList<Item> eventList) {
                Log.d("TAG", "evetList size: " + Integer.toString(eventList.size()));
                items.addAll(eventList);
                for(Item i:items){
                    name.add(i.getName());
                    price.add(i.getPrice());
                    price_sale.add(i.getSale_price());
                    img.add(i.getImage());
                }

                grid = new GridAdapter(getContext(),name, img, price_sale,price );
                gview.setAdapter(grid);
            }
        });

        Log.d("TAG", "items size: " + Integer.toString(items.size()));

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData(new MyCallback() {

                    @Override
                    public void onCallback(ArrayList<Item> eventList) {
                        clearData();
                        Log.d("TAG", "evetList size: " + Integer.toString(eventList.size()));
                        items.addAll(eventList);
                        if(sortName == true){
                            Collections.sort(items, new Comparator<Item>() {
                                @Override
                                public int compare(Item item1, Item item2) {
                                    return item1.getName().compareTo(item2.getName());
                                }
                            });
                            sortName = false;
                        }
                        else{
                            Collections.sort(items, new Comparator<Item>() {
                                @Override
                                public int compare(Item item1, Item item2) {
                                    return item1.getPrice().compareTo(item2.getPrice());
                                }
                            });
                            sortName = true;
                        }


                        for(Item i:items){
                            name.add(i.getName());
                            price.add(i.getPrice());
                            price_sale.add(i.getSale_price());
                            img.add(i.getImage());
                        }
                        Log.d("TAG", "After sort");
                        Log.d("TAG", "Name size: " + Integer.toString(name.size()));
                        Log.d("TAG", "Price size: " + Integer.toString(price.size()));
                        Log.d("TAG", "Price_sale size: " + Integer.toString(price_sale.size()));
                        Log.d("TAG", "Img size: " + Integer.toString(img.size()));
                        grid.updateData(name, img, price_sale, price);
                    }
                });

            }
        });


        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("item", items.get(position));
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