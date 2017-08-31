package com.example.android.movielist.rest;

import com.example.android.movielist.model.APIResponse;
import com.example.android.movielist.model.APIResponseDetailMovie;
import com.example.android.movielist.model.cast.APIResponseCast;
import com.example.android.movielist.model.review.APIResponseReview;
import com.example.android.movielist.model.trailer.APIResponseTrailerMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("{movie_id}")
    Call<APIResponseDetailMovie> getMovieId(
            @Path("movie_id") long id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("videos")
    Call<APIResponseTrailerMovie> getTrailerMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("reviews")
    Call<APIResponseReview> getReviewMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("credits")
    Call<APIResponseCast> getCast(
            @Query("api_key") String api_key,
            @Query("language") String language
    );
}
