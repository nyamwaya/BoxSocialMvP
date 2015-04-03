package com.example.administrator.boxsocialmvp.Networking;

import com.example.administrator.boxsocialmvp.Objects.NbaListings;
import com.example.administrator.boxsocialmvp.Objects.TvShow;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 3/22/2015.
 */
public interface NbaListingsApi {

    @GET("/e7c6345o")
    public void getBballListings(
    @Query("apikey") String key,
    Callback<NbaListings> response);
}
