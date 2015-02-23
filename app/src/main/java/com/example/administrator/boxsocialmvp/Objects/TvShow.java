package com.example.administrator.boxsocialmvp.Objects;

/**
 * Created by Administrator on 2/21/2015.
 */
public class TvShow {
    private String logoFilename;
    private String listDateTime;
    private String showName;
    private Boolean live;
    private String description;
    private Boolean inProgress;
    private String episodeTitle;

    public String getLogoFilename() {
        return logoFilename;
    }

    public void setLogoFilename(String logoFilename) {
        this.logoFilename = logoFilename;
    }

    public String getListDateTime() {
        return listDateTime;
    }

    public void setListDateTime(String listDateTime) {
        this.listDateTime = listDateTime;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Boolean getLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }
}
