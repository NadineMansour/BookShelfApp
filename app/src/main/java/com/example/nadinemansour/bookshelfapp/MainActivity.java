package com.example.nadinemansour.bookshelfapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }

    public void facebookLogin(View view) {
        Intent intent = new Intent("com.example.nadinemansour.bookshelfapp.User_profile");
        startActivity(intent);
    }

    public void twitterLogin(View view) {
        Intent intent = new Intent("com.example.nadinemansour.bookshelfapp.User_profile");
        startActivity(intent);
    }



}
