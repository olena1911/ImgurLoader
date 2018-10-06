package com.testproject.imgurloader.api.model;

/**
 * Class for API response parsing.
 */
public class ImageResponse {
    private boolean success;
    private int status;
    private UploadedImage data;

    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public UploadedImage getData() {
        return data;
    }

    public static class UploadedImage {
        private String id;
        private String title;
        private String deletehash;
        private String link;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }

        public String getDeletehash() {
            return deletehash;
        }
    }
}