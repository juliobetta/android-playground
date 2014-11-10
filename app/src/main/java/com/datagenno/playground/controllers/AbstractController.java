package com.datagenno.playground.controllers;

import android.app.Activity;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.datagenno.playground.R;
import com.datagenno.playground.ui.CustomProgressDialog;

import retrofit.RestAdapter;

/**
 * Created by juliobetta on 10/31/14.
 */
public abstract class AbstractController {
    protected final String BASEPATH = "http://www.datagenno.com";
    protected RestAdapter rest;
    protected final Activity activity;
    protected final CustomProgressDialog progress;

    public AbstractController(Activity activity) {
        this.activity = activity;
        this.rest     = new RestAdapter.Builder().setEndpoint(BASEPATH).build();
        this.progress = new CustomProgressDialog(this.activity);

        this.progress.setMessage(activity.getString(R.string.loading));
    }

    public abstract void show(String path);

    /**
     * Show failure message and finish current activity
     */
    protected Response.ErrorListener onResponseError() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.hideAndDismiss(); // to avoid activity leaking

                Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        activity.finish();
                    }
                }, 2000);
            }
        };
    }
}
