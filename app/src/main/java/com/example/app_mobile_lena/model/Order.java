package com.example.app_mobile_lena.model;

import com.example.app_mobile_lena.Cart_section.CartItems;

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
    private String status;

    private static int seed = 0;

    private ArrayList<CartItems> cartItems;

    String randomId = "HD00" + Integer.toString(seed);

    public Order() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(String email, String phone, String total, String address, ArrayList<CartItems> cartItems,String status) {
        this.Id = randomId;
        this.email = email;
        this.phone = phone;
        this.total = total;
        this.address = address;
        this.cartItems = cartItems;
        this.status = status;
        this.seed++;
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
    public void addToCart(CartItems item){
        this.cartItems.add(item);
    }

    public CartItems getCartItemAt(int index){
        return this.cartItems.get(index);
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
