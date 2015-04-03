package com.example.administrator.boxsocialmvp.Objects;

import java.util.List;

/**
 * Created by Administrator on 3/26/2015.
 */
public class AllSportsToday {


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
        private List<Collection2> collection2;

        public List<Collection1> collection1;

        public List<Collection2> getCollection2 ()
        {
            return collection2;
        }

        public void setCollection2 (List<Collection2> collection2)
        {
            this.collection2 = collection2;
        }

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
            return "ClassPojo [collection2 = "+collection2+", collection1 = "+collection1+"]";
        }
    }
    public class Collection2
    {
        private String game_title;

        private String Network;

        private String start_time;

        private String sport_type;

        private String show_type;

        public String getGame_title ()
        {
            return game_title;
        }

        public void setGame_title (String game_title)
        {
            this.game_title = game_title;
        }

        public String getNetwork ()
        {
            return Network;
        }

        public void setNetwork (String Network)
        {
            this.Network = Network;
        }

        public String getStart_time ()
        {
            return start_time;
        }

        public void setStart_time (String start_time)
        {
            this.start_time = start_time;
        }

        public String getSport_type ()
        {
            return sport_type;
        }

        public void setSport_type (String sport_type)
        {
            this.sport_type = sport_type;
        }

        public String getShow_type ()
        {
            return show_type;
        }

        public void setShow_type (String show_type)
        {
            this.show_type = show_type;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [game_title = "+game_title+", Network = "+Network+", start_time = "+start_time+", sport_type = "+sport_type+", show_type = "+show_type+"]";
        }
    }

    public class Collection1
    {
        private String Date;

        public String getDate ()
        {
            return Date;
        }

        public void setDate (String Date)
        {
            this.Date = Date;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [Date = "+Date+"]";
        }
    }
}
