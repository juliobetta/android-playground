package com.datagenno.playground.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by juliobetta on 11/11/14.
 */
public class Constants {
    public static final String BASE_PATH = "http://www.datagenno.com/";

    public static final DisplayImageOptions IMAGE_OPTIONS = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .considerExifParams(true)
        .bitmapConfig(Bitmap.Config.RGB_565)
        .build();
}
