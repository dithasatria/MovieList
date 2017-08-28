package com.example.android.movielist.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DITHA on 26/08/2017.
 */

public class APIClientDiscover {

    private static final String BASE_URL = "http://api.themoviedb.org/3/discover/";
    private static Retrofit mRetrofit;

    public static Retrofit getRetrofitClientDiscover(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
