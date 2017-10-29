package com.example.gebruiker.parceltracer.presenter.utils;

import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static final String API_BASE_URL = "https://api.aftership.com/v4/";
    private static final String API_KEY = "104b6506-bd37-4f5b-ab69-22f107fe4802";

    private static Retrofit retrofit;
    private static Retrofit.Builder builder;
    private static OkHttpClient.Builder httpClient;

    public static <S> S createService(Class<S> serviceClass) {
        Gson gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(AftershipResource.class, new DataDeserializer())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request request = chain.request();

            Request.Builder requestBuilder = request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("aftership-api-key", API_KEY);

            return chain.proceed(requestBuilder.build());
        });

        builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));

        retrofit = builder.build();

        return retrofit.create(serviceClass);
    }
}