package com.example.app_mobile_lena;

import java.util.ArrayList;

public interface MyCallback {
    public default void onCallback(ArrayList<Item> eventList) {

    }
    public default void onCallback(Item item){

    }
}
