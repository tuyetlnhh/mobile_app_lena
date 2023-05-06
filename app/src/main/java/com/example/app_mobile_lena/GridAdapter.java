package com.example.app_mobile_lena;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    product_list_fragment context;
    String[] name;
    int[] img;
    LayoutInflater inflater;

    public GridAdapter(product_list_fragment context, String[] name, int[] img){
        this.context = context;
        this.name = name;
        this.img = img;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.grid_item,null);
        }
        ImageView imgView = convertView.findViewById(R.id.grid_image);
        TextView txt = convertView.findViewById(R.id.item_name);
        imgView.setImageResource(img[position]);
        txt.setText(name[position]);
        return convertView;
    }


}
