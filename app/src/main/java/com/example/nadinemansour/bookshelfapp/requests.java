package com.example.nadinemansour.bookshelfapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class requests extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.request_listview);

        RequestsData data[]=new RequestsData[]{
                new RequestsData("User1"),
                new RequestsData("User2"),
                new RequestsData("User3"),
                new RequestsData("User4"),
                new RequestsData("User5")
        };

        RequestsAdapter myAdapter=new
                RequestsAdapter( this,
                R.layout.requests_list_item,
                data);

        lv.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profile_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.profile_id:
                intent= new Intent("com.example.nadinemansour.bookshelfapp.User_profile");
                startActivity(intent);
                return true;

            case R.id.newsfeed_id:
                intent = new Intent("com.example.nadinemansour.bookshelfapp.News_feed");
                startActivity(intent);
                return true;

            case R.id.friends_id:
                intent = new Intent("com.example.nadinemansour.bookshelfapp.Friends");
                startActivity(intent);
                return true;

            case R.id.requests_id:
                intent = new Intent("com.example.nadinemansour.bookshelfapp.requests");
                startActivity(intent);
                return true;

            case R.id.search_id:
                intent = new Intent("com.example.nadinemansour.bookshelfapp.Search");
                startActivity(intent);
                return true;

            case R.id.logout_id:
                intent = new Intent("com.example.nadinemansour.bookshelfapp.MainActivity");
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
