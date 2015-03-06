package com.example.administrator.boxsocialmvp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.boxsocialmvp.Adapters.SocialAdapter;
import com.example.administrator.boxsocialmvp.Objects.Socializer;
import com.example.administrator.boxsocialmvp.Utils.SlidingActivity;
import com.gc.materialdesign.views.ButtonFlat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import twitter4j.ConnectionLifeCycleListener;
import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;


/**
 * Created by Administrator on 2/27/2015.
 */
public class SocialActivity extends SlidingActivity {

    private RecyclerView recyclerview;
    private SocialAdapter adapter;
    private SharedPreferences pref;
    private Twitter twitter;
    private ConfigurationBuilder cb;
    private TvCard show;
    final List<Socializer> chatter = new ArrayList<>();
    private TwitterStream twitterStream;
    int i;
    StatusListener statusListener = new StatusListener() {

        @Override
        public void onStatus(Status status) {
            // The main section that you get the tweet. You can access it by status object.
            // You can save it in a database table.
            for( i=0; i<10; i++) {
                Log.e("STATUS COLLECTED: ", status.getText());
                Socializer socializer = new Socializer();
                socializer.setSocialNetwork("Twitter");
                socializer.setUsername(status.getUser().getName());
                socializer.setUserImg(status.getUser().getBiggerProfileImageURL());
                socializer.setStatusText(status.getText());
                socializer.setMediaImg(status.getMediaEntities()[0].getMediaURL());
                chatter.add(socializer);
            }

            if(i==10){
                i=0;
            }
            HashSet hs = new HashSet();
            hs.addAll(chatter);
            chatter.clear();
            chatter.addAll(hs);



            adapter.notifyDataSetChanged();




        }


        @Override
        public void onDeletionNotice(StatusDeletionNotice sdn) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void onTrackLimitationNotice(int i) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void onScrubGeo(long l, long l1) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void onStallWarning(StallWarning warning) {

        }


        @Override
        public void onException(Exception ex) {
            Log.e("OnException()", ex.toString());
        }
    };
    private long maxId = 0;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LocationManager locationManager;
    private Criteria criteria;
    private String provider;
    private MyLocationListener mylistener;
    private double lat;
    private double lon;
    private double rad = 25;
    private Query.Unit unit = Query.Unit.mi;
    private boolean locationIsFound = false;
    private GeoLocation geoLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

         cb = new ConfigurationBuilder();
        pref = getPreferences(0);
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("Z5BLZfUDtxpuW1OMBa4zEsptn")
                .setOAuthConsumerSecret("opLmxfZ2VCsqCNAu2Lqz0jBPj2Ls2sBJTQLchMkTrnaV3oXa1d")
                .setOAuthAccessToken("410560475-NNp0z7TEPjoYvw7jZdY0d4rE7eMEi7U2Cd87juL8")
                .setOAuthAccessTokenSecret("hotdQQNKDYmGyHrF2GygHR06phXaP0EF8sKXHlvwXPH0d");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_view);
        recyclerview = (RecyclerView) findViewById(R.id.social_list);
        show = (TvCard)getIntent().getExtras().getSerializable("show");

        ButtonFlat buttonFlat = (ButtonFlat)findViewById(R.id.back_btn);
        buttonFlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        getLatLong();

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        TextView title = (TextView)findViewById(R.id.show_name);
        title.setText(show.showTitle);
        adapter = new SocialAdapter(SocialActivity.this,show,chatter);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(SocialActivity.this));

        try {
            new AsyncTask<Void,Void,Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    searchTwitterverse();
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    adapter.notifyDataSetChanged();
                    super.onPostExecute(aVoid);
                }
            }.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new AsyncTask<Void,Void,Void>(){

                        @Override
                        protected Void doInBackground(Void... params) {
                            searchTwitterverse();
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
//                            adapter = new SocialAdapter(SocialActivity.this,show,chatter);
//                            recyclerview.setAdapter(adapter);
                            adapter.notifyItemInserted(0);
                            recyclerview.scrollToPosition(0);
                            mSwipeRefreshLayout.setRefreshing(false);
                            super.onPostExecute(aVoid);
                        }
                    }.execute();

                }
            });
        }


        
        
