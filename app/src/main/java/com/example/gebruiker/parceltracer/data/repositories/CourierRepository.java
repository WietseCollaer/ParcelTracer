package com.example.gebruiker.parceltracer.data.repositories;

import com.example.gebruiker.parceltracer.data.remote.datasources.RemoteCourierDataSource;
import com.example.gebruiker.parceltracer.model.Courier;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CourierRepository {
    private RemoteCourierDataSource remoteDataSource;

    @Inject
    public CourierRepository(RemoteCourierDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public List<Courier> getCouriers() {
        Observable<List<Courier>> resource = remoteDataSource.getCouriers();
        return resource.blockingFirst();
    }

    public List<Courier> getAllCouriers() {
        Observable<List<Courier>> resource = remoteDataSource.getAllCouriers();
        return resource.blockingFirst();
    }
}