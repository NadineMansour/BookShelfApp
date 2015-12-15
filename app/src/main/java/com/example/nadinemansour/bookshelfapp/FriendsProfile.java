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

public class FriendsProfile extends AppCompatActivity {

    private ListView lv;
    public static UserData profile;
    NewsFeedData data[];
    NewsFeedAdapter myAdapter;
    public  static NewsFeedData selected_post;
    private TextView user_name;
    private TextView email;
    private TextView provider;
    String user_app_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setLogo(R.drawable.ic_friends);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        user_name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        provider = (TextView)findViewById(R.id.provider);
        user_app_token = FacebookLoginFragment.user_token;

        profile = Friends.selected_friend;

        user_name.setText(profile.name);
        email.setText(profile.email);
        provider.setText(profile.provider);

        //get the current user data
        ApiRouter.withToken(user_app_token).getFriendsPosts(profile.id ,new Callback<List<NewsFeedData>>() {
            @Override
            public void success(List<NewsFeedData> posts, Response response) {
                Log.d("Friends Profile:", "Success");
                data = new NewsFeedData[posts.size()];
                int i = 0;
                for ( NewsFeedData post : posts) {
                    data[i] = post;
                    i++;
                }
                setTimeline();
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Friends Profile:", "Failure");
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
                User_profile.selected_post = data[position];
                Intent i = new Intent(FriendsProfile.this, Selected_post.class);
                startActivity(i);
            }
        });
    }

    public void remove(View view){
        ApiRouter.withToken(user_app_token).removeFriend(profile.id, new Callback<Response>() {
            @Override
            public void success(Response posts, Response response) {
                Log.d("Unfriend:", "Success");
                Intent i = new Intent(FriendsProfile.this, Friends.class);
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Unfriend:", "Failure");
            }
        });
    }

    public void quote(View view) {
        User_profile.profile = profile;
        Intent intent= new Intent(FriendsProfile.this, Quote_new.class);
        startActivity(intent);
    }

    public void review(View view) {
        User_profile.profile = profile;
        Intent intent= new Intent(FriendsProfile.this, New_review.class);
        startActivity(intent);
    }

    public void posts(View view){
        ApiRouter.withToken(user_app_token).getFriendsPosts(profile.id ,new Callback<List<NewsFeedData>>() {
            @Override
            public void success(List<NewsFeedData> posts, Response response) {
                Log.d("Friends Profile:", "Success");
                data = new NewsFeedData[posts.size()];
                int i = 0;
                for ( NewsFeedData post : posts) {
                    data[i] = post;
                    i++;
                }
                setTimeline();
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Friends Profile:", "Failure");
            }
        });
    }

    public void friends(View view) {
        User_profile.profile = profile;
        ApiRouter.withToken(User_profile.current_user.token).getFriendsFriends(profile.id, new Callback<List<UserData>>() {
            @Override
            public void success(List<UserData> friends, Response response) {
                Log.d("Friends:", "Success");
                UserData[] friendsData = new UserData[friends.size()];
                int i = 0;
                for (final UserData friend : friends) {
                    if (friend.email == null)
                        friend.email = "No Email";
                    friendsData[i] = friend;
                    i++;
                }
                setFriends(friendsData);
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Friend:", "Failure");
            }
        });
    }

    void setFriends(UserData[] friendsData){
        lv = (ListView) findViewById(R.id.profile_listview);

        UserAdapter myAdapter=new
                UserAdapter( this,
                R.layout.activity_friends_item,
                friendsData);

        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

            }
        });
    }

    public void status(View view) {
        User_profile.profile = profile;
        Intent intent= new Intent(FriendsProfile.this, New_status.class);
        startActivity(intent);
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
                intent= new Intent(FriendsProfile.this, User_profile.class);
                startActivity(intent);
                return true;

            case R.id.newsfeed_id:
                intent = new Intent(FriendsProfile.this, News_feed.class);
                startActivity(intent);
                return true;

            case R.id.friends_id:
                intent = new Intent(FriendsProfile.this, Friends.class);
                startActivity(intent);
                return true;

            case R.id.requests_id:
                intent = new Intent(FriendsProfile.this, requests.class);
                startActivity(intent);
                return true;

            case R.id.search_id:
                intent = new Intent(FriendsProfile.this, Search.class);
                startActivity(intent);
                return true;

            case R.id.logout_id:
                intent = new Intent(FriendsProfile.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
