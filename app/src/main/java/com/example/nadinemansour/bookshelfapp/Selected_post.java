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
import android.widget.ListView;
import android.widget.TextView;

import com.example.nadinemansour.bookshelfapp.util.ApiRouter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Selected_post extends AppCompatActivity {
    private ListView lv;
    public static NewsFeedData post;
    CommentData data[];
    String app_token;
    TextView content;
    TextView footer;
    TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        app_token = User_profile.current_user.token;
        post = User_profile.selected_post;
        content = (TextView) findViewById(R.id.post_content);
        footer = (TextView) findViewById(R.id.footer);
        user = (TextView) findViewById(R.id.post_user);

        if (post.genre.equals("status")){
            content.setText("I am currently reading "+post.book+" by "+post.author);
            footer.setText("");
        }
        else {
            footer.setText(post.book+" by "+post.author);
            content.setText(post.content);
        }
        comments();
    }

    public void comments(){
        //get the current user data
        ApiRouter.withToken(app_token).getRelatedComments(post.id, new Callback<List<CommentData>>() {
            @Override
            public void success(List<CommentData> comments, Response response) {
                Log.d("Comments:", "Success");
                data = new CommentData[comments.size()];
                int i = 0;
                for (CommentData comment : comments) {
                    data[i] = comment;
                    i++;
                }
                setComments();
            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("Comments:", "Failure");
            }
        });
    }

    public void setComments(){
        lv = (ListView) findViewById(R.id.comments_listview);

        CommentAdapter myAdapter=new
                CommentAdapter( this,
                R.layout.comment_item,
                data);

        lv.setAdapter(myAdapter);
    }

    public void addComment(View view) {
        EditText comment = (EditText) findViewById(R.id.comment_content);
        String comment_content = comment.getText().toString();
        ApiRouter.withToken(app_token).addComment(post.id, User_profile.current_user.id,comment_content, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Log.d("Add Comment:", "Success");
                Intent intent = new Intent(Selected_post.this, Selected_post.class);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Add Comment:", "Success");
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
                intent= new Intent(Selected_post.this, User_profile.class);
                startActivity(intent);
                return true;

            case R.id.newsfeed_id:
                intent = new Intent(Selected_post.this, News_feed.class);
                startActivity(intent);
                return true;

            case R.id.friends_id:
                intent = new Intent(Selected_post.this, Friends.class);
                startActivity(intent);
                return true;

            case R.id.requests_id:
                intent = new Intent(Selected_post.this, requests.class);
                startActivity(intent);
                return true;

            case R.id.search_id:
                intent = new Intent(Selected_post.this, Search.class);
                startActivity(intent);
                return true;

            case R.id.logout_id:
                intent = new Intent(Selected_post.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

}
