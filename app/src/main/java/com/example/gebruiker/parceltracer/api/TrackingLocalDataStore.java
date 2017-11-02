package com.example.gebruiker.parceltracer.api;

import android.content.Context;

import com.example.gebruiker.parceltracer.api.services.TrackingService;
import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.Query;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

import com.example.gebruiker.parceltracer.data.DatabaseContract.TrackingEntry;

public class TrackingLocalDataStore implements TrackingService {
    private StorIOContentResolver mStorIOContentResolver;

    @Inject
    public TrackingLocalDataStore(@NonNull Context context) {
        mStorIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(context.getContentResolver())
                .addTypeMapping(Tracking.class, ContentResolverTypeMapping.builder()
                .putResolver(new TrackingStorIOContentResolverPutResolver())
                .getResolver(new TrackingStorIOContentResolverGetResolver())
                .deleteResolver(new TrackingStorIOContentResolverDeleteResolver())
                .build()).build();
    }

    @Override
    public Observable<AftershipResource> getTrackings() {
        return mStorIOContentResolver.get()
                .listOfObjects(Tracking.class)
                .withQuery(Query.builder().uri(TrackingEntry.CONTENT_URI).build())
                .prepare().;
    }
}