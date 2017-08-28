package com.example.android.movielist.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by DITHA on 23/08/2017.
 */

public class Utility {
    public static String URL_IMAGE = "https://image.tmdb.org/t/p/w500/";
    public static String overView;
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 120);
        return noOfColumns;
    }
}