package com.example.administrator.boxsocialmvp.Objects;

import java.util.List;

/**
 * Created by Administrator on 2/21/2015.
 */
public class Image {

        private List<Items> items;

    public List<Items> getItems () {
            return items;
        }

    public void setItems (List<Items> items)
    {
        this.items = items;
    }



    @Override
    public String toString()
    {
        return "ClassPojo [items = "+items+"}";
    }

    public class Items
    {
        private String displayLink;

        private String title;

        private String link;

        private Image image;

        private String htmlTitle;

        private String mime;

        private String snippet;

        private String htmlSnippet;

        private String kind;

        public String getDisplayLink ()
        {
            return displayLink;
        }

        public void setDisplayLink (String displayLink)
        {
            this.displayLink = displayLink;
        }

        public String getTitle ()
        {
            return title;
        }

        public void setTitle (String title)
        {
            this.title = title;
        }

        public String getLink ()
        {
            return link;
        }

        public void setLink (String link)
        {
            this.link = link;
        }

        public Image getImage ()
        {
            return image;
        }

        public void setImage (Image image)
        {
            this.image = image;
        }

        public String getHtmlTitle ()
        {
            return htmlTitle;
        }

        public void setHtmlTitle (String htmlTitle)
        {
            this.htmlTitle = htmlTitle;
        }

        public String getMime ()
        {
            return mime;
        }

        public void setMime (String mime)
        {
            this.mime = mime;
        }

        public String getSnippet ()
        {
            return snippet;
        }

        public void setSnippet (String snippet)
        {
            this.snippet = snippet;
        }

        public String getHtmlSnippet ()
        {
            return htmlSnippet;
        }

        public void setHtmlSnippet (String htmlSnippet)
        {
            this.htmlSnippet = htmlSnippet;
        }

        public String getKind ()
        {
            return kind;
        }

        public void setKind (String kind)
        {
            this.kind = kind;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [displayLink = "+displayLink+", title = "+title+", link = "+link+", image = "+image+", htmlTitle = "+htmlTitle+", mime = "+mime+", snippet = "+snippet+", htmlSnippet = "+htmlSnippet+", kind = "+kind+"]";
        }

    }
}


