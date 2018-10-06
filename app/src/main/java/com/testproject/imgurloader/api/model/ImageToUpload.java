package com.testproject.imgurloader.api.model;

import java.io.File;

/**
 * Stores data about uploading image file.
 */
public class ImageToUpload {

    private int position;
    private File imageFile;
    private String title;
    private String uri;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
