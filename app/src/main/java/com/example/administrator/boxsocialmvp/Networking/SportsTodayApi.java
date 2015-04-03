package com.example.administrator.boxsocialmvp.Networking;

import com.example.administrator.boxsocialmvp.Objects.AllSportsToday;
import com.example.administrator.boxsocialmvp.Objects.NbaListings;
import com.example.administrator.boxsocialmvp.Objects.TodaysSports;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 3/22/2015.
 */
public interface SportsTodayApi {


    @GET("/aoyxqfqm")
    public void getTodaysSports(
            @Query("apikey") String key,
            Callback<AllSportsToday> response);



}
