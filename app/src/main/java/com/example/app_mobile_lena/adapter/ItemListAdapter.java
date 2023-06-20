package com.example.app_mobile_lena.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.app_mobile_lena.Cart_section.CartItems;
import com.example.app_mobile_lena.R;

import org.checkerframework.checker.units.qual.A;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemListAdapter  extends BaseAdapter {
    Context context;

    ArrayList<CartItems> item = new ArrayList<>();
    LayoutInflater inflater;
    public ItemListAdapter(Context context, ArrayList<CartItems> item){
        this.context = context;
        this.item.addAll(item);
    }


    @Override
    public int getCount(){
        return item.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        }

        ImageView imgView = convertView.findViewById(R.id.productImage);
        TextView txtName = convertView.findViewById(R.id.txtProductName);
        TextView txtPrice = convertView.findViewById(R.id.txtProductPrice);



        txtName.setText(item.get(position).getName());
        txtPrice.setText(addThousandSeparator(Double.valueOf(item.get(position).getPrice()))+"VND");


//
//        imgView.setImageResource(R.drawable.image_1);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading_icon) // (tùy chọn) hình ảnh hiển thị trước khi tải
                .error(R.drawable.no_image);
        Glide.with(convertView)
                .load(item.get(position).getImg())
                .apply(requestOptions)
                .into(imgView);

        return convertView;
    }
}
