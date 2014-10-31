package com.datagenno.playground.controllers;

import android.app.Activity;

import retrofit.RestAdapter;

/**
 * Created by juliobetta on 10/31/14.
 */
public abstract class Controller {
    private final String BASEPATH = "http://www.datagenno.com";
    protected RestAdapter rest;
    protected Activity activity;

    public Controller(Activity activity) {
        this.activity = activity;
        this.rest     = new RestAdapter.Builder().setEndpoint(BASEPATH).build();
    }

    public abstract void show(String path);
}
