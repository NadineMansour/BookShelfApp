package com.example.nadinemansour.bookshelfapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by NadineMansour on 11/29/15.
 */
public class CommentAdapter extends ArrayAdapter<CommentData> {

    private Context context;
    private int resource;
    private CommentData[] objects;


    public CommentAdapter(Context context,
                           int resource,
                          CommentData[] objects) {
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

        TextView content= (TextView)
                row.findViewById(R.id.comment_content);
        TextView user=(TextView)
                row.findViewById(R.id.comment_user);




        content.setText((CharSequence)
                objects[position].content);
        user.setText((CharSequence)
                objects[position].user);
        return row;
    }
}
