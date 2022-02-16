package com.example.firebasetest;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User {

    public ArrayList<String> watchlist;
    public String email;

    public User(){}

    public User(ArrayList<String> watchlist, String email){
        this.watchlist = watchlist;
        this.email = email;
    }

}
