package com.merliti.searchdbapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class TrashAdapter extends ArrayAdapter<Trash> {
    private Activity activity;
    private ArrayList<Trash> trashList;
    private static LayoutInflater inflater = null;

    public TrashAdapter(@NonNull Activity activity, int resource, ArrayList<Trash> trashList) {
        super(activity, resource, trashList);
        try{
            this.activity = activity;
            this.trashList = trashList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e){

        }

    }
    public int getCount() {
        return trashList.size();
    }

    public Trash getItem(Trash position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView container;
        public TextView description;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.layout_item, null);
                holder = new ViewHolder();

                holder.name = (TextView) vi.findViewById(R.id.name);
                holder.container = (TextView) vi.findViewById(R.id.container);
                holder.description = (TextView) vi.findViewById(R.id.description);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }



            holder.name.setText(trashList.get(position).name);
            holder.container.setText(trashList.get(position).container);
            holder.description.setText(trashList.get(position).description);


        } catch (Exception e) {


        }
        return vi;
    }

}


