package com.example.app_mobile_lena;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
        private Context context;

        public ImageAdapter(Context context, List<ProductImage> mListProductImage) {
            this.context = context;
            this.mListProductImage = mListProductImage;
        }
        private List<ProductImage> mListProductImage;
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

            ProductImage image = mListProductImage.get(position);
            if(image != null) {
                Glide.with(context).load(image.getResourceId()).into(img);
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
