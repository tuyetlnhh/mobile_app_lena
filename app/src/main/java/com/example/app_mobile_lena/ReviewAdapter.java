package com.example.app_mobile_lena;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ReviewAdapter extends ArrayAdapter<ReviewModel> {

    public ReviewAdapter(Context context, ArrayList<ReviewModel> reviews){
        super(context,R.layout.product_review_item,reviews);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        ReviewModel review = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_review_item,parent,false);

        }

        ImageView user_profile = convertView.findViewById(R.id.ivUserAvatar);
        TextView  user_comment = convertView.findViewById(R.id.tvUserComment);
        TextView user_name = convertView.findViewById(R.id.tvUsername);
        TextView date_review = convertView.findViewById(R.id.tvDateReview);
        RatingBar user_rating = convertView.findViewById(R.id.userRatingBar);

        user_profile.setImageResource(review.userProfileId);
        user_comment.setText(review.userComment);
        user_name.setText(review.userName);
        date_review.setText(review.date);
        user_rating.setRating((float) review.rating);

        return convertView;
    }
}
