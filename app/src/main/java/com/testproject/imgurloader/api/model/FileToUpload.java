package com.testproject.imgurloader.api.model;

import java.io.File;

public class FileToUpload {

    private File imageFile;
    private String title;

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
}
