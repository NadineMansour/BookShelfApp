package com.example.nadinemansour.bookshelfapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nadinemansour.bookshelfapp.util.ApiRouter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Friends extends AppCompatActivity {

    private ListView lv;
    UserData[] data;
    public static UserData selected_friend;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getFriends();
    }

    void getFriends(){
        ApiRouter.withToken(User_profile.current_user.token).getFriends(new Callback<List<UserData>>() {
            @Override
            public void success(List<UserData> friends, Response response) {
                Log.d("Friends:", "Success");
                lv = (ListView) findViewById(R.id.friends_listview);
                data = new UserData[friends.size()];
                int i = 0;
                for (final UserData friend : friends) {
                    if (friend.email == null)
                        friend.email = "No Email";
                    data[i] = friend;
                    i++;
                }
                setFriends();
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Friend:", "Failure");
            }
        });
    }

    void setFriends(){
        lv = (ListView) findViewById(R.id.friends_listview);

        UserAdapter myAdapter=new
                UserAdapter( this,
                R.layout.activity_friends_item,
                data);

        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                selected_friend = data[position];
                Intent i = new Intent(Friends.this, FriendsProfile.class);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.profile_id:
                intent= new Intent(Friends.this, User_profile.class);
                startActivity(intent);
                return true;

            case R.id.newsfeed_id:
                intent = new Intent(Friends.this, News_feed.class);
                startActivity(intent);
                return true;

            case R.id.friends_id:
                intent = new Intent(Friends.this, Friends.class);
                startActivity(intent);
                return true;

            case R.id.requests_id:
                intent = new Intent(Friends.this, requests.class);
                startActivity(intent);
                return true;

            case R.id.search_id:
                intent = new Intent(Friends.this, Search.class);
                startActivity(intent);
                return true;

            case R.id.logout_id:
                intent = new Intent(Friends.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

}
