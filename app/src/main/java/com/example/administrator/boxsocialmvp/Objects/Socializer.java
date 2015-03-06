package com.example.administrator.boxsocialmvp.Objects;

import java.util.Date;

/**
 * Created by Administrator on 3/2/2015.
 */
public class Socializer implements Comparable<Socializer>  {
    String userImg;
    String socialNetwork;
    String username;
    String statusText;
    String mediaImg;
    private Date dateTime;


    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getMediaImg() {
        return mediaImg;
    }

    public void setMediaImg(String mediaImg) {
        this.mediaImg = mediaImg;
    }

    public String getSocialNetwork() {
        return socialNetwork;

    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }


    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date datetime) {
        this.dateTime = datetime;
    }

    @Override
    public int compareTo(Socializer another) {
        return getDateTime().compareTo(another.getDateTime());
    }
}
