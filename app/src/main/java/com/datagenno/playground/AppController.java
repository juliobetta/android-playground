package com.datagenno.playground;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.activeandroid.ActiveAndroid;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

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
        initImageLoader(getApplicationContext());
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


    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
