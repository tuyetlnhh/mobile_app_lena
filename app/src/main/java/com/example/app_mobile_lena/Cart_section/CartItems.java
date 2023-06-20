package com.example.app_mobile_lena.Cart_section;

public class CartItems {
    private String productId;

    private String name;

    private String category;

    private String img;
    private long quantity;
    private double price;

    public CartItems(String productId, String name, String category, String img, long quantity, double price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.img = img;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", img='" + img + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
