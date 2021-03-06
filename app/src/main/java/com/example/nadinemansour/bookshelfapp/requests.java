package com.example.nadinemansour.bookshelfapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nadinemansour.bookshelfapp.util.ApiRouter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class requests extends AppCompatActivity {

    private ListView lv;
    UserData [] data;
    public static UserData selected_request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getRequests();
    }

    void getRequests(){
        ApiRouter.withToken(User_profile.current_user.token).getRequests(new Callback<List<UserData>>() {
            @Override
            public void success(List<UserData> requests, Response response) {
                Log.d("Requests:", "Success");
                data = new UserData[requests.size()];
                int i = 0;
                for (final UserData request : requests) {
                    if (request.email == null)
                        request.email = "No Email";
                    data[i] = request;
                    i++;
                }
                setRequests();
            }
            @Override
            public void failure(RetrofitError e) {
                Log.d("Requests:", "Failure");
            }
        });
    }

    void setRequests(){
        lv = (ListView) findViewById(R.id.request_listview);

        RequestsAdapter myAdapter=new
                RequestsAdapter( this,
                R.layout.requests_list_item,
                data);

        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                selected_request = data[position];
                Intent i = new Intent(requests.this, RequestProfile.class);
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
                intent= new Intent(requests.this, User_profile.class);
                startActivity(intent);
                return true;

            case R.id.newsfeed_id:
                intent = new Intent(requests.this, News_feed.class);
                startActivity(intent);
                return true;

            case R.id.friends_id:
                intent = new Intent(requests.this, Friends.class);
                startActivity(intent);
                return true;

            case R.id.requests_id:
                intent = new Intent(requests.this, requests.class);
                startActivity(intent);
                return true;

            case R.id.search_id:
                intent = new Intent(requests.this, Search.class);
                startActivity(intent);
                return true;

            case R.id.logout_id:
                intent = new Intent(requests.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
