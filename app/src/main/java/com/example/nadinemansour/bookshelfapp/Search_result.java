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
import android.widget.Toast;

import com.example.nadinemansour.bookshelfapp.util.ApiRouter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Search_result extends AppCompatActivity {

    private ListView lv;
    List<SearchResultData> friends;
    List<SearchResultData> requests;
    List<SearchResultData> pending;
    List<SearchResultData> not_friends;
    boolean f1,f2,f3,f4;
    public static UserData selected_person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        f1 = f2 = f3 = f4 = false;
        searchFriends();
    }

    void searchFriends(){
        ApiRouter.withToken(User_profile.current_user.token).searchFriends(Search.content, new Callback<List<SearchResultData>>() {
            @Override
            public void success(List<SearchResultData> f, Response response) {
                Log.d("Friends-Search:", "Success");
                friends = f;
                searchRequests();
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Friends-Search:", "Failure");
            }
        });
    }

    void searchRequests(){
        ApiRouter.withToken(User_profile.current_user.token).searchRequests(Search.content, new Callback<List<SearchResultData>>() {
            @Override
            public void success(List<SearchResultData> f, Response response) {
                Log.d("Requests-Search:", "Success");
                requests = f;
                searchPending();
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Requests-Search:", "Failure");
            }
        });
    }

    void searchPending(){
        ApiRouter.withToken(User_profile.current_user.token).searchPending(Search.content, new Callback<List<SearchResultData>>() {
            @Override
            public void success(List<SearchResultData> f, Response response) {
                Log.d("Pending-Search:", "Success");
                pending = f;
                searchNotFriends();
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Pending-Search:", "Failure");
            }
        });
    }

    void searchNotFriends(){
        ApiRouter.withToken(User_profile.current_user.token).searchNotFriends(Search.content, new Callback<List<SearchResultData>>() {
            @Override
            public void success(List<SearchResultData> f, Response response) {
                Log.d("Not-Friends-Search:", "Success");
                not_friends = f;
                setResult();
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Not-Friends-Search:", "Failure");
            }
        });
    }

    void setResult(){
        final SearchResultData [] result = new SearchResultData[friends.size()+requests.size()+pending.size()+not_friends.size()];
        int k=0;
        if (friends!=null)
            for (int i=0 ;i<friends.size();i++){
                result[k] = friends.get(i);
                result[k].type = SearchAdapter.TYPE_FRIENDS;
                k++;
            }
        if (requests!=null)
            for (int i=0 ;i<requests.size();i++){
                result[k] = requests.get(i);
                result[k].type = SearchAdapter.TYPE_REQUEST;
                k++;
            }
        if (pending!=null)
            for (int i=0 ;i<pending.size();i++){
                result[k] = pending.get(i);
                result[k].type = SearchAdapter.TYPE_PENDING;
                k++;
            }
        if (not_friends!=null)
            for (int i=0 ;i<not_friends.size();i++){
                result[k] = not_friends.get(i);
                result[k].type = SearchAdapter.TYPE_NOTFRIENDS;
                k++;
            }

        lv = (ListView) findViewById(R.id.list1);
        SearchAdapter customAdapter = new SearchAdapter(this, R.id.text, result);
        lv.setAdapter(customAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                switch (result[position].type) {
                    case SearchAdapter.TYPE_FRIENDS:
                        Friends.selected_friend = new UserData(result[position].name,result[position].image,result[position].toString(),result[position].email,result[position].provider,result[position].id);
                        Intent i = new Intent(Search_result.this, FriendsProfile.class);
                        startActivity(i);
                        break;
                    case SearchAdapter.TYPE_REQUEST:
                        com.example.nadinemansour.bookshelfapp.requests.selected_request = new UserData(result[position].name,result[position].image,result[position].toString(),result[position].email,result[position].provider,result[position].id);
                        Intent i1 = new Intent(Search_result.this, RequestProfile.class);
                        startActivity(i1);
                        break;
                    case SearchAdapter.TYPE_NOTFRIENDS:
                        selected_person = new UserData(result[position].name,result[position].image,result[position].toString(),result[position].email,result[position].provider,result[position].id);
                        Intent i2 = new Intent(Search_result.this, NotFriends.class);
                        startActivity(i2);
                        break;
                }
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

    public void view_profile(View view) {
        Intent intent= new Intent(this, User_profile.class);
        startActivity(intent);
    }

}
