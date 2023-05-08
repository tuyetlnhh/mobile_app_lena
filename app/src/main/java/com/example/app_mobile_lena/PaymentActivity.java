package com.example.app_mobile_lena;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    TextView selection;
    String[] items = { "An Giang", "Bà Rịa-Vũng Tàu", "Bạc Liêu",
            "Bắc Giang", "Bắc Kạn", "Bắc Ninh" };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
//selection = (TextView) findViewById(R.id.textProvince);
        Spinner spin = (Spinner) findViewById(R.id.spinnerProvince);
        spin.setOnItemSelectedListener(this);
        // set a clickable right push-button comboBox to show items
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);
        // provide a particular design for the drop-down lines
        aa.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // associate GUI spinner and adapter
        spin.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
