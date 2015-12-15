package com.example.nadinemansour.bookshelfapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by NadineMansour on 11/29/15.
 */
public class UserAdapter extends ArrayAdapter<UserData> {

    private Context context;
    private int resource;
    private UserData[] objects;


    public UserAdapter(Context context,
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

        TextView user_name= (TextView)
                row.findViewById(R.id.name);
        TextView email=(TextView)
                row.findViewById(R.id.email);
        ImageView picture = (ImageView)row.findViewById(R.id.picture);




        user_name.setText((CharSequence)
                objects[position].name);
        email.setText((CharSequence)
                objects[position].email);
        Picasso.with(inflater.getContext()).load("http://brentcarnduff.com/wp-content/uploads/2014/08/url-small.jpg").into(picture);
        return row;
    }
}
