package com.datagenno.playground;

import android.app.Application;
import android.text.TextUtils;

import com.activeandroid.ActiveAndroid;

import java.util.Locale;

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


    /**
     * Get system's language
     * @return
     */
    public static String getLanguage() {
        String locale = Locale.getDefault().getLanguage();

        locale = TextUtils.equals(locale, "pt") ? "pt_br" : locale;

        return locale;
    }
}
