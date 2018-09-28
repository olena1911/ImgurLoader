package com.testproject.imgurloader.api.model;

public class ImageResponse {
    private boolean success;
    private int status;
    private UploadedImage data;

    public static class UploadedImage {
        private String id;
        private String title;
        private String description;
        private String type;
        private boolean animated;
        private int width;
        private int height;
        private int size;
        private int views;
        private int bandwidth;
        private String vote;
        private boolean favorite;
        private String account_url;
        private String deletehash;
        private String name;
        private String link;
    }
}