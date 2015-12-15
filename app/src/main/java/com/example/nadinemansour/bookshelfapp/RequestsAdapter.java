package com.example.nadinemansour.bookshelfapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by NadineMansour on 11/25/15.
 */
public class RequestsAdapter
        extends ArrayAdapter<UserData> {

    private Context context;
    private int resource;
    private UserData[] objects;


    public RequestsAdapter(Context context,
                           int resource,
                           UserData[] objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater=
                ((Activity) context).getLayoutInflater();
        View row=inflater.inflate(resource, parent, false);

        TextView user= (TextView)
                row.findViewById(R.id.name);

        TextView email= (TextView)
                row.findViewById(R.id.email);


        user.setText((CharSequence)
                objects[position].name);
        Log.d("Name:" ,objects[position].name );
        email.setText((CharSequence)
                objects[position].email);

        return row;
    }

}