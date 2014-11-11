package com.datagenno.playground.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.datagenno.playground.R;
import com.datagenno.playground.models.Group;
import com.datagenno.playground.models.Sign;

import java.util.ArrayList;

/**
 * Created by juliobetta on 11/10/14.
 */
public class SignAdapter extends ArrayAdapter<Object> {
    private ArrayList<Object> items;

    public SignAdapter(Context context, ArrayList<Object> items) {
        super(context, R.layout.activity_disease, items);
        this.items = items;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Object item    = items.get(position);
              TextView text = null;

        if(item != null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // if it's a Group, create a list section ...
            if(item instanceof Group) {
                view = inflater.inflate(R.layout.adapter_group, parent, false);
                text = (TextView) view.findViewById(R.id.group_name);

                // ... and remove click listeners
                view = removeClickListenersFrom(view);
            } else if (item instanceof Sign) {
                Sign sign = (Sign) item;
                     view = inflater.inflate(R.layout.adapter_sign, parent, false);
                     text = (TextView) view.findViewById(R.id.sign_name);

                if(sign.imagens != null) {
                    text.setTextColor(getContext().getResources().getColor(R.color.link_color));
                } else {
                    view = removeClickListenersFrom(view);
                }
            }
;
            text.setText(item.toString());
        }

        return view;
    }


    /**
     * Update list passing a list of items
     * @param items
     */
    public void update(ArrayList<Object> items) {
        clear();

        for (Object object : items) {
            this.add(object);
        }

        this.notifyDataSetChanged();
        this.notifyDataSetInvalidated();
    }


    /**
     * Remove click listeners from a view
     * @param view
     * @return
     */
    private View removeClickListenersFrom(View view) {
        view.setOnClickListener(null);
        view.setOnLongClickListener(null);
        view.setLongClickable(false);

        return view;
    }
}
