package com.testproject.imgurloader.api;

import com.testproject.imgurloader.api.model.ImageResponse;

import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ImgurApi {

    @POST("/3/image")
    void postImage(
            @Query("title") String title,
            @Part RequestBody file,
            Callback<ImageResponse> cb
    );
}
