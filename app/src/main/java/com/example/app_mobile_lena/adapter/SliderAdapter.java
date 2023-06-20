package com.example.app_mobile_lena.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app_mobile_lena.ProductDetail_section.ProductDetailActivity;
import com.example.app_mobile_lena.QRScanActivity;
import com.example.app_mobile_lena.R;
import com.example.app_mobile_lena.model.Item;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {
    int [] images;
    private Context context;
    ArrayList<Item> item = new ArrayList<>();
    Item item1 = new Item("sp006","Thỏ con hoa đào","Gấu Bông", "https://bizweb.dktcdn.net/thumb/large/100/105/259/products/tho-hoa-dao-75b0bf15-be92-4741-8ba6-a4ff2309e68f.jpg?v=1572583291647",250000.0,270000.0,(long)15.0,"Gấu bông xinh xinh",4.0);
    ArrayList<String> img1 = new ArrayList<>(Arrays.asList("https://bizweb.dktcdn.net/thumb/large/100/105/259/products/tho-hoa-dao-75b0bf15-be92-4741-8ba6-a4ff2309e68f.jpg?v=1572583291647"));
    Item item2 = new Item("sp005","Cáo baby","Gấu Bông", "https://bizweb.dktcdn.net/thumb/1024x1024/100/105/259/products/cao-con-2-6bf07d0d-52cb-43a8-82f6-9f3d2e5b4334.png?v=1564194517740",200000.0,250000.0,(long)20.0,"Thú bông cho trẻ em mọi lứa tuổi",5.0);
    ArrayList<String> img2 = new ArrayList<>(Arrays.asList("https://bizweb.dktcdn.net/thumb/1024x1024/100/105/259/products/cao-con-2-6bf07d0d-52cb-43a8-82f6-9f3d2e5b4334.png?v=1564194517740"));
    Item item3 = new Item("sp025","Hoa tulip","Hoa", "https://vn-live-01.slatic.net/p/5313225c51e82f423cf73f7c1518e189.jpg_1500x1500q80.jpg",230000.0,250000.0,(long)40.0,"7 hoa tulip bằng len",5.0);
    ArrayList<String> img3 = new ArrayList<>(Arrays.asList("https://vn-live-01.slatic.net/p/5313225c51e82f423cf73f7c1518e189.jpg_1500x1500q80.jpg"));
    public SliderAdapter(int[] images, Context context) {
        this.images = images;
        this.context = context;
        item1.setSlider(img1);
        item2.setSlider(img2);
        item3.setSlider(img3);
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item, parent, false);
        return new Holder(view);
    }
    public SliderAdapter(Context context) {
        this.context = context;
    }


    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        viewHolder.imageView.setImageResource(images[position]);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProductDetailActivity.class);
               switch (position){
                   case 0:
                       intent.putExtra("item",item1);
                       break;
                   case 1:
                       intent.putExtra("item",item2);
                       break;
                   case 2:
                       intent.putExtra("item",item3);
                       break;
                   default:
               }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;

        public Holder (View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
