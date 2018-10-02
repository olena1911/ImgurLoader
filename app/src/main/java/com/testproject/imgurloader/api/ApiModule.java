package com.testproject.imgurloader.api;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public final String BASE_URL = "https://api.imgur.com";

    @Provides
    public OkHttpClient provideClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public ImgurApi provideApiService() {
        return provideRetrofit(BASE_URL, provideClient()).create(ImgurApi.class);
    }
}
