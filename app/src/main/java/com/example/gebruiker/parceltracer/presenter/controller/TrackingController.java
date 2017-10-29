package com.example.gebruiker.parceltracer.presenter.controller;

import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.example.gebruiker.parceltracer.presenter.services.TrackingService;
import com.example.gebruiker.parceltracer.presenter.utils.ServiceGenerator;

import java.util.List;

import io.reactivex.Observable;

public class TrackingController {
    private TrackingService service;
    private Observable<AftershipResource> resource;

    public TrackingController() {
        service = ServiceGenerator.createService(TrackingService.class);
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