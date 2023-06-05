package com.example.app_mobile_lena;

public class User {
    private String email;
    private String date;
    private String password;
    private  String gender;
    User(){
        this.email = "";
        this.date = "";
        this.gender = "";
        this.password = "";
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
}
