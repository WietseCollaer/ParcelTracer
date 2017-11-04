package com.example.gebruiker.parceltracer.api.repositories;

import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.example.gebruiker.parceltracer.model.Checkpoint;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.example.gebruiker.parceltracer.api.services.TrackingService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TrackingRepository {
    private TrackingService service;
    private Observable<AftershipResource> resource;

    @Inject
    public TrackingRepository(TrackingService service) {
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

    public List<Checkpoint> getCheckpointsById(String trackingId) {
        resource = service.getTrackingById(trackingId);
        return resource.blockingFirst().getTracking().getCheckpoints();
    }

    public List<Checkpoint> getCheckpointsByNumber(String slug, String trackingNumber) {
        resource = service.getTrackingByNumber(slug, trackingNumber);
        return resource.blockingFirst().getTracking().getCheckpoints();
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