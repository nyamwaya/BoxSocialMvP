package com.example.administrator.boxsocialmvp.Networking;

import com.example.administrator.boxsocialmvp.Objects.TvShow;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 2/27/2015.
 */
public interface AiringToday extends PopularApi {
    @GET("/tv/airing_today")
    public void getShows(
            @Query("api_key") String key,
            @Query("page") String pagecount,
            Callback<TvShow> response);
}
