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
import android.widget.AdapterView;
import android.widget.ListView;

public class User_profile extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setLogo(R.drawable.ic_friends);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        lv = (ListView) findViewById(R.id.profile_listview);

        NewsFeedData data[]=new NewsFeedData[]{
                new NewsFeedData("User1" , "User1" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",1),
                new NewsFeedData("User2" , "User1" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",2),
                new NewsFeedData("User1" , "User1" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",3),
                new NewsFeedData("User1" , "User1" , "Review" , "This is my first quote from this awesome book" , "Book2" , "Author2",4),
                new NewsFeedData("User1" , "User1" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",5)
        };

        NewsFeedAdapter myAdapter=new
                NewsFeedAdapter( this,
                R.layout.newsfeed_list_item,
                data);

        lv.setAdapter(myAdapter);

        // React to user clicks on item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                Intent intent = new Intent("com.example.nadinemansour.bookshelfapp.Selected_post");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profile_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void quote(View view) {
        Intent intent = new Intent("com.example.nadinemansour.bookshelfapp.New_quote");
        startActivity(intent);
    }

    public void review(View view) {
        Intent intent = new Intent("com.example.nadinemansour.bookshelfapp.New_review");
        startActivity(intent);
    }

    public void status(View view) {
        Intent intent = new Intent("com.example.nadinemansour.bookshelfapp.New_status");
        startActivity(intent);
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
