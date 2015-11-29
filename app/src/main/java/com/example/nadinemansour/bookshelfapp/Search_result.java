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
import android.widget.Toast;

public class Search_result extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.list1);

        final SearchResultData[] items = new SearchResultData[6];

        items[0] = new SearchResultData("Friend1" , "Friend1@email.com", SearchAdapter.TYPE_FRIENDS);
        items[1] = new SearchResultData("Friend2" , "Friend2@email.com", SearchAdapter.TYPE_FRIENDS);
        items[2] = new SearchResultData("Request1" , "Request1@email.com", SearchAdapter.TYPE_PENDING);
        items[3] = new SearchResultData("Pending1" , "Pending1@email.com", SearchAdapter.TYPE_REQUEST);
        items[4] = new SearchResultData("NotFriend1" , "NotFriend1@email.com", SearchAdapter.TYPE_NOTFRIENDS);
        items[5] = new SearchResultData("NotFriend2" , "NotFriend12@email.com", SearchAdapter.TYPE_NOTFRIENDS);


        SearchAdapter customAdapter = new SearchAdapter(this, R.id.text, items);
        lv.setAdapter(customAdapter);

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
                intent= new Intent(Search_result.this, User_profile.class);
                startActivity(intent);
                return true;

            case R.id.newsfeed_id:
                intent = new Intent(Search_result.this, News_feed.class);
                startActivity(intent);
                return true;

            case R.id.friends_id:
                intent = new Intent(Search_result.this, Friends.class);
                startActivity(intent);
                return true;

            case R.id.requests_id:
                intent = new Intent(Search_result.this, requests.class);
                startActivity(intent);
                return true;

            case R.id.search_id:
                intent = new Intent(Search_result.this, Search.class);
                startActivity(intent);
                return true;

            case R.id.logout_id:
                intent = new Intent(Search_result.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

}
