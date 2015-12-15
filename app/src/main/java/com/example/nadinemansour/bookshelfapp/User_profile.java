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
import android.widget.ListView;
import android.widget.TextView;

import com.example.nadinemansour.bookshelfapp.util.ApiRouter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class User_profile extends AppCompatActivity {

    private ListView lv;
    public static UserData current_user;
    public static UserData profile;
    NewsFeedData data[];
    NewsFeedAdapter myAdapter;
    public  static NewsFeedData selected_post;
    private TextView user_name;
    private TextView email;
    private TextView provider;
    boolean f1,f2 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setLogo(R.drawable.ic_friends);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        user_name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        provider = (TextView)findViewById(R.id.provider);

        final String user_app_token = FacebookLoginFragment.user_token;

        //get the current user data
        ApiRouter.withToken(user_app_token).getUser(new Callback<UserData>() {
            @Override
            public void success(UserData user, Response response) {
                Log.d("Profile:", "Success");
                current_user = user;
                profile = user;
                user_name.setText(current_user.name);
                email.setText(current_user.email);
                provider.setText(current_user.provider);

                ApiRouter.withToken(user_app_token).getTimeline(new Callback<List<NewsFeedData>>() {
                    @Override
                    public void success(List<NewsFeedData> posts, Response response) {
                        Log.d("Posts:", "Success");
                        data = new NewsFeedData[posts.size()];
                        int i = 0;
                        for (final NewsFeedData post : posts) {
                            data[i] = post;
                            i++;
                        }
                        setTimeline();
                    }

                    @Override
                    public void failure(RetrofitError e) {
                        Log.d("Posts:", "Failure");
                    }
                });
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Profile:", "Failure");
            }
        });
    }


    void setTimeline(){

        lv = (ListView) findViewById(R.id.profile_listview);

        myAdapter = new
                NewsFeedAdapter(this,
                R.layout.newsfeed_list_item,
                data);

        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                selected_post = data[position];
                Intent i = new Intent(User_profile.this, Selected_post.class);
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

    public void quote(View view) {
        Intent intent= new Intent(User_profile.this, Quote_new.class);
        startActivity(intent);
    }

    public void review(View view) {
        Intent intent= new Intent(User_profile.this, New_review.class);
        startActivity(intent);
    }

    public void status(View view) {
        Intent intent= new Intent(User_profile.this, New_status.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.profile_id:
                intent= new Intent(User_profile.this, User_profile.class);
                startActivity(intent);
                return true;

            case R.id.newsfeed_id:
                intent = new Intent(User_profile.this, News_feed.class);
                startActivity(intent);
                return true;

            case R.id.friends_id:
                intent = new Intent(User_profile.this, Friends.class);
                startActivity(intent);
                return true;

            case R.id.requests_id:
                intent = new Intent(User_profile.this, requests.class);
                startActivity(intent);
                return true;

            case R.id.search_id:
                intent = new Intent(User_profile.this, Search.class);
                startActivity(intent);
                return true;

            case R.id.logout_id:
                intent = new Intent(User_profile.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
