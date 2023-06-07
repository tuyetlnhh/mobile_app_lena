package com.example.app_mobile_lena;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String ID;
    private String email;
    private String date;
    private String password;
    private  String gender;
    private ArrayList<Object> cart = new ArrayList<Object>();

    public ArrayList<Object> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Object> cart) {
        this.cart = cart;
    }

    User(){
        this.email = "";
        this.date = "";
        this.gender = "";
        this.password = "";
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGender() {
        return gender;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", cart=" + cart +
                '}';
    }
}
