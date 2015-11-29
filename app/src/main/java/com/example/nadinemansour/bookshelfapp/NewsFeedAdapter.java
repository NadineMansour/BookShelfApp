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
public class NewsFeedAdapter
        extends ArrayAdapter<NewsFeedData> {

    private Context context;
    private int resource;
    private NewsFeedData[] objects;


    public NewsFeedAdapter(Context context,
                     int resource,
                           NewsFeedData[] objects) {
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

        TextView header= (TextView)
                row.findViewById(R.id.header);
        TextView content=(TextView)
                row.findViewById(R.id.content);
        TextView footer=(TextView)
                row.findViewById(R.id.footer);


        header.setText((CharSequence)
                objects[position].type +" by "+objects[position].user1 + " on " + objects[position].user2 +"'s timeline");
        content.setText((CharSequence)
                objects[position].content);
        footer.setText((CharSequence)
                objects[position].book + " - "+
                objects[position].author);
        return row;
    }




}

