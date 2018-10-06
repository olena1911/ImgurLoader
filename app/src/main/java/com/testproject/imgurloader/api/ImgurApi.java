package com.testproject.imgurloader.api;

import com.testproject.imgurloader.api.model.ImageResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImgurApi {

    @Multipart
    @POST("/3/image")
    Call<ImageResponse> postImage(
            @Header("Authorization") String auth,
            @Part("title") RequestBody title,
            @Part MultipartBody.Part image
    );
}
