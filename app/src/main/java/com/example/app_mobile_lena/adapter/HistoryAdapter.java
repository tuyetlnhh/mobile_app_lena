package com.example.app_mobile_lena.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_mobile_lena.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HistoryAdapter  extends BaseAdapter {
    Context context;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> img = new ArrayList<>();
    ArrayList<String> cate = new ArrayList<>();
    ArrayList<Double> price = new ArrayList<>();
    LayoutInflater inflater;
    public HistoryAdapter(Context context, ArrayList<String> name, ArrayList<String> img, ArrayList<String> cate, ArrayList<Double> price){
        Log.d("TAG","size = "+Integer.toString(name.size()));

        this.context = context;
        this.name.addAll(name);
        this.img.addAll(img) ;
        this.cate.addAll(cate);
        this.price.addAll(price);
        Log.d("TAG","this name size =" + Integer.toString(this.name.size()));

    }


    @Override
    public int getCount(){
        return name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String addThousandSeparator(Double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        }

        ImageView imgView = convertView.findViewById(R.id.itemImage);
        TextView txtName = convertView.findViewById(R.id.itemName);
        TextView txtPrice = convertView.findViewById(R.id.itemPrice);
        TextView txtCate = convertView.findViewById(R.id.itemCate);

        txtName.setText(name.get(position));
        txtPrice.setText(addThousandSeparator(Double.valueOf(price.get(position)))+"VND");
        txtCate.setText(cate.get(position));


        imgView.setImageResource(R.drawable.image_1);
//        RequestOptions requestOptions = new RequestOptions()
//                .placeholder(R.drawable.loading_icon) // (tùy chọn) hình ảnh hiển thị trước khi tải
//                .error(R.drawable.no_image);
//        Glide.with(convertView)
//                .load(img.get(position))
//                .apply(requestOptions)
//                .into(imgView);

        return convertView;
    }
}
