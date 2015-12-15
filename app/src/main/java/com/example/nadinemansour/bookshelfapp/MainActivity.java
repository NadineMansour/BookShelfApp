package com.example.nadinemansour.bookshelfapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends AppCompatActivity {

    TextView text;
    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accesstoken = loginResult.getAccessToken();
            Profile profile  = Profile.getCurrentProfile();
            if (profile!=null){
                text.setText(profile.getName());
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) this.findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager , callback);
        text = (TextView)this.findViewById(R.id.textView2);
        //loginButton.setReadPermissions();
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }



    public void facebookLogin(View view) {
        Intent intent = new Intent("com.example.nadinemansour.bookshelfapp.User_profile");
        startActivity(intent);
    }

    public void twitterLogin(View view) {
        Intent intent = new Intent("com.example.nadinemansour.bookshelfapp.User_profile");
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode , resultCode , data);
    }



}
