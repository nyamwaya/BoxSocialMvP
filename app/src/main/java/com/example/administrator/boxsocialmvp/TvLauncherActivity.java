package com.example.administrator.boxsocialmvp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import com.example.administrator.boxsocialmvp.Networking.BingImageSearchApi;
import com.example.administrator.boxsocialmvp.Networking.ImageSearchApi;
import com.example.administrator.boxsocialmvp.Networking.NbaListingsApi;
import com.example.administrator.boxsocialmvp.Networking.PopularApi;
import com.example.administrator.boxsocialmvp.Networking.SportsTodayApi;
import com.example.administrator.boxsocialmvp.Objects.AllSportsToday;
import com.example.administrator.boxsocialmvp.Objects.BoxSocialConstants;
import com.example.administrator.boxsocialmvp.Objects.D;
import com.example.administrator.boxsocialmvp.Objects.Example;
import com.example.administrator.boxsocialmvp.Objects.Image;
import com.example.administrator.boxsocialmvp.Objects.NbaListings;
import com.example.administrator.boxsocialmvp.Objects.TodaysSports;
import com.example.administrator.boxsocialmvp.Objects.TvShow;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;


public class TvLauncherActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private final String TAG = this.getClass().getSimpleName();
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private RecyclerView rcview;

    public static final String SHOW_ENDPOINT = "http://api.themoviedb.org/3" ;
    public static final String KIMONO_ENDPOINT = "https://www.kimonolabs.com/api" ;
    public static final String GOOGLE_ENDPOINT = "https://www.googleapis.com" ;
    public static final String BING_ENDPOINT = "https://api.datamarket.azure.com" ;
    public static final String BING_USER_ID = "a07d9fe6-17f0-4e53-b993-31f3d767ccb9";

    private CharSequence mTitle;
    private ShowAdapter adapter;
    final List<TvCard> data = new ArrayList<>();
    final List<Image> image = new ArrayList<>();
    private int selectedItem = 1;
    private TextView titleTop;

    public void requestImage(String imageSearch, final TvCard currentCard) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(GOOGLE_ENDPOINT)
                .build();
        ImageSearchApi searchApi = restAdapter.create(ImageSearchApi.class);
        searchApi.getImages("image",
                getResources().getString(R.string.custom_search_android_api_key),
                getResources().getString(R.string.custom_search_id),
                imageSearch,

        new Callback<Image>() {
            @Override
            public void success(Image images, Response response) {


                   Image.Items item1 = images.getItems().get(1);
                   currentCard.previewImg = item1.getLink();

                   Image.Items item2 = images.getItems().get(2);
                   currentCard.bannerImg = item2.getLink();



            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();

                Log.e("GOOGLE ERROR", error.getMessage());

                try {
                    Log.e(TAG,error.toString());
                    Log.e(TAG,error.getUrl());
                    Log.e(TAG, error.getResponse().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void requestBingImage(String imageSearch, final TvCard currentCard) {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(BING_ENDPOINT)
                .setClient(new OkClient(new OkHttpClient()));

        final String credentials = getResources().getString(R.string.bing_search_key) + ":" + getResources().getString(R.string.bing_search_key);

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                // create Base64 encodet string
                String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                request.addHeader("Authorization", string);
            }
        });
        RestAdapter adapter = builder.build();
        BingImageSearchApi searchApi = adapter.create(BingImageSearchApi.class);
        searchApi.getImages("'"+imageSearch+"'","json",
                new Callback<Example>() {
                    @Override
                    public void success(Example example, Response response) {
                        Log.e(TAG,"Response: "+response.getReason());
                        Log.e(TAG,"Response Url: "+response.getUrl());
                        Log.e(TAG,"Response Status: "+response.getStatus());
                        Log.e(TAG,"Response Status: "+response.getHeaders());
                        try {
                            Log.v(TAG,"Bing Image "+example.getD().getResults().get(0).getMediaUrl());
                            currentCard.previewImg = example.getD().getResults().get(0).getMediaUrl();
                            currentCard.bannerImg = example.getD().getResults().get(1).getMediaUrl();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG,error.toString());
                        Log.e(TAG, error.getMessage());
                        Log.e(TAG, error.getUrl());
                        Log.e(TAG, error.getLocalizedMessage());
                        error.printStackTrace();
                    }
                });
    }

    public void getNbaListings(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(KIMONO_ENDPOINT)
                .build();
        NbaListingsApi nbaListingsApi = restAdapter.create(NbaListingsApi.class);
        nbaListingsApi.getBballListings(getResources().getString(R.string.nba_listings_api_key),
                new Callback<NbaListings>() {
                    @Override
                    public void success(NbaListings nbaListings, Response response) {
                        loopNbaData(nbaListings);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                        Log.e(TAG, error.toString());
                        Log.e(TAG, error.getMessage());
                        Log.e(TAG, error.getUrl());
                        Log.e(TAG, error.getLocalizedMessage());



                    }
                });
    }
    public void getTodaysSports(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(KIMONO_ENDPOINT)
                .build();
        SportsTodayApi sportsTodayApiApi = restAdapter.create(SportsTodayApi.class);
        sportsTodayApiApi.getTodaysSports(getResources().getString(R.string.nba_listings_api_key),
                new Callback<AllSportsToday>() {


                    @Override
                    public void success(AllSportsToday allSportsToday, Response response) {
                        loopSportsData(allSportsToday);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });
    }

    public void requestData(int i){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(SHOW_ENDPOINT)
                .build();

        PopularApi apiService = restAdapter.create(PopularApi.class);
        switch (i){
            case 0:

                apiService.getAiringShows(getResources().getString(R.string.imdb_key_id),
                        "1",
                        new Callback<TvShow>() {
                            @Override
                            public void success(TvShow tvShows, Response response) {
                                loopData(tvShows);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.e(TAG, error.toString());
                            }
                        });
                break;
            case 1:
                apiService.getTopShows(getResources().getString(R.string.imdb_key_id),
                        "1",
                        new Callback<TvShow>() {
                            @Override
                            public void success(TvShow tvShows, Response response) {
                                loopData(tvShows);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.e(TAG, error.toString());
                            }
                        });
                break;
            case 2:
                apiService.getOnTheAirShows(getResources().getString(R.string.imdb_key_id),
                        "1",
                        new Callback<TvShow>() {
                            @Override
                            public void success(TvShow tvShows, Response response) {
                                loopData(tvShows);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.e(TAG, error.toString());
                            }
                        });
                break;
            case 3:
                apiService.getShows(getResources().getString(R.string.imdb_key_id),
                        "1",
                        new Callback<TvShow>() {
                            @Override
                            public void success(TvShow tvShows, Response response) {
                                loopData(tvShows);

                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.e(TAG, error.toString());
                            }
                        });
                break;
            case 4:
                getTodaysSports();
                break;
            default:
                apiService.getShows(getResources().getString(R.string.imdb_key_id),
                        "1",
                        new Callback<TvShow>() {
                            @Override
                            public void success(TvShow tvShows, Response response) {
                                loopData(tvShows);
                            }
                            @Override
                            public void failure(RetrofitError error) {
                                Log.e(TAG, error.toString());
                            }
                        });
                break;

        }



    }

    private void loopData(TvShow tvShows) {
        data.clear();
        for(int i=0; i<tvShows.getResults().size(); i++){
            TvShow.Result show = tvShows.getResults().get(i);
                TvCard currentCard = new TvCard();
                Log.e("ShowsDATA: ", show.getName());
                currentCard.showTitle = show.getName();
                currentCard.chatter = "CHATTER";
                currentCard.previewImg = show.getPosterPath();
                currentCard.bannerImg = show.getBackdropPath();
                data.add(currentCard);

       }
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }
   private void loopNbaData(NbaListings nbaGames){
       data.clear();
       for(int i = 0; i<nbaGames.getResults().getCollection1().size(); i++){
          NbaListings.Collection1 nbaGame = nbaGames.getResults().getCollection1().get(i);
          TvCard currentCard = new TvCard();
           Log.e("ShowsDATA: ", nbaGame.getGame().getText());
           currentCard.showTitle  = nbaGame.getDescription();
           currentCard.chatter = "CHATTER";
           currentCard.network = nbaGame.getNetwork();
           currentCard.showTime = nbaGame.getDay()+" "+nbaGame.getDate()+" "+nbaGame.getShow_time();
           try {
               requestImage(nbaGame.getGame().getText().replace("NBA Basketball: ",""),currentCard);

           } finally {
               data.add(currentCard);

           }
       }
       if(adapter!=null){
           adapter.notifyDataSetChanged();
       }
   }

   private void loopSportsData(AllSportsToday todaysSports){
       data.clear();
       for(int i = 0; i<todaysSports.getResults().getCollection2().size(); i++){
           AllSportsToday.Collection2 sport = todaysSports.getResults().getCollection2().get(i);


          TvCard currentCard = new TvCard();
           if(sport.getSport_type().equalsIgnoreCase("NBA Basketball")||
                   sport.getSport_type().equalsIgnoreCase("MLB Preseason Baseball")||
                   sport.getSport_type().equalsIgnoreCase("NHL Hockey")||
                   sport.getSport_type().equalsIgnoreCase("UEFA Europa League Soccer")||
                   sport.getSport_type().equalsIgnoreCase("English Premier League Soccer")||
                   sport.getSport_type().equalsIgnoreCase("2015 NCAA Basketball Tournament")
                   ) {
               if(sport.getShow_type().toLowerCase().contains("live")) {
                   Log.e("ShowsDATA: ", sport.getGame_title());
                   currentCard.showTitle = sport.getGame_title();
                   currentCard.chatter = "CHATTER";
                   currentCard.network = sport.getNetwork()+" - "+sport.getSport_type();
                   currentCard.showTime = sport.getStart_time() + " " + sport.getShow_type();


                   try {
                       requestBingImage(sport.getGame_title(), currentCard);
                   } finally {
                       data.add(currentCard);

                   }
               }

           }


       }
           long seed = System.nanoTime();
           Collections.shuffle(data, new Random(seed));
           if (adapter != null) {
               adapter.notifyDataSetChanged();
           }

   }

    public static List<TvCard> getSampleData(){
        List<TvCard> sampleData = new ArrayList<>();
        String[] titles = {"Agents","Hannibal","Game of Thrones","Empire"};
        String[] network = {"ABC","NBC","HBO","CBS"};
        String[] times = {"7:00 PM EST","9:00 PM EST","8:00 PM EST","LIVE NOW"};
        String[] chatter = {"CHATTER","CHATTER","CHATTER","CHATTER"};
        String[] imgUrls = {"http://upload.wikimedia.org/wikipedia/en/0/ 09/Agents_of_SHIELD_season_1_poster.jpeg",
                "http://upload.wikimedia.org/wikipedia/en/3/30/Hannibal_Season_2_promtional_poster.jpg",
                "http://ia.media-imdb.com/images/M/MV5BMTk0NDg4NjQ5N15BMl5BanBnXkFtZTgwMzkzNTgyMTE@._V1_SX214_AL_.jpg",
                "http://d236bkdxj385sg.cloudfront.net/wp-content/uploads/2015/01/empire-ad.jpg"};
        for(int i=0; i<titles.length; i++){
            TvCard currentCard = new TvCard();
            currentCard.showTitle = titles[i];
            currentCard.network = network[i];
            currentCard.showTime = times[i];
            currentCard.chatter = chatter[i];
            currentCard.previewImg = imgUrls[i];
            sampleData.add(currentCard);
        }
        return sampleData;

    }

    SharedPreferences pref;
    private static String CONSUMER_KEY = "Z5BLZfUDtxpuW1OMBa4zEsptn";
    private static String CONSUMER_SECRET = "opLmxfZ2VCsqCNAu2Lqz0jBPj2Ls2sBJTQLchMkTrnaV3oXa1d";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = getPreferences(0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("CONSUMER_KEY", CONSUMER_KEY);
        edit.putString("CONSUMER_SECRET", CONSUMER_SECRET);
        edit.apply();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_launcher);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        rcview = (RecyclerView) findViewById(R.id.show_list);
        try {
            requestData(selectedItem);
        } finally {
            adapter = new ShowAdapter(TvLauncherActivity.this,data);
            rcview.setAdapter(adapter);
            rcview.setLayoutManager(new LinearLayoutManager(TvLauncherActivity.this));

        }

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(mTitle);

        titleTop = (TextView) findViewById(R.id.title_text);
        titleTop.setText(mTitle);

        setSupportActionBar(mToolbar);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,  mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        selectedItem = position;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position))
                .commit();
    }

    public void onSectionAttached(int number) {
        BoxSocialConstants.setTvApiPath(number);
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section1);
                titleTop.setText(mTitle);
                requestData(number);


                break;
            case 1:
                mTitle = getString(R.string.title_section2);
                titleTop.setText(mTitle);

                requestData(number);



                break;
            case 2:
                mTitle = getString(R.string.title_section3);
                titleTop.setText(mTitle);

                requestData(number);

                break;
            case 3:
                mTitle = getString(R.string.title_section4);
                titleTop.setText(mTitle);

                requestData(number);

                break;
            case 4:
                mTitle = getString(R.string.title_section5);
                titleTop.setText(mTitle);

                requestData(number);

                break;

        }
    }

//    public void restoreActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setTitle(Html.fromHtml("<font color=\"#ffffff\">" + mTitle + "</font>"));
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2A94D6")));
//
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.tv_launcher, menu);
//            restoreActionBar();
//            return true;
//        }
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tv_launcher, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((TvLauncherActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
