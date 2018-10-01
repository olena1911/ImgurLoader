package com.testproject.imgurloader.api;

import com.testproject.imgurloader.api.model.ImageResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ImgurApi {

    @Multipart
    @POST("/3/image")
    Call<ImageResponse> postImage(
            @Part("title") RequestBody title,
            @Part MultipartBody.Part file
    );
}
