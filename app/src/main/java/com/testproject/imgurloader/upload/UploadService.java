package com.testproject.imgurloader.upload;

import android.content.Context;

import com.testproject.imgurloader.api.model.FileToUpload;
import com.testproject.imgurloader.api.model.ImageResponse;
import com.testproject.imgurloader.api.ImgurApi;


import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Callback;

public class UploadService {
    public final static String LOG_TAG = UploadService.class.getSimpleName();
    private ImgurApi imgurApi;

    public UploadService(ImgurApi imgurApi) {
        this.imgurApi = imgurApi;
    }

    public void uploadFile(FileToUpload fileToUpload, final Callback<ImageResponse> callback) {
        imgurApi.postImage(fileToUpload.getTitle(), RequestBody.create(fileToUpload.getImageFile().getfileToUpload.getImageFile(), callback);

    }
}
