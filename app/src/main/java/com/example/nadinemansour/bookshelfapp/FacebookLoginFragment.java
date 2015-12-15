package com.example.nadinemansour.bookshelfapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nadinemansour.bookshelfapp.util.ApiRouter;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class FacebookLoginFragment extends Fragment {

    public static String user_token;
    private CallbackManager callbackManager;
    private TextView welcome;
    private FacebookCallback<LoginResult> callback  = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            ApiRouter.withoutToken().login(accessToken.getToken(), new Callback<UserData>() {
                        @Override
                        public void success(UserData user, Response response) {
                            Log.d("LoginFB:", "Success");
                            user_token = user.token;
                            Intent intent = new Intent("com.example.nadinemansour.bookshelfapp.User_profile");
                            startActivity(intent);
                        }

                        @Override
                        public void failure(RetrofitError e) {
                            Log.d("LoginFB:", "Failure");
                        }
                    });
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    public FacebookLoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_facebook_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        welcome = (TextView)view.findViewById(R.id.welcome);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        //loginButton.setReadPermissions() ;
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager , callback);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode , resultCode , data);
    }
}
