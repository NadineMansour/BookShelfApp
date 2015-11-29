package com.example.nadinemansour.bookshelfapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class Selected_post extends AppCompatActivity {
    private ListView lv;
    CommentData data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.comments_listview);

        data = new CommentData[]{
                new CommentData("The content of Comment#1." , "User1"),
                new CommentData("The content of Comment#2." , "User1"),
                new CommentData("The content of Comment#3." , "User3"),

        };

        CommentAdapter myAdapter=new
                CommentAdapter( this,
                R.layout.comment_item,
                data);

        lv.setAdapter(myAdapter);
    }

}
