package com.example.app_mobile_lena;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ImageAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    public class ViewAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;
        private Integer[] images={R.drawable.image_2,R.drawable.image_2,R.drawable.image_3,R.drawable.image_4};

        public ViewAdapter(Context context)
        {
            this.context=context;
        }
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater=(LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
            View view =layoutInflater.inflate(R.layout.product_image_item,null);
            ImageView imageView=view.findViewById(R.id.image_view);
            imageView.setImageResource(images[position]);
            ViewPager viewPager=(ViewPager) container;
            viewPager.addView(view,0);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ViewPager viewPager=(ViewPager) container;
            View view=(View) object;
            viewPager.removeView(view);
        }
    }
}
