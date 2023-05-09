package com.example.app_mobile_lena;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductReviews_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductReviews_Fragment extends Fragment {

    public ProductReviews_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_product_reviews_, container, false);
        int[] userProfileIds = {R.drawable.image_1,R.drawable.image_2,R.drawable.image_3};
        String[] userNames = {"John","Shane","Violet"};
        String[] userComments = {"San pham tot, toi rat thich no","Cung binh thuong nhung gia re","hihi hehe haha"};
        double[] userRatings = {3,3.5,4};
        String[] dates = {"11:23 3/3/2020","11:24 3/4/2021","6/9/2015"};
        ArrayList<ReviewModel> reviews = new ArrayList<>();

        for(int i = 0 ; i<userNames.length;i++){
            reviews.add(
                    new ReviewModel(
                            userRatings[i],
                            dates[i],
                            userProfileIds[i],
                            userNames[i],
                            userComments[i]
                    ));
        }

        ReviewAdapter reviewAdapter = new ReviewAdapter(contentView.getContext(),reviews);

        ListView listReviews = contentView.findViewById(R.id.lvProductReviews);
        listReviews.setAdapter(reviewAdapter);
        return contentView;
    }
}