package com.example.android.movielist.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DITHA on 23/08/2017.
 */

public class APIClient {
    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private static Retrofit mRetrofit;

    public static Retrofit getRetrofitClient(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }


}