//        getTweetStreamForKeywords();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        getTweetStreamForKeywords();
    }

    private void getLatLong() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the location provider
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);   //default

        // user defines the criteria

        criteria.setCostAllowed(false);
        // get the best provider depending on the criteria
        provider = locationManager.getBestProvider(criteria, false);

        // the last known location of this provider
        Location location = locationManager.getLastKnownLocation(provider);
        if(location!=null){
        lat = location.getLatitude();
        lon = location.getLongitude();
        locationIsFound = true;
            geoLocation = new GeoLocation(lat,lon);
        }


        mylistener = new MyLocationListener();

        if (location != null) {
            mylistener.onLocationChanged(location);
        } else {
            // leads to the settings because there is no last known location
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    public void searchTwitterverse(){
        String abbr[] = show.showTitle.split("(?<=[\\S])[\\S]*\\s*");
        String lang[] = {"en"};
        String abbreviation = "";
        for (String letter : abbr) {
            abbreviation = abbreviation + letter.substring(0, 1);
        }

        String keywords[] = {show.showTitle.replaceAll("\\s+", ""),
                abbreviation.trim().replaceAll("\\s+", ""), show.showTitle};

        Calendar cal = Calendar.getInstance();

        String date = ""+cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
        Log.e("QueryDate : ",date);
        Query query =new Query("(#"+keywords[0]+")"
                + " OR (#"+keywords[1]+")"
                +" OR ("+keywords[2]+") since:"+date);
        query.setCount(25);
        query.setLang("en");

        Log.e("QueryMaxId = ",""+maxId);
        if(maxId!=0) {
            query.setSinceId(maxId);
        }

        try {
            QueryResult result = twitter.search(query);
            maxId = result.getMaxId();

            for(Status status: result.getTweets()){
                Log.e("STATUS COLLECTED: ", status.getText());
                Socializer socializer = new Socializer();
                socializer.setSocialNetwork("Twitter");
                socializer.setUsername(status.getUser().getName());
                socializer.setUserImg(status.getUser().getBiggerProfileImageURL());
                socializer.setStatusText(status.getText());
                socializer.setDateTime(status.getCreatedAt());


                try {
                    if(status.getMediaEntities().length>0) {
                        socializer.setMediaImg(status.getMediaEntities()[0].getMediaURL());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                chatter.add(socializer);

            }

            Collections.sort(chatter);
            Collections.reverse(chatter);

//            adapter.notifyDataSetChanged();


        } catch (TwitterException te) {
            te.printStackTrace();
        }
    }
    public void searchTwitterNearerverse(){
        String abbr[] = show.showTitle.split("(?<=[\\S])[\\S]*\\s*");
        String lang[] = {"en"};
        String abbreviation = "";
        for (String letter : abbr) {
            abbreviation = abbreviation + letter.substring(0, 1);
        }

        String keywords[] = {show.showTitle.replaceAll("\\s+", ""),
                abbreviation.trim().replaceAll("\\s+", ""), show.showTitle};

        Calendar cal = Calendar.getInstance();

        String date = ""+cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
        Log.e("QueryDate : ",date);
        Query query =new Query("(#"+keywords[0]+")"
                + " OR (#"+keywords[1]+")"
                +" OR ("+keywords[2]+") since:"+date);
        query.setCount(25);
        query.setLang("en");
        if(locationIsFound) {
            query.setGeoCode(geoLocation,rad,unit);
        }

        Log.e("QueryMaxId = ", "" + maxId);
        if(maxId!=0) {
            query.setSinceId(maxId);
        }

        try {
            QueryResult result = twitter.search(query);
            maxId = result.getMaxId();

            for(Status status: result.getTweets()){
                Log.e("STATUS COLLECTED: ", status.getText());
                Socializer socializer = new Socializer();
                socializer.setSocialNetwork("Twitter");
                socializer.setUsername(status.getUser().getName());
                socializer.setUserImg(status.getUser().getBiggerProfileImageURL());
                socializer.setStatusText(status.getText());
                socializer.setDateTime(status.getCreatedAt());


                try {
                    if(status.getMediaEntities().length>0) {
                        socializer.setMediaImg(status.getMediaEntities()[0].getMediaURL());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                chatter.add(socializer);

            }

            Collections.sort(chatter);
            Collections.reverse(chatter);

//            adapter.notifyDataSetChanged();


        } catch (TwitterException te) {
            te.printStackTrace();
        }
    }

    private void getTweetStreamForKeywords(){
        FilterQuery fq = new FilterQuery();
        String abbr[] = show.showTitle.split("(?<=[\\S])[\\S]*\\s*");
        String lang[] = {"en"};
        String abbreviation = "";
        for (String letter : abbr) {
            abbreviation = abbreviation + letter.substring(0, 1);
        }

        String keywords[] = {show.showTitle.replaceAll("\\s+", ""),
                abbreviation.trim().replaceAll("\\s+", ""), show.showTitle};
        Log.e("STATUS FILTER", keywords[0] + keywords[1] + keywords[2]);
        fq.track(keywords);
        fq.language(lang);


        if(twitterStream==null) {
            twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
            twitterStream.addListener(statusListener);
            twitterStream.filter(fq);
        }else{
            twitterStream.addListener(statusListener);
            twitterStream.filter(fq);
        }



    }

    @Override
    protected void onPause() {
        super.onPause();
//        twitterStream.cleanUp();
//        twitterStream.shutdown();
    }

    public static void loadPhoto(ImageView imageView, Context context) {


        AlertDialog.Builder imageDialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.custom_fullimage_dialog,null);
        ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
        image.setImageDrawable(imageView.getDrawable());
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        imageDialog.setView(layout);

        imageDialog.setPositiveButton(context.getResources().getString(R.string.ok_button), new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });


        imageDialog.create();
        imageDialog.show();
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // Initialize the location fields



            Toast.makeText(SocialActivity.this, "" + location.getLatitude() + location.getLongitude(),
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Toast.makeText(SocialActivity.this, provider + "'s status changed to "+status +"!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(SocialActivity.this, "Provider " + provider + " enabled!",
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(SocialActivity.this, "Provider " + provider + " disabled!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void searchNearMe(){
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected void onPreExecute() {
                chatter.clear();
                maxId =0;
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                searchTwitterNearerverse();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();

                super.onPostExecute(aVoid);
            }
        }.execute();
    }
    public void searchAll(){
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected void onPreExecute() {
                chatter.clear();
                maxId =0;
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                searchTwitterverse();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();

                super.onPostExecute(aVoid);
            }
        }.execute();
    }

}
