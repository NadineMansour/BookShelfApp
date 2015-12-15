package com.example.nadinemansour.bookshelfapp;

import android.app.Activity;
import android.content.Intent;
import  android.os.Bundle;
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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.nadinemansour.bookshelfapp.util.ApiRouter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class News_feed extends AppCompatActivity {

    private ListView lv;
    private boolean all;
    private boolean quotes;
    private boolean reviews;
    private boolean status;
    int qcount,rcount,scout = 0;
    NewsFeedData data[];
    NewsFeedData quotesl[];
    NewsFeedData reviewsl[];
    NewsFeedData statusl[];
    NewsFeedAdapter myAdapter;
    public  static NewsFeedData selected_post;
    public static UserData current_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        current_user = User_profile.current_user;
        lv = (ListView) findViewById(R.id.newsfeed_listview);
        getNewsFeed();
    }

    void getNewsFeed(){
        ApiRouter.withToken(current_user.token).getNewsFeed(new Callback<List<NewsFeedData>>() {
            @Override
            public void success(List<NewsFeedData> posts, Response response) {
                Log.d("Newsfeed:", "Success");
                data = new NewsFeedData[posts.size()];
                int i = 0;
                int length = 0;
                for (final NewsFeedData post : posts) {
                    data[i] = post;
                    switch (post.genre) {
                        case "quote":
                            qcount++;
                            break;
                        case "review":
                            rcount++;
                            break;
                        case "status":
                            scout++;
                            break;
                        default:
                            break;
                    }
                    i++;
                }
                Log.d("NewsfeedSize:", (data.length) + "");
                setNewsFeed();
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Newsfeed:", "Failure");
            }
        });
    }

    void setNewsFeed(){
        lv = (ListView) findViewById(R.id.newsfeed_listview);

        myAdapter = new
                NewsFeedAdapter(this,
                R.layout.newsfeed_list_item,
                data);

        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                selected_post = data[position];
                User_profile.selected_post = data[position];
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
        quotesl =new NewsFeedData[qcount];
        int k =0;
        for (int i = 0;i<data.length;i++){
            if (data[i].genre.equals("quote")){
                quotesl[k] = data[i];
                k++;
            }
        }
        NewsFeedAdapter myAdapter=new
                NewsFeedAdapter( this,
                R.layout.newsfeed_list_item,
                quotesl);

        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                selected_post = data[position];
                User_profile.selected_post = quotesl[position];
                Intent i = new Intent(News_feed.this, Selected_post.class);
                startActivity(i);
            }
        });
    }

    void reviews(){
        reviewsl =new NewsFeedData[rcount];
        int k =0;
        for (int i = 0;i<data.length;i++){
            if (data[i].genre.equals("review")){
                reviewsl[k] = data[i];
                k++;
            }
        }
        NewsFeedAdapter myAdapter=new
                NewsFeedAdapter( this,
                R.layout.newsfeed_list_item,
                reviewsl);

        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                selected_post = reviewsl[position];
                User_profile.selected_post = reviewsl[position];
                Intent i = new Intent(News_feed.this, Selected_post.class);
                startActivity(i);
            }
        });
    }

    void status(){
        statusl =new NewsFeedData[scout];
        int k =0;
        for (int i = 0;i<data.length;i++){
            if (data[i].genre.equals("status")){
                statusl[k] = data[i];
                k++;
            }
        };
        NewsFeedAdapter myAdapter=new
                NewsFeedAdapter( this,
                R.layout.newsfeed_list_item,
                statusl);

        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                selected_post = statusl[position];
                User_profile.selected_post = statusl[position];
                Intent i = new Intent(News_feed.this, Selected_post.class);
                startActivity(i);
            }
        });
    }

    void all(){
        NewsFeedAdapter myAdapter=new
                NewsFeedAdapter( this,
                R.layout.newsfeed_list_item,
                data);

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
