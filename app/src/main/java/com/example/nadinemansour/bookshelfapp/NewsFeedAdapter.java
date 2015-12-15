package com.example.nadinemansour.bookshelfapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nadinemansour.bookshelfapp.util.ApiRouter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NadineMansour on 11/25/15.
 */
public class NewsFeedAdapter
        extends ArrayAdapter<NewsFeedData> {

    String app_token;
    int pos;
    private Context context;
    private int resource;
    private NewsFeedData[] objects;
    View row;


    public NewsFeedAdapter(Context context,
                     int resource,
                           NewsFeedData[] objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
        app_token = User_profile.current_user.token;
    }

    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater=
                ((Activity) context).getLayoutInflater();
        row=inflater.inflate(resource,parent,false);
        pos = position;

        TextView content=(TextView)
                row.findViewById(R.id.content);
        TextView footer=(TextView)
                row.findViewById(R.id.footer);

        if (objects[position].genre.equals("status")){
            content.setText("I am currently reading "+objects[position].book+" by "+objects[position].author);
            footer.setText("");
        }
        else {
            footer.setText(objects[position].book+" by "+objects[position].author);
            content.setText(objects[position].content);
        }

        TextView header= (TextView)
                row.findViewById(R.id.header);
        header.setText((CharSequence)
                objects[position].genre + " by " +objects[position].user.name + " on " + objects[position].timeline.name +"'s timeline");

        return row;
    }



}

