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
import com.example.app_mobile_lena.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> img = new ArrayList<>();
    ArrayList<Double> price_sale = new ArrayList<>();
    ArrayList<Double> price = new ArrayList<>();
    LayoutInflater inflater;

    public GridAdapter(Context context, ArrayList<String> name, ArrayList<String> img, ArrayList<Double> price_sale, ArrayList<Double> price){
       Log.d("TAG","size = "+Integer.toString(name.size()));

        this.context = context;
        this.name.addAll(name);
        this.img.addAll(img) ;
        this.price_sale.addAll(price_sale);
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
        Log.i("TAG","name = " +  name.get(position));
        txtName.setText(name.get(position));
        txtPrice.setText(addThousandSeparator(Double.valueOf(price.get(position)))+"VND");
        txtPriceSale.setText(addThousandSeparator(Double.valueOf(price_sale.get(position)))+"VND");

//        imgView.setImageResource(R.drawable.image_1);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading_icon) // (tùy chọn) hình ảnh hiển thị trước khi tải
                .error(R.drawable.no_image);
        Glide.with(convertView)
                .load(img.get(position))
                .apply(requestOptions)
                .into(imgView);

        return convertView;
    }
    public void updateData(ArrayList<String> name, ArrayList<String> img, ArrayList<Double> price_sale, ArrayList<Double> price) {
        this.name.clear();
        this.name.addAll(name);
        this.img.clear();
        this.img.addAll(img);
        this.price_sale.clear();
        this.price_sale.addAll(price_sale);
        this.price.clear();
        this.price.addAll(price);
        notifyDataSetChanged();
    }


}
