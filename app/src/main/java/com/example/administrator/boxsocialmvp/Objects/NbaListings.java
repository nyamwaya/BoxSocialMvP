package com.example.administrator.boxsocialmvp.Objects;

import java.util.List;

/**
 * Created by Administrator on 3/22/2015.
 */
public class NbaListings {
    private String nextrun;

    private String newdata;

    private String thisversionrun;

    private Results results;

    private String count;

    private String name;

    private String lastrunstatus;

    private String frequency;

    private String lastsuccess;

    private String thisversionstatus;

    private String version;

    public String getNextrun ()
    {
        return nextrun;
    }

    public void setNextrun (String nextrun)
    {
        this.nextrun = nextrun;
    }

    public String getNewdata ()
    {
        return newdata;
    }

    public void setNewdata (String newdata)
    {
        this.newdata = newdata;
    }

    public String getThisversionrun ()
    {
        return thisversionrun;
    }

    public void setThisversionrun (String thisversionrun)
    {
        this.thisversionrun = thisversionrun;
    }

    public Results getResults ()
    {
        return results;
    }

    public void setResults (Results results)
    {
        this.results = results;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLastrunstatus ()
    {
        return lastrunstatus;
    }

    public void setLastrunstatus (String lastrunstatus)
    {
        this.lastrunstatus = lastrunstatus;
    }

    public String getFrequency ()
    {
        return frequency;
    }

    public void setFrequency (String frequency)
    {
        this.frequency = frequency;
    }

    public String getLastsuccess ()
    {
        return lastsuccess;
    }

    public void setLastsuccess (String lastsuccess)
    {
        this.lastsuccess = lastsuccess;
    }

    public String getThisversionstatus ()
    {
        return thisversionstatus;
    }

    public void setThisversionstatus (String thisversionstatus)
    {
        this.thisversionstatus = thisversionstatus;
    }

    public String getVersion ()
    {
        return version;
    }

    public void setVersion (String version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [nextrun = "+nextrun+", newdata = "+newdata+", thisversionrun = "+thisversionrun+", results = "+results+", count = "+count+", name = "+name+", lastrunstatus = "+lastrunstatus+", frequency = "+frequency+", lastsuccess = "+lastsuccess+", thisversionstatus = "+thisversionstatus+", version = "+version+"]";
    }


    public class Results
    {

        public List<Collection1> collection1;

        public List<Collection1> getCollection1 ()
        {
            return collection1;
        }

        public void setCollection1 (List<Collection1> collection1)
        {
            this.collection1 = collection1;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [collection1 = "+collection1+"]";
        }

    }


    public class Collection1
    {
        private String description;

        private String day;

        private Game game;

        private String date;

        private String show_time;

        private String network;

        public String getDescription ()
        {
            return description;
        }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getDay ()
        {
            return day;
        }

        public void setDay (String day)
        {
            this.day = day;
        }

        public Game getGame ()
        {
            return game;
        }

        public void setGame (Game game)
        {
            this.game = game;
        }

        public String getDate ()
        {
            return date;
        }

        public void setDate (String date)
        {
            this.date = date;
        }

        public String getShow_time ()
        {
            return show_time;
        }

        public void setShow_time (String show_time)
        {
            this.show_time = show_time;
        }

        public String getNetwork ()
        {
            return network;
        }

        public void setNetwork (String network)
        {
            this.network = network;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [description = "+description+", day = "+day+", game = "+game+", date = "+date+", show_time = "+show_time+", network = "+network+"]";
        }
    }



    public class Game
    {
        private String text;

        private String href;

        public String getText ()
        {
            return text;
        }

        public void setText (String text)
        {
            this.text = text;
        }

        public String getHref ()
        {
            return href;
        }

        public void setHref (String href)
        {
            this.href = href;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [text = "+text+", href = "+href+"]";
        }
    }


}