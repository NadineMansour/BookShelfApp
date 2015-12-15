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
import android.widget.TextView;

import com.example.nadinemansour.bookshelfapp.util.ApiRouter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RequestProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView user_name = (TextView)findViewById(R.id.name);
        TextView email = (TextView)findViewById(R.id.email);
        TextView provider = (TextView)findViewById(R.id.provider);

        user_name.setText(requests.selected_request.name);
        email.setText(requests.selected_request.email);
        provider.setText(requests.selected_request.provider);
    }


    public void accept(View view) {
        ApiRouter.withToken(User_profile.current_user.token).accept(requests.selected_request.id, new Callback<Response>() {
            @Override
            public void success(Response posts, Response response) {
                Log.d("Accept:", "Success");
                Intent i = new Intent(RequestProfile.this, requests.class);
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Accept:", "Failure");
            }
        });
    }

    public void reject(View view) {
        ApiRouter.withToken(User_profile.current_user.token).reject(requests.selected_request.id, new Callback<Response>() {
            @Override
            public void success(Response posts, Response response) {
                Log.d("Reject:", "Success");
                Intent i = new Intent(RequestProfile.this, requests.class);
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Reject:", "Failure");
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
                intent= new Intent(RequestProfile.this, User_profile.class);
                startActivity(intent);
                return true;

            case R.id.newsfeed_id:
                intent = new Intent(RequestProfile.this, News_feed.class);
                startActivity(intent);
                return true;

            case R.id.friends_id:
                intent = new Intent(RequestProfile.this, Friends.class);
                startActivity(intent);
                return true;

            case R.id.requests_id:
                intent = new Intent(RequestProfile.this, requests.class);
                startActivity(intent);
                return true;

            case R.id.search_id:
                intent = new Intent(RequestProfile.this, Search.class);
                startActivity(intent);
                return true;

            case R.id.logout_id:
                intent = new Intent(RequestProfile.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


}
