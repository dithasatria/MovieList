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

    @GET("movie/popular")
    Call<APIResponse> getTopMovie(
            @Query("api_key") String apiKey
    );

    @GET("movie/now_playing")
    Call<APIResponse> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("movie/top_rated")
    Call<APIResponse> getTopRated(
            @Query("api_key") String apiKey
    );

    @GET("movie/upcoming")
    Call<APIResponse> getUpcoming(
            @Query("api_key") String apiKey
    );

    @GET("discover/movie")
    Call<APIResponse> getMovie(
            @Query("api_key") String apiKey,
            @Query("year") String year,
            @Query("sort_by") String sortBy
    );

    @GET("movie/{movie_id}")
    Call<APIResponseDetailMovie> getMovieId(
            @Path("movie_id") long id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/videos")
    Call<APIResponseTrailerMovie> getTrailerMovie(
            @Path("movie_id") long id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/reviews")
    Call<APIResponseReview> getReviewMovie(
            @Path("movie_id") long id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/credits")
    Call<APIResponseCast> getCast(
            @Path("movie_id") long id,
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("search/movie")
    Call<APIResponse> getDataMovie(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );
}
