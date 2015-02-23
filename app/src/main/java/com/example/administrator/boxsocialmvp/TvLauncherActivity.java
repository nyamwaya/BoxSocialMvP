package com.example.administrator.boxsocialmvp;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import com.example.administrator.boxsocialmvp.Networking.ImageSearchApi;
import com.example.administrator.boxsocialmvp.Networking.TvRageApiService;
import com.example.administrator.boxsocialmvp.Objects.Image;
import com.example.administrator.boxsocialmvp.Objects.TvShow;
import com.google.api.client.googleapis.GoogleUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TvLauncherActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private RecyclerView rcview;

    public static final String SHOW_ENDPOINT = "https://api.myjson.com" ;
    public static final String GOOGLE_ENDPOINT = "https://www.googleapis.com" ;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private ShowAdapter adapter;
    final List<TvCard> data = new ArrayList<>();
    final List<Image> image = new ArrayList<>();

    public void requestImage(String imageSearch) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(GOOGLE_ENDPOINT)
                .build();
        ImageSearchApi searchApi = restAdapter.create(ImageSearchApi.class);
        searchApi.getImages(getResources().getString(R.string.custom_search_api_key),
                getResources().getString(R.string.custom_search_id),
                imageSearch,

        new Callback<List<Image>>() {
            @Override
            public void success(List<Image> images, Response response) {
                Image img = images.get(0);
                Image imgPojo = new Image();
                if(img.getLink()!=null && !img.getLink().equalsIgnoreCase("")) {
                    imgPojo.setLink(img.getLink());
                    TvLauncherActivity.this.image.add(imgPojo);

                }
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                Log.e("GOOGLE ERROR", error.getMessage());
            }
        });
    }

    public void requestData(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(SHOW_ENDPOINT)
                .build();
        TvRageApiService apiService = restAdapter.create(TvRageApiService.class);

        apiService.getShows(new Callback<List<TvShow>>() {
            @Override
            public void success(List<TvShow> tvShows, Response response) {
                for(int i=0; i<tvShows.size(); i++){
                    TvShow show = tvShows.get(i);
                    TvCard currentCard = new TvCard();

                    if(!show.getEpisodeTitle().equalsIgnoreCase("To be announced")) {
                        Log.e("ShowsDATA: ",show.getEpisodeTitle());
                        currentCard.showTitle = show.getEpisodeTitle();
                        currentCard.network = show.getLogoFilename();
                        currentCard.showTime = show.getListDateTime();
                        currentCard.chatter = "CHATTER";
                        data.add(currentCard);
                        requestImage(show.getEpisodeTitle()+" poster");
                    }

               }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }



    public static List<TvCard> getSampleData(){
        List<TvCard> sampleData = new ArrayList<>();
        String[] titles = {"Agents","Hannibal","Game of Thrones","Empire"};
        String[] network = {"ABC","NBC","HBO","CBS"};
        String[] times = {"7:00 PM EST","9:00 PM EST","8:00 PM EST","LIVE NOW"};
        String[] chatter = {"CHATTER","CHATTER","CHATTER","CHATTER"};
        String[] imgUrls = {"http://upload.wikimedia.org/wikipedia/en/0/09/Agents_of_SHIELD_season_1_poster.jpeg",
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_launcher);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        rcview = (RecyclerView) findViewById(R.id.show_list);
        try {
            requestData();
        } finally {
            adapter = new ShowAdapter(TvLauncherActivity.this,data,image);
            rcview.setAdapter(adapter);
            rcview.setLayoutManager(new LinearLayoutManager(TvLauncherActivity.this));

        }

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color=\"#ffffff\">" + mTitle + "</font>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2A94D6")));

    }


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
