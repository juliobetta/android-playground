package com.datagenno.playground.controllers;

import android.app.Application;
import com.activeandroid.ActiveAndroid;

/**
 * Created by juliobetta on 11/10/14.
 * @see <a href="http://rominirani.com/android-application-class/">Android Application Class</a>
 */
public class AppController extends Application {
    private static AppController instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        ActiveAndroid.initialize(this);
    }


    public static synchronized AppController getInstance() {
        return instance;
    }
}
