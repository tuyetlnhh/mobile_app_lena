package com.example.app_mobile_lena;

public class orderHistoryItem {
    private String id;
    private String status;
    private String img;
    private String name;
    private double price;
    private double price_sale;

    public orderHistoryItem(String id, String status, String img, String name, double price, double price_sale) {
        this.id = id;
        this.status = status;
        this.img = img;
        this.name = name;
        this.price = price;
        this.price_sale = price_sale;
    }

    public orderHistoryItem() {
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getPrice_sale() {
        return price_sale;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPrice_sale(double price_sale) {
        this.price_sale = price_sale;
    }

    @Override
    public String toString() {
        return "orderHistoryItem{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", price_sale=" + price_sale +
                '}';
    }
}
