package com.example.nadinemansour.bookshelfapp;

import android.app.Activity;
import android.content.Intent;
import  android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;


public class News_feed extends AppCompatActivity {

    private ListView lv;
    private boolean all;
    private boolean quotes;
    private boolean reviews;
    private boolean status;
    NewsFeedData data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.newsfeed_listview);

        data = new NewsFeedData[]{
                new NewsFeedData("User1" , "User1" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",1),
                new NewsFeedData("User2" , "User2" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",2),
                new NewsFeedData("User1" , "User1" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",3),
                new NewsFeedData("User1" , "User1" , "Review" , "This is my first quote from this awesome book" , "Book2" , "Author2",4),
                new NewsFeedData("User1" , "User1" , "status" , "This is my first quote from this awesome book" , "Book1" , "Author1",5)
        };

        NewsFeedAdapter myAdapter=new
                NewsFeedAdapter( this,
                R.layout.newsfeed_list_item,
                data);

        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                Intent i = new Intent(News_feed.this, Selected_post.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profile_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    void quotes(){
        NewsFeedData quotes[]=new NewsFeedData[]{
                new NewsFeedData("User1" , "User1" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",1),
                new NewsFeedData("User2" , "User2" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",2),
                new NewsFeedData("User1" , "User1" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",3)
        };
        NewsFeedAdapter myAdapter=new
                NewsFeedAdapter( this,
                R.layout.newsfeed_list_item,
                quotes);

        lv.setAdapter(myAdapter);
    }

    void reviews(){
        NewsFeedData reviews[]=new NewsFeedData[]{
                new NewsFeedData("User1" , "User1" , "Review" , "This is my first quote from this awesome book" , "Book2" , "Author2",4)
        };
        NewsFeedAdapter myAdapter=new
                NewsFeedAdapter( this,
                R.layout.newsfeed_list_item,
                reviews);

        lv.setAdapter(myAdapter);
    }

    void status(){
        NewsFeedData status[]=new NewsFeedData[]{
                new NewsFeedData("User1" , "User1" , "status" , "This is my first quote from this awesome book" , "Book1" , "Author1",5)
        };
        NewsFeedAdapter myAdapter=new
                NewsFeedAdapter( this,
                R.layout.newsfeed_list_item,
                status);

        lv.setAdapter(myAdapter);
    }

    void all(){
        NewsFeedData all[]=new NewsFeedData[]{
                new NewsFeedData("User1" , "User1" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",1),
                new NewsFeedData("User2" , "User2" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",2),
                new NewsFeedData("User1" , "User1" , "Quote" , "This is my first quote from this awesome book" , "Book1" , "Author1",3),
                new NewsFeedData("User1" , "User1" , "Review" , "This is my first quote from this awesome book" , "Book2" , "Author2",4),
                new NewsFeedData("User1" , "User1" , "status" , "This is my first quote from this awesome book" , "Book1" , "Author1",5)
        };
        NewsFeedAdapter myAdapter=new
                NewsFeedAdapter( this,
                R.layout.newsfeed_list_item,
                all);

        lv.setAdapter(myAdapter);
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.all_rb:
                all = checked;
                quotes = false;
                reviews = false;
                status = false;
                all();
                break;
            case R.id.quotes_rb:
                quotes = checked;
                all = false;
                reviews = false;
                status = false;
                quotes();
                break;
            case R.id.reviews_rb:
                reviews=checked;
                quotes = false;
                all = false;
                status = false;
                reviews();
                break;
            case R.id.status_rb:
                status=checked;
                quotes = false;
                reviews = false;
                all = false;
                status();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.profile_id:
                intent= new Intent(News_feed.this, User_profile.class);
                startActivity(intent);
                return true;

            case R.id.newsfeed_id:
                intent = new Intent(News_feed.this, News_feed.class);
                startActivity(intent);
                return true;

            case R.id.friends_id:
                intent = new Intent(News_feed.this, Friends.class);
                startActivity(intent);
                return true;

            case R.id.requests_id:
                intent = new Intent(News_feed.this, requests.class);
                startActivity(intent);
                return true;

            case R.id.search_id:
                intent = new Intent(News_feed.this, Search.class);
                startActivity(intent);
                return true;

            case R.id.logout_id:
                intent = new Intent(News_feed.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


}
