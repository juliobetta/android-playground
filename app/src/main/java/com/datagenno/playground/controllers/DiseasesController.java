package com.datagenno.playground.controllers;

import android.app.Activity;

import com.datagenno.playground.models.Disease;
import com.google.gson.internal.LinkedTreeMap;

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
    private interface API {
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
                SignsController signsController = new SignsController(activity);
                signsController.list((LinkedTreeMap) disease.signs);

                activity.getActionBar().setTitle(disease.name);

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