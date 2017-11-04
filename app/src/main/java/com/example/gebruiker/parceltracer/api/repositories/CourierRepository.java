package com.example.gebruiker.parceltracer.api.repositories;

import com.example.gebruiker.parceltracer.api.services.CourierService;
import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.example.gebruiker.parceltracer.model.Courier;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CourierRepository {
    private CourierService service;
    private Observable<AftershipResource> resource;

    @Inject
    public CourierRepository(CourierService service) {
        this.service = service;
    }

    public List<Courier> getCouriers() {
        resource = service.getCouriers();
        return resource.blockingFirst().getCouriers();
    }

    public List<Courier> getAllCouriers() {
        resource = service.getAllCouriers();
        return resource.blockingFirst().getCouriers();
    }
}