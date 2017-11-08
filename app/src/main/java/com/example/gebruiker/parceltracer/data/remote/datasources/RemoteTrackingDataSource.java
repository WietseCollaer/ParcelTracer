package com.example.gebruiker.parceltracer.data.remote.datasources;

import com.example.gebruiker.parceltracer.data.remote.services.TrackingService;
import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.example.gebruiker.parceltracer.model.Checkpoint;
import com.example.gebruiker.parceltracer.model.Tracking;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoteTrackingDataSource {
    private TrackingService service;
    private Observable<AftershipResource> resource;

    @Inject
    public RemoteTrackingDataSource(TrackingService service) {
        this.service = service;
    }

    public Observable<List<Tracking>> getTrackings() {
        resource = service.getTrackings();
        return Observable.just(resource.blockingFirst().getTrackings());
    }

    public Observable<Tracking> getTrackingById(String id) {
        resource = service.getTrackingById(id);
        return Observable.just(resource.blockingFirst().getTracking());
    }

    public Observable<Tracking> getTrackingByNumber(String slug, String number) {
        resource = service.getTrackingByNumber(slug, number);
        return Observable.just(resource.blockingFirst().getTracking());
    }

    public Observable<List<Checkpoint>> getCheckpointsById(String trackingId) {
        resource = service.getTrackingById(trackingId);
        return Observable.just(resource.blockingFirst().getTracking().getCheckpoints());
    }

    public Observable<List<Checkpoint>> getCheckpointsByNumber(String slug, String trackingNumber) {
        resource = service.getTrackingByNumber(slug, trackingNumber);
        return Observable.just(resource.blockingFirst().getTracking().getCheckpoints());
    }

    public Observable<Tracking> addTracking(AftershipResource item) {
        resource = service.addTracking(item);
        return Observable.just(resource.blockingFirst().getTracking());
    }

    public Observable<Tracking> deleteTrackingById(String id) {
        resource = service.deleteTrackingById(id);
        return Observable.just(resource.blockingFirst().getTracking());
    }

    public Observable<Tracking> deleteTrackingByNumber(String slug, String number) {
        resource = service.deleteTrackingByNumber(slug, number);
        return Observable.just(resource.blockingFirst().getTracking());
    }
}