package com.example.administrator.boxsocialmvp.Objects;

/**
 * Created by Administrator on 2/27/2015.
 */
public class BoxSocialConstants {

    private static final String airingToday = "/tv/airing_today";
    private static final String popular = "/tv/popular";
    private static final String top_rated = "/tv/top_rated";
    private static final String on_the_air = "/tv/on_the_air";

    private static String tvApiPath;

    public static void setTvApiPath(int category){
        switch (category){
            case 1:
                tvApiPath = popular;
                break;
            case 2:
                tvApiPath = top_rated;
                break;
            case 3:
                tvApiPath = on_the_air;
                break;
            case 4:
                tvApiPath = airingToday;
        }
    }
    public static String getTvApiPath(){
        return tvApiPath;
    }
}
