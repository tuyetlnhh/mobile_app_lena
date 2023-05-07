package com.example.app_mobile_lena;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class GridAdapter extends BaseAdapter {
    Context context;
    String[] name;
    int[] img;
    int[] price_sale;
    int[] price;
    LayoutInflater inflater;

    public GridAdapter(Context context, String[] name, int[] img, int[] price_sale, int[] price){
        this.context = context;
        this.name = name;
        this.img = img;
        this.price_sale = price_sale;
        this.price = price;
    }

    @Override
    public int getCount(){
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String addThousandSeparator(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.grid_item,null);
        }
        ImageView imgView = convertView.findViewById(R.id.grid_image);
        TextView txtName = convertView.findViewById(R.id.item_name);
        TextView txtPriceSale = convertView.findViewById(R.id.item_price_sale);
        TextView txtPrice = convertView.findViewById(R.id.item_price);
        imgView.setImageResource(img[position]);
        txtName.setText(name[position]);
        txtPrice.setText(addThousandSeparator(price[position])+"VND");
        txtPriceSale.setText(addThousandSeparator(price_sale[position])+"VND");

        return convertView;
    }


}
