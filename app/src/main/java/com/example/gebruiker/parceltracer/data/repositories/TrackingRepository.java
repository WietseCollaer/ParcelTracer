package com.example.gebruiker.parceltracer.data.repositories;

import com.example.gebruiker.parceltracer.data.local.datasources.LocalTrackingDataSource;
import com.example.gebruiker.parceltracer.data.remote.datasources.RemoteTrackingDataSource;
import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.example.gebruiker.parceltracer.model.Checkpoint;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.example.gebruiker.parceltracer.data.remote.services.TrackingService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TrackingRepository {
    private LocalTrackingDataSource localDataSource;
    private RemoteTrackingDataSource remoteDataSource;

    @Inject
    public TrackingRepository(LocalTrackingDataSource localDataSource,
                              RemoteTrackingDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public List<Tracking> getTrackings() {
        Observable<List<Tracking>> resource = remoteDataSource.getTrackings();
        return resource.blockingFirst();
    }

    public Tracking getTrackingById(String id) {
        Observable<Tracking> resource = remoteDataSource.getTrackingById(id);
        return resource.blockingFirst();
    }

    public Tracking getTrackingByNumber(String slug, String number) {
        Observable<Tracking> resource = remoteDataSource.getTrackingByNumber(slug, number);
        return resource.blockingFirst();
    }

    public List<Checkpoint> getCheckpointsById(String trackingId) {
        Observable<List<Checkpoint>> resource = remoteDataSource.getCheckpointsById(trackingId);
        return resource.blockingFirst();
    }

    public List<Checkpoint> getCheckpointsByNumber(String slug, String trackingNumber) {
        Observable<List<Checkpoint>> resource =
                remoteDataSource.getCheckpointsByNumber(slug, trackingNumber);
        return resource.blockingFirst();
    }

    public Tracking addTracking(AftershipResource item) {
        Observable<Tracking> resource = remoteDataSource.addTracking(item);
        return resource.blockingFirst();
    }

    public Tracking deleteTrackingById(String id) {
        Observable<Tracking> resource = remoteDataSource.deleteTrackingById(id);
        return resource.blockingFirst();
    }

    public Tracking deleteTrackingByNumber(String slug, String number) {
        Observable<Tracking> resource = remoteDataSource.deleteTrackingByNumber(slug, number);
        return resource.blockingFirst();
    }
}