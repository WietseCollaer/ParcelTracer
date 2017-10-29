package com.example.gebruiker.parceltracer.presenter.services;

import com.example.gebruiker.parceltracer.model.AftershipResource;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TrackingService {
    @GET("trackings")
    Observable<AftershipResource> getTrackings();

    @GET("trackings/{id}")
    Observable<AftershipResource> getTrackingById(@Path("id") String id);

    @GET("trackings/{slug}/{number}")
    Observable<AftershipResource> getTrackingByNumber(@Path("slug") String slug, @Path("number") String number);

    @POST("trackings")
    Observable<AftershipResource> addTracking(@Body AftershipResource resource);

    @DELETE("trackings/{id}")
    Observable<AftershipResource> deleteTrackingById(@Path("id") String id);

    @DELETE("trackings/{slug}/{number}")
    Observable<AftershipResource> deleteTrackingByNumber(@Path("slug") String slug, @Path("number") String number);
}