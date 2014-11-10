package com.datagenno.playground.controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.widget.Toast;

import com.datagenno.playground.R;

import retrofit.RestAdapter;

/**
 * Created by juliobetta on 10/31/14.
 */
public abstract class AbstractController {
    private final String BASEPATH = "http://www.datagenno.com";
    protected RestAdapter rest;
    protected final Activity activity;
    protected final ProgressDialog progress;

    public AbstractController() {
        this.activity = null;
        this.progress = null;
    }

    public AbstractController(Activity activity) {
        this.activity = activity;
        this.rest     = new RestAdapter.Builder().setEndpoint(BASEPATH).build();
        this.progress = new ProgressDialog(this.activity);

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
