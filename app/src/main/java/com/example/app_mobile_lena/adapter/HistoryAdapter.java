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

public class HistoryAdapter  extends BaseAdapter {
    Context context;
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();
    ArrayList<String> total = new ArrayList<>();
    ArrayList<CartItems> item = new ArrayList<>();
    LayoutInflater inflater;
    public HistoryAdapter(Context context, ArrayList<String> id, ArrayList<String>status, ArrayList<CartItems> item, ArrayList<String> total){
        this.id.addAll(id);
        this.status.addAll(status);
        this.context = context;
        this.item.addAll(item);
        Log.d("TAG", "Total: " + total.get(0));
        this.total.addAll(total);
    }


    @Override
    public int getCount(){
        return id.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.order_history_item, parent, false);
        }

        ImageView imgView = convertView.findViewById(R.id.itemImage);
        TextView txtName = convertView.findViewById(R.id.itemName);
        TextView txtPrice = convertView.findViewById(R.id.itemPrice);
        TextView txtCate = convertView.findViewById(R.id.itemCate);
        TextView txtId = convertView.findViewById(R.id.invoiceID);
        TextView txtStatus = convertView.findViewById(R.id.invoiceStatus);
        TextView txtTotal = convertView.findViewById(R.id.totalInvoice);

        txtTotal.setText("Thành tiền: " + total.get(position)+"VND");
        txtId.setText(id.get(position));
        txtStatus.setText(status.get(position));
        txtName.setText(item.get(position).getName());
        txtPrice.setText(addThousandSeparator(Double.valueOf(item.get(position).getPrice()))+"VND");
        txtCate.setText(item.get(position).getCategory());

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
