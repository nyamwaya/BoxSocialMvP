package com.example.administrator.boxsocialmvp.Networking;

import com.example.administrator.boxsocialmvp.Objects.TvShow;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Administrator on 2/21/2015.
 */
public interface TvRageApiService {

    @GET("/bins/4ccjb")
    public void getShows(Callback<List<TvShow>> response);

}
