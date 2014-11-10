package com.datagenno.playground;

import android.app.Application;
import com.activeandroid.ActiveAndroid;

/**
 * Created by juliobetta on 11/10/14.
 * @see <a href="http://rominirani.com/android-application-class/">Android Application Class</a>
 */
public class ApplicationController extends Application {
    private static ApplicationController instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        ActiveAndroid.initialize(this);
    }


    public static synchronized ApplicationController getInstance() {
        return instance;
    }
}
