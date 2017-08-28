package com.example.android.movielist.rest;

import com.example.android.movielist.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DITHA on 23/08/2017.
 */

public interface APIService {

    @GET("popular")
    Call<APIResponse> getTopMovie(
            @Query("api_key") String apiKey
    );

    @GET("now_playing")
    Call<APIResponse> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("top_rated")
    Call<APIResponse> getTopRated(
            @Query("api_key") String apiKey
    );

    @GET("upcoming")
    Call<APIResponse> getUpcoming(
            @Query("api_key") String apiKey
    );

    @GET("movie")
    Call<APIResponse> getMovie(
            @Query("api_key") String apiKey,
            @Query("year") String year,
            @Query("sort_by") String sortBy
    );
}
