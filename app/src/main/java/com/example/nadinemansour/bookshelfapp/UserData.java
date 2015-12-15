package com.example.nadinemansour.bookshelfapp;

import java.util.List;

/**
 * Created by NadineMansour on 11/29/15.
 */
public class UserData {
    String name;
    String picture;
    String token;
    String email;
    String provider;
    int id;


    public UserData(String n , String i , String t , String e, String p , int id){
        super();
        this.name = n;
        this.email = e;
        this.picture = i;
        this.token = t;
        this.provider = p;
        this.id = id;
    }
}
