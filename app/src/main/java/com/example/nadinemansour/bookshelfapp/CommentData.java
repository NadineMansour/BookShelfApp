package com.example.nadinemansour.bookshelfapp;

/**
 * Created by NadineMansour on 11/29/15.
 */
public class CommentData {
    String content;
    String user;
    int user_id;

    public CommentData(String c , String u){
        super();
        this.user = u;
        this.content = c;
    }
}
