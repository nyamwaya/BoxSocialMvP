
package com.example.administrator.boxsocialmvp.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("__metadata")
    @Expose
    private com.example.administrator.boxsocialmvp.Objects.Metadata Metadata;
    @Expose
    private String ID;
    @Expose
    private String Title;
    @Expose
    private String MediaUrl;
    @Expose
    private String SourceUrl;
    @Expose
    private String DisplayUrl;
    @Expose
    private String Width;
    @Expose
    private String Height;
    @Expose
    private String FileSize;
    @Expose
    private String ContentType;
    @Expose
    private com.example.administrator.boxsocialmvp.Objects.Thumbnail Thumbnail;

    /**
     * 
     * @return
     *     The Metadata
     */
    public com.example.administrator.boxsocialmvp.Objects.Metadata getMetadata() {
        return Metadata;
    }

    /**
     * 
     * @param Metadata
     *     The __metadata
     */
    public void setMetadata(com.example.administrator.boxsocialmvp.Objects.Metadata Metadata) {
        this.Metadata = Metadata;
    }

    /**
     * 
     * @return
     *     The ID
     */
    public String getID() {
        return ID;
    }

    /**
     * 
     * @param ID
     *     The ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * 
     * @return
     *     The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * 
     * @param Title
     *     The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * 
     * @return
     *     The MediaUrl
     */
    public String getMediaUrl() {
        return MediaUrl;
    }

    /**
     * 
     * @param MediaUrl
     *     The MediaUrl
     */
    public void setMediaUrl(String MediaUrl) {
        this.MediaUrl = MediaUrl;
    }

    /**
     * 
     * @return
     *     The SourceUrl
     */
    public String getSourceUrl() {
        return SourceUrl;
    }

    /**
     * 
     * @param SourceUrl
     *     The SourceUrl
     */
    public void setSourceUrl(String SourceUrl) {
        this.SourceUrl = SourceUrl;
    }

    /**
     * 
     * @return
     *     The DisplayUrl
     */
    public String getDisplayUrl() {
        return DisplayUrl;
    }

    /**
     * 
     * @param DisplayUrl
     *     The DisplayUrl
     */
    public void setDisplayUrl(String DisplayUrl) {
        this.DisplayUrl = DisplayUrl;
    }

    /**
     * 
     * @return
     *     The Width
     */
    public String getWidth() {
        return Width;
    }

    /**
     * 
     * @param Width
     *     The Width
     */
    public void setWidth(String Width) {
        this.Width = Width;
    }

    /**
     * 
     * @return
     *     The Height
     */
    public String getHeight() {
        return Height;
    }

    /**
     * 
     * @param Height
     *     The Height
     */
    public void setHeight(String Height) {
        this.Height = Height;
    }

    /**
     * 
     * @return
     *     The FileSize
     */
    public String getFileSize() {
        return FileSize;
    }

    /**
     * 
     * @param FileSize
     *     The FileSize
     */
    public void setFileSize(String FileSize) {
        this.FileSize = FileSize;
    }

    /**
     * 
     * @return
     *     The ContentType
     */
    public String getContentType() {
        return ContentType;
    }

    /**
     * 
     * @param ContentType
     *     The ContentType
     */
    public void setContentType(String ContentType) {
        this.ContentType = ContentType;
    }

    /**
     * 
     * @return
     *     The Thumbnail
     */
    public com.example.administrator.boxsocialmvp.Objects.Thumbnail getThumbnail() {
        return Thumbnail;
    }

    /**
     * 
     * @param Thumbnail
     *     The Thumbnail
     */
    public void setThumbnail(com.example.administrator.boxsocialmvp.Objects.Thumbnail Thumbnail) {
        this.Thumbnail = Thumbnail;
    }

}
