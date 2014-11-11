package com.datagenno.playground.controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.widget.Toast;

import com.datagenno.playground.AppController;
import com.datagenno.playground.R;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by juliobetta on 10/31/14.
 */
public abstract class AbstractController {
    private static final String BASEPATH = "http://www.datagenno.com";
    protected RestAdapter rest;
    protected final Activity activity;
    protected final ProgressDialog progress;


    /**
     * Add language param to each request
     */
    private static final RequestInterceptor COOKIES_REQUEST_INTERCEPTOR = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            request.addQueryParam("language", AppController.getLanguage());
        }
    };


    public AbstractController() {
        this.activity = null;
        this.progress = null;
    }

    public AbstractController(Activity activity) {
        this.activity = activity;
        this.progress = new ProgressDialog(this.activity);

        this.rest = new RestAdapter.Builder()
            .setEndpoint(BASEPATH)
            .setRequestInterceptor(COOKIES_REQUEST_INTERCEPTOR)
            .build();

        this.progress.setMessage(activity.getString(R.string.loading));
    }


    /**
     * Show failure message and finish current activity
     * @param message
     */
    protected void showFailureMessage(String message) {
        this.progress.hide();
        this.progress.dismiss();

        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.finish();
            }
        }, 2000);
    }
}
