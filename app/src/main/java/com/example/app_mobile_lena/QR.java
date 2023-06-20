package com.example.app_mobile_lena;

public class QR {
    private String collection;
    private String document;
    private String user;

    public QR(String collection, String document, String user) {
        this.collection = collection;
        this.document = document;
        this.user = user;
    }

    public QR() {
        this.collection = "";
        this.document = "";
        this.user = "haha@gmail.com";
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
