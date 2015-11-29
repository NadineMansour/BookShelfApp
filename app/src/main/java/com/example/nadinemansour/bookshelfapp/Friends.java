package com.example.nadinemansour.bookshelfapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Friends extends AppCompatActivity {

    private ListView lv;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setContentView(R.layout.content_friends);

        lv = (ListView) findViewById(R.id.friends_listview);

        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("User 1");
        your_array_list.add("User 2");
        your_array_list.add("User 3");
        your_array_list.add("User 3");
        your_array_list.add("User 4");
        your_array_list.add("User 5");
        your_array_list.add("User 6");
        your_array_list.add("User 7");
        your_array_list.add("User 8");
        your_array_list.add("User 9");
        your_array_list.add("User 10");
        your_array_list.add("User 11");
        your_array_list.add("User 12");
        your_array_list.add("User 13");
        your_array_list.add("User 14");
        your_array_list.add("User 15");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.friends_list_item,
                R.id.user_name,
                your_array_list );

        lv.setAdapter(arrayAdapter);

        // React to user clicks on item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                Intent intent = new Intent("com.example.nadinemansour.bookshelfapp.User_profile");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.profile_id:
                intent = new Intent("com.example.nadinemansour.bookshelfapp.User_profile");
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
