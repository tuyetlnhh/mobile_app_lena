package com.example.app_mobile_lena;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Item implements Serializable {
    private String key;
    private String name;
    private String category;
    private String image;
    private Double price;
    private Double sale_price;
    private long quantity;
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private ArrayList<String> slider;
    private Double rate;

    Item(){
        this.name = "";
        this.category = "";
        this.image = "";
        this.price = 0.0;
        this.sale_price = 0.0;
        this.quantity = 0;
        this.description = "";
        this.rate = 0.0;
        slider = new ArrayList<>();
    }

    public Double getRate() {
        return rate;
    }

    public ArrayList<String> getSlider() {
        return slider;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSale_price() {
        return sale_price;
    }

    public long getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setSale_price(Double sale_price) {
        this.sale_price = sale_price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSlider(ArrayList<String> slider) {
        this.slider.addAll(slider);
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void orderByName(ArrayList<Item> itemList){
        Collections.sort(itemList, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.getName().compareTo(item2.getName());
            }
        });
    }


}
