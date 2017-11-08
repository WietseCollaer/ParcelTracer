package com.example.gebruiker.parceltracer.data.remote.services;

import com.example.gebruiker.parceltracer.model.AftershipResource;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CourierService {
    @GET("couriers")
    Observable<AftershipResource> getCouriers();

    @GET("couriers/all")
    Observable<AftershipResource> getAllCouriers();
}