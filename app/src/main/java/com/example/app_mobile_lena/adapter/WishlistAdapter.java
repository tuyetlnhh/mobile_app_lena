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
import com.example.app_mobile_lena.model.Item;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class WishlistAdapter extends BaseAdapter {

    Context context;

    ArrayList<Item> wishlist = new ArrayList<>();
    LayoutInflater inflater;
    public WishlistAdapter(Context context, ArrayList<Item> item){
        this.context = context;
        this.wishlist.addAll(item);
        Log.d("TAG","Item: " + wishlist.toString());
        Log.d("TAG","Item: " + Integer.toString(wishlist.size()));
    }
    @Override
    public int getCount() {
        return wishlist.size();
    }

    public String addThousandSeparator(Double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }
    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.wishlist_item, parent, false);
        }
        Log.d("TAG","test thu");
        ImageView imgView = convertView.findViewById(R.id.itemImage);
        TextView txtName = convertView.findViewById(R.id.itemName);
        TextView txtPrice = convertView.findViewById(R.id.itemPrice);
        txtName.setText(wishlist.get(position).getName());
        txtPrice.setText(addThousandSeparator(Double.valueOf(wishlist.get(position).getPrice()))+"VND");

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading_icon) // (tùy chọn) hình ảnh hiển thị trước khi tải
                .error(R.drawable.no_image);
        Glide.with(convertView)
                .load(wishlist.get(position).getImage())
                .apply(requestOptions)
                .into(imgView);

        return convertView;
    }
}
