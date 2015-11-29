package com.example.nadinemansour.bookshelfapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by NadineMansour on 11/25/15.
 */
public class RequestsAdapter
        extends ArrayAdapter<RequestsData> {

    private Context context;
    private int resource;
    private RequestsData[] objects;


    public RequestsAdapter(Context context,
                           int resource,
                           RequestsData[] objects) {
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
        View row=inflater.inflate(resource,parent,false);

        TextView user= (TextView)
                row.findViewById(R.id.user_name);


        user.setText((CharSequence)
                objects[position].username);

        return row;
    }

}