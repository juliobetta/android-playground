package com.datagenno.playground.services;

import com.datagenno.playground.models.Disease;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by juliobetta on 10/30/14.
 */
public interface DiseaseService {
    @GET("/diseases/{id}.json")
    void showDisease(@Path("id") String disease, Callback<Disease> callback);
}


