package com.example.app_mobile_lena;

public class ReviewModel {
    int Id;
    int userId;
    double rating;
    String date;
    int userProfileId;
    String userName;
    String userComment;

    public ReviewModel(double rating, String date, int userProfileId, String userName, String userComment) {
        this.rating = rating;
        this.date = date;
        this.userProfileId = userProfileId;
        this.userName = userName;
        this.userComment = userComment;
    }
}
