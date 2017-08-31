package com.example.android.movielist.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DITHA on 30/08/2017.
 */

public class APIClientTrailerMovie {

    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private static Retrofit mRetrofit;

    public static Retrofit getRetrofitClientTrailer(long id_movie){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL + id_movie + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
