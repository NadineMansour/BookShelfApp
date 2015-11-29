package com.example.nadinemansour.bookshelfapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by NadineMansour on 11/29/15.
 */
public class SearchAdapter extends ArrayAdapter {

    public static final int TYPE_FRIENDS = 0;
    public static final int TYPE_REQUEST = 1;
    public static final int TYPE_PENDING = 2;
    public static final int TYPE_NOTFRIENDS = 3;

    private SearchResultData[] objects;

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return objects[position].getType();
    }

    public SearchAdapter(Context context, int resource, SearchResultData[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SearchViewHolder viewHolder = null;
        SearchResultData listViewItem = objects[position];
        int listViewItemType = getItemViewType(position);


        if (convertView == null) {

            if (listViewItemType == TYPE_FRIENDS) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.friends_list_item, null);
            } else if (listViewItemType == TYPE_REQUEST) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.requests_list_item, null);
            } else if (listViewItemType == TYPE_PENDING) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.pending, null);
            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.not_friends, null);
            }

            TextView user = (TextView) convertView.findViewById(R.id.user_name);
            TextView email = (TextView) convertView.findViewById(R.id.email);
            viewHolder = new SearchViewHolder(user , email);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (SearchViewHolder) convertView.getTag();
        }

        viewHolder.user_name.setText(listViewItem.user_name);
        viewHolder.email.setText( listViewItem.email);

        return convertView;
    }

}
