package com.example.gebruiker.parceltracer.data.remote.datasources;

import com.example.gebruiker.parceltracer.data.remote.services.CourierService;
import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.example.gebruiker.parceltracer.model.Courier;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoteCourierDataSource {
    private CourierService service;
    private Observable<AftershipResource> resource;

    @Inject
    public RemoteCourierDataSource(CourierService service) {
        this.service = service;
    }

    public Observable<List<Courier>> getCouriers() {
        resource = service.getCouriers();
        return Observable.just(resource.blockingFirst().getCouriers());
    }

    public Observable<List<Courier>> getAllCouriers() {
        resource = service.getAllCouriers();
        return Observable.just(resource.blockingFirst().getCouriers());
    }
}