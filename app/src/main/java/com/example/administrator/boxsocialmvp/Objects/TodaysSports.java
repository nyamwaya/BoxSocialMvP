package com.example.administrator.boxsocialmvp.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 3/22/2015.
 */
public class TodaysSports {

    @Expose
    private Results results;
    /**
     *
     * @return
     *     The results
     */
    public Results getResults() {
        return results;
    }

    /**
     *
     * @param results
     *     The results
     */
    public void setResults(Results results) {
        this.results = results;
    }


    public class Results {

        @Expose
        private List<Collection1> collection1 = new ArrayList<Collection1>();

        /**
         *
         * @return
         *     The collection1
         */
        public List<Collection1> getCollection1() {
            return collection1;
        }

        /**
         *
         * @param collection1
         *     The collection1
         */
        public void setCollection1(List<Collection1> collection1) {
            this.collection1 = collection1;
        }

    }


    public class Collection1 {

        @Expose
        private GameTitle gameTitle;
        @SerializedName("sport_image")
        @Expose
        private List<SportImage> sportImage = new ArrayList<SportImage>();
        @SerializedName("title_desc")
        @Expose
        private List<TitleDesc> titleDesc = new ArrayList<TitleDesc>();
        @Expose
        private List<String> description = new ArrayList<String>();
        @SerializedName("time_network")
        @Expose
        private List<String> timeNetwork = new ArrayList<String>();

        /**
         *
         * @return
         *     The gameTitle
         */
        public GameTitle getGameTitle() {
            return gameTitle;
        }

        /**
         *
         * @param gameTitle
         *     The gameTitle
         */
        public void setGameTitle(GameTitle gameTitle) {
            this.gameTitle = gameTitle;
        }

        /**
         *
         * @return
         *     The sportImage
         */
        public List<SportImage> getSportImage() {
            return sportImage;
        }

        /**
         *
         * @param sportImage
         *     The sport_image
         */
        public void setSportImage(List<SportImage> sportImage) {
            this.sportImage = sportImage;
        }

        /**
         *
         * @return
         *     The titleDesc
         */
        public List<TitleDesc> getTitleDesc() {
            return titleDesc;
        }

        /**
         *
         * @param titleDesc
         *     The title_desc
         */
        public void setTitleDesc(List<TitleDesc> titleDesc) {
            this.titleDesc = titleDesc;
        }

        /**
         *
         * @return
         *     The description
         */
        public List<String> getDescription() {
            return description;
        }

        /**
         *
         * @param description
         *     The description
         */
        public void setDescription(List<String> description) {
            this.description = description;
        }

        /**
         *
         * @return
         *     The timeNetwork
         */
        public List<String> getTimeNetwork() {
            return timeNetwork;
        }

        /**
         *
         * @param timeNetwork
         *     The time_network
         */
        public void setTimeNetwork(List<String> timeNetwork) {
            this.timeNetwork = timeNetwork;
        }

    }

    public class GameTitle {

        @Expose
        private String text;
        @Expose
        private String href;

        /**
         *
         * @return
         *     The text
         */
        public String getText() {
            return text;
        }

        /**
         *
         * @param text
         *     The text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         *
         * @return
         *     The href
         */
        public String getHref() {
            return href;
        }

        /**
         *
         * @param href
         *     The href
         */
        public void setHref(String href) {
            this.href = href;
        }

    }

    public class SportImage {

        @Expose
        private String text;
        @Expose
        private String href;
        @Expose
        private String src;
        @Expose
        private String alt;

        /**
         *
         * @return
         *     The text
         */
        public String getText() {
            return text;
        }

        /**
         *
         * @param text
         *     The text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         *
         * @return
         *     The href
         */
        public String getHref() {
            return href;
        }

        /**
         *
         * @param href
         *     The href
         */
        public void setHref(String href) {
            this.href = href;
        }

        /**
         *
         * @return
         *     The src
         */
        public String getSrc() {
            return src;
        }

        /**
         *
         * @param src
         *     The src
         */
        public void setSrc(String src) {
            this.src = src;
        }

        /**
         *
         * @return
         *     The alt
         */
        public String getAlt() {
            return alt;
        }

        /**
         *
         * @param alt
         *     The alt
         */
        public void setAlt(String alt) {
            this.alt = alt;
        }

    }

    public class TitleDesc {

        @Expose
        private String text;
        @Expose
        private String href;

        /**
         *
         * @return
         *     The text
         */
        public String getText() {
            return text;
        }

        /**
         *
         * @param text
         *     The text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         *
         * @return
         *     The href
         */
        public String getHref() {
            return href;
        }

        /**
         *
         * @param href
         *     The href
         */
        public void setHref(String href) {
            this.href = href;
        }

    }


}
