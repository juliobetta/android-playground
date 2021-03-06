package com.datagenno.playground.controllers;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.datagenno.playground.R;
import com.datagenno.playground.adapters.SignAdapter;
import com.datagenno.playground.models.Group;
import com.datagenno.playground.models.Sign;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by juliobetta on 11/10/14.
 */
public class SignsController extends AbstractController {

    public SignsController(Activity activity) {
        super(activity);
    }


    /**
     * List disease's signs
     * @param signs
     */
    public void list(LinkedTreeMap signs) {

        SignAdapter signAdapter = new SignAdapter(activity, new ArrayList<Object>());
        ListView signsList      = (ListView) activity.findViewById(R.id.signsList);

        for(Object group : signs.entrySet()) {
            Map.Entry<String, ArrayList> groupSet = (Map.Entry) group;

            signAdapter.add(new Group(groupSet.getKey().toString()));

            for(Object sign : groupSet.getValue()) {
                LinkedTreeMap signObject = (LinkedTreeMap) sign;
                Sign signModel = new Sign(signObject.get("sinal").toString());

                Object images = signObject.get("imagens");
                signModel.setImages(images == null ? null : images.toString());

                Object videos = signObject.get("videos");
                signModel.setVideos(videos == null ? null : videos.toString());

                signAdapter.add(signModel);
            }
        }

        signsList.setAdapter(signAdapter);
        signsList.setOnItemClickListener(show());
    }


    /**
     * On Sign item
     * @return
     */
    private AdapterView.OnItemClickListener show() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                final Sign sign = (Sign) adapter.getItemAtPosition(pos);

                Toast.makeText(activity, sign.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
