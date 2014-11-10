package com.datagenno.playground.controllers;

import android.app.Activity;
import android.widget.TextView;

import com.datagenno.playground.R;
import com.datagenno.playground.models.Disease;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by juliobetta on 10/31/14.
 */
public class DiseasesController extends AbstractController {

    private API service;


    /**
     * Retrofit API
     */
    public interface API {
        @GET("/diseases/{id}.json")
        void showDisease(@Path("id") String disease, Callback<Disease> callback);
    }


    /**
     * Constructor
     * @param activity
     */
    public DiseasesController(Activity activity) {
        super(activity);
        this.service = this.rest.create(API.class);
    }


    /**
     * Show disease
     * @param path
     */
    public void show(String path) {
        progress.show();

        this.service.showDisease(path, new Callback<Disease>() {
            @Override
            public void success(Disease disease, Response response) {
                listSigns(disease);
                progress.hide();
                progress.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                showFailureMessage(error.getMessage());
            }
        });
    }


    /**
     * List disease's signs
     * @param disease
     */
    private void listSigns(Disease disease) {
        TextView responseText = (TextView) activity.findViewById(R.id.signs);
        LinkedTreeMap signs   = (LinkedTreeMap) disease.signs;

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
}
