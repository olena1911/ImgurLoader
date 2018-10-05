package com.testproject.imgurloader.api;

import com.github.simonpercic.oklog3.OkLogInterceptor;

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
        OkLogInterceptor okLogInterceptor = OkLogInterceptor.builder().build();
        return new OkHttpClient.Builder()
                .addInterceptor(okLogInterceptor)
                .build();
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
