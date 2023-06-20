package com.example.app_mobile_lena;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class PaymentActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    TextView selection;
    String[] tinh = { "An Giang", "Bà Rịa-Vũng Tàu", "Bạc Liêu",
            "Bắc Giang", "Bắc Kạn", "Bắc Ninh","Thành phố Hồ Chí Minh" };
    String[] quan = { "Quận 1", "Quận 2", "Quận 3",
            "Quận 4", "Quận Thủ Đức"};
    String[] xa = { "Bình Chiểu", "Bình Tho", "Hiệp Bình Chánh",
            "Linh Chiểu", "Linh Đông", "Linh Trung" };

    Button btnOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        btnOrder = findViewById(R.id.btnOrder);
//selection = (TextView) findViewById(R.id.textProvince);
        Spinner spinTinh = (Spinner) findViewById(R.id.spinnerProvince);
        Spinner spinQuan = (Spinner) findViewById(R.id.spinnerDistrict);
        Spinner spinXa = (Spinner) findViewById(R.id.spinnerWard);
        spinTinh.setOnItemSelectedListener(this);
        spinQuan.setOnItemSelectedListener(this);
        spinXa.setOnItemSelectedListener(this);
        // set a clickable right push-button comboBox to show items
        ArrayAdapter<String> tinhArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tinh);
        ArrayAdapter<String> quanArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, quan);
        ArrayAdapter<String> xaArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, xa);
        // provide a particular design for the drop-down lines
        tinhArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // associate GUI spinner and adapter
        spinTinh.setAdapter(tinhArrayAdapter);


        quanArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // associate GUI spinner and adapter
        spinQuan.setAdapter(quanArrayAdapter);


        xaArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // associate GUI spinner and adapter
        spinXa.setAdapter(xaArrayAdapter);

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences userPref = getSharedPreferences("CURRENT_USER",MODE_PRIVATE);
                String userObject = userPref.getString("userObject","");
                String total = CartActivity.txtTotal.getText().toString();
                Log.d("TAG","Total is " + total);
                User user = null;
                Gson gson = new Gson();
                if(!userObject.isEmpty()){
                    user = gson.fromJson(userObject,User.class);
                    Log.d("I AM LOGGIN AS ", user.toString());
                }
                OrderRepository od = new OrderRepository();
                Order order = new Order(user.getEmail(),"0912339905",total,"Ở đâu không biết",user.getCart());
                od.add(order);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
