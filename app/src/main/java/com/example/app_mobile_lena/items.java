package com.example.app_mobile_lena;

import java.io.Serializable;

public class items implements Serializable {
    private String name;
    private String category;
    private String image;
    private Double price;
    private Double sale_price;
    private int quantity;

    private String description;
    items(){
        this.name = "";
        this.category = "";
        this.image = "";
        this.price = 0.0;
        this.sale_price = 0.0;
        this.quantity = 0;
        this.description = "";
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

    public int getQuantity() {
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSale_price(Double sale_price) {
        this.sale_price = sale_price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
