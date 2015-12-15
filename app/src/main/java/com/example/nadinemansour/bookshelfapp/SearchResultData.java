package com.example.nadinemansour.bookshelfapp;

/**
 * Created by NadineMansour on 11/29/15.
 */
public class SearchResultData {
    String name;
    String email;
    String provider;
    int id;
    int type;
    String image;

    public SearchResultData(String u, String e, int type) {
        this.name = u;
        this.email = e;
        this.type = type;
    }


    public int getType() {
        return type;
    }

}
