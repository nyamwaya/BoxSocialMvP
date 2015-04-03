package com.example.administrator.boxsocialmvp.Networking;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.administrator.boxsocialmvp.Objects.D;
import com.example.administrator.boxsocialmvp.Objects.Example;
import com.example.administrator.boxsocialmvp.Objects.Image;
import com.example.administrator.boxsocialmvp.Objects.Result;
import com.example.administrator.boxsocialmvp.R;
import com.example.administrator.boxsocialmvp.TvCard;
import com.example.administrator.boxsocialmvp.Utils.Base64;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

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
            Callback<Example> response);


}
