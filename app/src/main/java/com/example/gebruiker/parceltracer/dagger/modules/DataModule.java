package com.example.gebruiker.parceltracer.dagger.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.gebruiker.parceltracer.data.remote.datasources.RemoteCourierDataSource;
import com.example.gebruiker.parceltracer.data.remote.datasources.RemoteTrackingDataSource;
import com.example.gebruiker.parceltracer.data.repositories.CourierRepository;
import com.example.gebruiker.parceltracer.data.repositories.TrackingRepository;
import com.example.gebruiker.parceltracer.data.remote.services.CourierService;
import com.example.gebruiker.parceltracer.data.remote.services.TrackingService;
import com.example.gebruiker.parceltracer.dagger.utils.DataDeserializer;
import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {
    private String baseUrl;
    private String apiKey;

    public DataModule(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AftershipResource.class, new DataDeserializer())
                .excludeFieldsWithoutExposeAnnotation();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(cache);
        httpClient.addInterceptor(chain -> {
            Request request = chain.request();

            Request.Builder requestBuilder = request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("aftership-api-key", apiKey);

            return chain.proceed(requestBuilder.build());
        });
        return httpClient.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient)
                .baseUrl(baseUrl)
                .build();
    }

    @Provides
    @Singleton
    TrackingService provideTrackingService(Retrofit retrofit) {
        return retrofit.create(TrackingService.class);
    }

    @Provides
    @Singleton
    RemoteTrackingDataSource provideRemoteTrackingDataSource(TrackingService service) {
        return new RemoteTrackingDataSource(service);
    }

    @Provides
    @Singleton
    TrackingRepository provideTrackingRepository(RemoteTrackingDataSource remoteDataSource) {
        return new TrackingRepository(remoteDataSource);
    }

    @Provides
    @Singleton
    CourierService provideCourierService(Retrofit retrofit) {
        return retrofit.create(CourierService.class);
    }

    @Provides
    @Singleton
    RemoteCourierDataSource provideRemoteCourierDataSource(CourierService service) {
        return new RemoteCourierDataSource(service);
    }

    @Provides
    @Singleton
    CourierRepository provideCourierRepository(RemoteCourierDataSource remoteDataSource) {
        return new CourierRepository(remoteDataSource);
    }
}