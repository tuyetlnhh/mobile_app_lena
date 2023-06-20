package com.example.app_mobile_lena;

import com.example.app_mobile_lena.model.Item;

import java.util.ArrayList;

public interface MyCallback {
    public default void onCallback(ArrayList<Item> eventList) {

    }
}
