package com.datagenno.playground.services;

import com.datagenno.playground.models.Disease;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by juliobetta on 10/30/14.
 */
public interface DiseaseService {
    @GET("/diseases/show/{id}")
    Disease showDisease(@Path("id") int disease);
}
