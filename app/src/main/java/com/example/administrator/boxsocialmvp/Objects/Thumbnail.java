
package com.example.administrator.boxsocialmvp.Objects;


import com.example.administrator.boxsocialmvp.Objects.Metadata_;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("__metadata")
    @Expose
    private Metadata_ Metadata;
    @Expose
    private String MediaUrl;
    @Expose
    private String ContentType;
    @Expose
    private String Width;
    @Expose
    private String Height;
    @Expose
    private String FileSize;

    /**
     * 
     * @return
     *     The Metadata
     */
    public Metadata_ getMetadata() {
        return Metadata;
    }

    /**
     * 
     * @param Metadata
     *     The __metadata
     */
    public void setMetadata(Metadata_ Metadata) {
        this.Metadata = Metadata;
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

}
