package com.example.nadinemansour.bookshelfapp;

/**
 * Created by NadineMansour on 11/29/15.
 */
public class SearchResultData {
    String user_name;
    String email;
    int type;

    public SearchResultData(String u, String e, int type) {
        this.user_name = u;
        this.email = e;
        this.type = type;
    }


    public int getType() {
        return type;
    }

}
