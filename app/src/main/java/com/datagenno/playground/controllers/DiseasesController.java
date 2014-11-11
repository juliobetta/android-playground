package com.datagenno.playground.controllers;

import android.app.Activity;

import com.datagenno.playground.AppController;
import com.datagenno.playground.models.Disease;
import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

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
        void showDisease(
            @Path("id") String disease,
            @QueryMap Map<String, String> params,
            Callback<Disease> callback
        );
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

        Map<String, String> params = new HashMap<String, String>();
        params.put("language", AppController.getLanguage());

        this.service.showDisease(path, params, new Callback<Disease>() {
            @Override
            public void success(Disease disease, Response response) {
                SignsController signsController = new SignsController(activity);
                signsController.list((LinkedTreeMap) disease.signs);
                progress.hide();
                progress.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                showFailureMessage(error.getMessage());
            }
        });
    }
}