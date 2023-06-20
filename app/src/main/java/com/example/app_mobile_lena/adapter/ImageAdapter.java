package com.example.app_mobile_lena.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.app_mobile_lena.R;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends PagerAdapter {
        private Context context;
    private ArrayList<String> mListProductImage = new ArrayList<>();

        public ImageAdapter(Context context, ArrayList<String> slider) {
            this.context = context;
            this.mListProductImage.addAll(slider);
        }

        private LayoutInflater layoutInflater;

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

    @Override
    public int getCount() {
                return mListProductImage.size();

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.product_image_item,container,false);
        ImageView img = view.findViewById(R.id.image_view);

        String image = mListProductImage.get(position);
        Log.d("TAG", "Image: " + image);
        if(image != null) {

            Glide.with(context).load(image).into(img);
        }

        //Add view to viewgroup
        container.addView(view);

        return view;
        }
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);

        }

}
