package com.datagenno.playground.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.activeandroid.Model;
import com.datagenno.playground.R;
import com.datagenno.playground.models.Group;
import com.datagenno.playground.models.Sign;

import java.util.ArrayList;

/**
 * Created by juliobetta on 11/10/14.
 */
public class SignAdapter extends ArrayAdapter<Model> {
    private ArrayList<Model> items;

    public SignAdapter(Context context, ArrayList<Model> items) {
        super(context, R.layout.activity_disease, items);
        this.items = items;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Model item = items.get(position);

        if(item != null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            int textId = 0;

            // if it's a Group, create a list section ...
            if(item instanceof Group) {
                view   = inflater.inflate(R.layout.adapter_group, parent, false);
                textId = R.id.group_name;

                // ... and remove click listeners
                view.setOnClickListener(null);
                view.setOnLongClickListener(null);
                view.setLongClickable(false);

            } else if (item instanceof Sign) {
                view = inflater.inflate(R.layout.adapter_sign, parent, false);
                textId = R.id.sign_name;
            }

            final TextView text = (TextView) view.findViewById(textId);
            text.setText(item.toString());
        }

        return view;
    }


    /**
     * Update list passing a list of items
     * @param items
     */
    public void update(ArrayList<Model> items) {
        clear();

        for (Model object : items) {
            this.add(object);
        }

        this.notifyDataSetChanged();
        this.notifyDataSetInvalidated();
    }
}
