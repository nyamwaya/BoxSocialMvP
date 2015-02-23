package com.example.administrator.boxsocialmvp.Networking;

import com.example.administrator.boxsocialmvp.Objects.Image;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Administrator on 2/21/2015.
 */
public interface ImageSearchApi {


    @GET("/customsearch/v1?")
    public void getImages(
            @Query("key") String key,
            @Query("cx") String customId,
            @Query("q") String search,
            Callback<List<Image>> response);
}
