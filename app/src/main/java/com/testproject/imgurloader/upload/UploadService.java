package com.testproject.imgurloader.upload;

import android.util.Log;

import com.testproject.imgurloader.api.ImgurApi;
import com.testproject.imgurloader.api.model.ImageToUpload;
import com.testproject.imgurloader.api.model.ImageResponse;
import com.testproject.imgurloader.gallery.GalleryMVP;
import com.testproject.imgurloader.links.LinksMVP;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadService {

    public final static String LOG_TAG = UploadService.class.getSimpleName();

    private ImgurApi imgurApi;
    private final static String API_KEY = "f28cd5fbd5be37f";
    private final static String AUTH_PARAMETER = "Client-ID " + API_KEY;

    private LinksMVP.Presenter mLinksPresenter;
    private GalleryMVP.Presenter mGalleryPresenter;

    public UploadService(LinksMVP.Presenter linksPresenter, ImgurApi imgurApi) {
        mLinksPresenter = linksPresenter;
        this.imgurApi = imgurApi;
    }

    public void setGalleryPresenter(GalleryMVP.Presenter galleryPresenter) {
        mGalleryPresenter = galleryPresenter;
    }

    public void uploadFile(final ImageToUpload imageToUpload) {
        Log.d(LOG_TAG, "file uploading start");
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageToUpload.getImageFile());
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", imageToUpload.getTitle(), requestFile);
        RequestBody name = RequestBody.create(okhttp3.MultipartBody.FORM, imageToUpload.getTitle());
        Call<ImageResponse> req = imgurApi.postImage(AUTH_PARAMETER, name, body);
        req.enqueue(new Callback<ImageResponse>() {
                        @Override
                        public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                            if (response.isSuccessful()) {
                                String link = response.body().getData().getLink();
                                String deletehash = response.body().getData().getDeletehash();
                                mLinksPresenter.saveLink(link, deletehash);
                                mGalleryPresenter.onImageUploaded(imageToUpload.getPosition());
                            } else {
                                Log.e(LOG_TAG, "Response is not successful.");
                            }
                        }

                        @Override
                        public void onFailure(Call<ImageResponse> call, Throwable t) {
                            Log.e(LOG_TAG, "Fail to connect to imgur API: " + t.getMessage());
                        }
                    }
        );
        Log.d(LOG_TAG, "file uploading finish");
    }
}
