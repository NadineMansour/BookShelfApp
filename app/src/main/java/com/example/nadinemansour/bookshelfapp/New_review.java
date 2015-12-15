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
import android.widget.EditText;

import com.example.nadinemansour.bookshelfapp.util.ApiRouter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class New_review extends AppCompatActivity {

    int user_id;
    int profile_id;
    EditText book;
    EditText author;
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        book = (EditText) findViewById(R.id.book);
        author = (EditText) findViewById(R.id.author);
        content = (EditText) findViewById(R.id.content);
        user_id = User_profile.current_user.id;
        profile_id = User_profile.profile.id;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profile_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void submit_review(View view) {
        String b = book.getText().toString();
        String a = author.getText().toString();
        String c = content.getText().toString();
        ApiRouter.withToken(User_profile.current_user.token).newQuote("review", user_id, profile_id, b, a, c, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Log.d("New quote:", "Success");
                Intent intent = new Intent(New_review.this, User_profile.class);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("New quote:", "Success");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.profile_id:
                intent= new Intent(New_review.this, User_profile.class);
                startActivity(intent);
                return true;

            case R.id.newsfeed_id:
                intent = new Intent(New_review.this, News_feed.class);
                startActivity(intent);
                return true;

            case R.id.friends_id:
                intent = new Intent(New_review.this, Friends.class);
                startActivity(intent);
                return true;

            case R.id.requests_id:
                intent = new Intent(New_review.this, requests.class);
                startActivity(intent);
                return true;

            case R.id.search_id:
                intent = new Intent(New_review.this, Search.class);
                startActivity(intent);
                return true;

            case R.id.logout_id:
                intent = new Intent(New_review.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

}
