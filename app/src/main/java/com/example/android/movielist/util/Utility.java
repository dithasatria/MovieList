package com.example.android.movielist.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by DITHA on 23/08/2017.
 */

public class Utility {
    public static String URL_IMAGE = "https://image.tmdb.org/t/p/w500";
    public static String URL_IMAGE_YOUTUBE = "https://i.ytimg.com/vi/";
    public static String URL_YOUTUBE = "https://www.youtube.com/watch?v=";
    public static String IMAGE_QUALITY = "/mqdefault.jpg";
    public static String KEY_LANGUAGE = "en-US";
    public static long idMovie;
    public static String OVERVIEW;
    public static String KEY_INTENT = "dataMovie";
    public static String PRODUCTION_COMPANY;
    public static String PRODUCTION_COUNTRY;
    public static String RELEASE_DATE;
    public static String genre;

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 120);
        return noOfColumns;
    }
}