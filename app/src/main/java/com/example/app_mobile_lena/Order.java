package com.example.app_mobile_lena;

import java.util.ArrayList;
import java.util.UUID;

public class Order {
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    private String email;
    private String phone;
    private String total;
    private String address;

    private ArrayList<CartItems> cartItems;
    UUID randomUUID = UUID.randomUUID();
    String randomId = randomUUID.toString();

    public Order(String email, String phone, String total, String address, ArrayList<CartItems> cartItems) {
        this.Id = randomId;
        this.email = email;
        this.phone = phone;
        this.total = total;
        this.address = address;
        this.cartItems = cartItems;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", total=" + total +
                ", address='" + address + '\'' +
                ", cartItems=" + cartItems +
                '}';
    }
}
