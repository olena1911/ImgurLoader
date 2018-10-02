package com.testproject.imgurloader.upload;

import android.util.Log;

import com.testproject.imgurloader.api.ImgurApi;
import com.testproject.imgurloader.api.model.FileToUpload;
import com.testproject.imgurloader.api.model.ImageResponse;
import com.testproject.imgurloader.links.LinksPresenter;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadService {
    public final static String LOG_TAG = UploadService.class.getSimpleName();
    private ImgurApi imgurApi;

    @Inject
    LinksPresenter mLinksPresenter;

    public UploadService(ImgurApi imgurApi) {
        this.imgurApi = imgurApi;
    }

    public void uploadFile(FileToUpload fileToUpload, final Callback<ImageResponse> callback) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), fileToUpload.getImageFile());
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", fileToUpload.getTitle(), requestFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), fileToUpload.getTitle());

        Call<ImageResponse> req = imgurApi.postImage(name, body);
        req.enqueue(new Callback<ImageResponse>() {
                        @Override
                        public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                            if (response.isSuccessful()) {
                                String imageLink = response.body().getData().getLink();
                                mLinksPresenter.saveLink(imageLink);
                            }
                            else {
                                Log.e(LOG_TAG, "Response is not successful.");
                            }
                        }

                        @Override
                        public void onFailure(Call<ImageResponse> call, Throwable t) {
                            Log.e(LOG_TAG, "Fail to connect to imgur API: " + t.getMessage());
                        }
                    }
        );
    }
}
