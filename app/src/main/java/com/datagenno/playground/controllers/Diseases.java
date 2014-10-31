package com.datagenno.playground.controllers;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.datagenno.playground.R;
import com.datagenno.playground.models.Disease;
import com.datagenno.playground.services.DiseaseService;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by juliobetta on 10/31/14.
 */
public class Diseases extends Controller {

    private DiseaseService service;


    public Diseases(Activity activity) {
        super(activity);

        this.service = this.rest.create(DiseaseService.class);
    }


    public void show(String path) {
        final EditText responseText = (EditText) activity.findViewById(R.id.reponseText);

        this.service.showDisease(path, new Callback<Disease>() {
            @Override
            public void success(Disease disease, Response response) {
                // android.os.Debug.waitForDebugger();

                LinkedTreeMap signs = (LinkedTreeMap) disease.signs;

                for(Object group : signs.entrySet()) {
                    Map.Entry<String, ArrayList> groupSet = (Map.Entry) group;

                    // prints out the group name
                    responseText.append(groupSet.getKey() + "\n");

                    for(Object sign : groupSet.getValue()) {
                        LinkedTreeMap signObject = (LinkedTreeMap) sign;

                        // prints out the sign name
                        responseText.append("\t" + signObject.get("sinal").toString() + "\n");
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(activity, error.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
