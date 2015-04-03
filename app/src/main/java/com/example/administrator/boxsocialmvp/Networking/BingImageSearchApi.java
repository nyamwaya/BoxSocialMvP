package com.example.administrator.boxsocialmvp.Networking;

import com.example.administrator.boxsocialmvp.Objects.ClassD;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 3/26/2015.
 */
public interface  BingImageSearchApi  {

    @GET("/Bing/Search/Image")
    public void getImages(
            @Query("Query") String searchQuery,
            @Query("$format") String format,
            Callback<ClassD> response);


}
