package com.example.gebruiker.parceltracer.api.controllers;

import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.example.gebruiker.parceltracer.api.services.TrackingService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TrackingController {
    private TrackingService service;
    private Observable<AftershipResource> resource;

    @Inject
    public TrackingController(TrackingService service) {
        this.service = service;
    }

    public List<Tracking> getTrackings() {
        resource = service.getTrackings();
        return resource.blockingFirst().getTrackings();
    }

    public Tracking getTrackingById(String id) {
        resource = service.getTrackingById(id);
        return resource.blockingFirst().getTracking();
    }

    public Tracking getTrackingByNumber(String slug, String number) {
        resource = service.getTrackingByNumber(slug, number);
        return resource.blockingFirst().getTracking();
    }

    public Tracking addTracking(AftershipResource item) {
        resource = service.addTracking(item);
        return resource.blockingFirst().getTracking();
    }

    public Tracking deleteTrackingById(String id) {
        resource = service.deleteTrackingById(id);
        return resource.blockingFirst().getTracking();
    }

    public Tracking deleteTrackingByNumber(String slug, String number) {
        resource = service.deleteTrackingByNumber(slug, number);
        return resource.blockingFirst().getTracking();
    }
}