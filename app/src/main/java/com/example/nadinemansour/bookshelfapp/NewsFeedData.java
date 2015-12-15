package com.example.nadinemansour.bookshelfapp;

/**
 * Created by NadineMansour on 11/25/15.
 */
public class NewsFeedData {
    String user1;
    String user2;
    String genre;
    String content;
    String book;
    String author;
    int id;
    int user_id;
    int timeline_id;
    UserData user;
    UserData timeline;


    public NewsFeedData(String u1 , String u2, String t, String c , String b , String a , int i){
        super();
        this.user1 = u1;
        this.user2 = u2;
        this.genre = t;
        this.content = c;
        this.book = b;
        this.author = a;
        this.id = i;
    }
}
