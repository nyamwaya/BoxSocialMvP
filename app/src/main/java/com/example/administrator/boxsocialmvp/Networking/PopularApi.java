package com.example.administrator.boxsocialmvp.Networking;

import com.example.administrator.boxsocialmvp.Objects.BoxSocialConstants;
import com.example.administrator.boxsocialmvp.Objects.TvShow;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 2/21/2015.
 */
public interface PopularApi {

    @GET("/tv/popular")
    public void getShows(
            @Query("api_key") String key,
            @Query("page") String pagecount,
            Callback<TvShow> response);

    @GET("/tv/top_rated")
    public void getTopShows(
            @Query("api_key") String key,
            @Query("page") String pagecount,
            Callback<TvShow> response);

    @GET("/tv/airing_today")
    public void getAiringShows(
            @Query("api_key") String key,
            @Query("page") String pagecount,
            Callback<TvShow> response);
    @GET("/tv/on_the_air")
    public void getOnTheAirShows(
            @Query("api_key") String key,
            @Query("page") String pagecount,
            Callback<TvShow> response);
}
