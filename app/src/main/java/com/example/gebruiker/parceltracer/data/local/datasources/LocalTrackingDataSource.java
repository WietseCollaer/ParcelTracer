package com.example.gebruiker.parceltracer.data.local.datasources;

import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.example.gebruiker.parceltracer.model.Checkpoint;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.example.gebruiker.parceltracer.model.Tracking_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import io.reactivex.Observable;

public class LocalTrackingDataSource {
    public Observable<List<Tracking>> getTrackings() {
        List<Tracking> trackings = SQLite.select().from(Tracking.class).queryList();
        return Observable.just(trackings);
    }

    public Observable<Tracking> getTrackingById(String id) {
        Tracking tracking = SQLite.select().from(Tracking.class)
                .where(Tracking_Table.id.is(id)).querySingle();
        return Observable.just(tracking);
    }

    public Observable<Tracking> getTrackingByNumber(String slug, String number) {
        Tracking tracking = SQLite.select().from(Tracking.class)
                .where(Tracking_Table.slug.is(slug)).and(Tracking_Table.trackingNumber.is(number))
                .querySingle();

        return Observable.just(tracking);
    }

    public Observable<List<Checkpoint>> getCheckpointsById(String trackingId) {
        List<Checkpoint> checkpoints = SQLite.select().from(Tracking.class)
                .where(Tracking_Table.id.is(trackingId)).querySingle().getCheckpoints();
        return Observable.just(checkpoints);
    }

    public Observable<List<Checkpoint>> getCheckpointsByNumber(String slug, String trackingNumber) {
        List<Checkpoint> checkpoints = SQLite.select().from(Tracking.class)
                .where(Tracking_Table.slug.is(slug))
                .and(Tracking_Table.trackingNumber.is(trackingNumber))
                .querySingle().getCheckpoints();
        return Observable.just(checkpoints);
    }

    public Observable<Tracking> addTracking(AftershipResource item) {
        Tracking tracking = item.getTracking();

        tracking.save();

        return Observable.just(tracking);
    }

    public Observable<Tracking> deleteTrackingById(String id) {
        Tracking tracking = SQLite.select().from(Tracking.class)
                .where(Tracking_Table.id.is(id)).querySingle();

        tracking.delete();

        return Observable.just(tracking);
    }

    public Observable<Tracking> deleteTrackingByNumber(String slug, String number) {
        Tracking tracking = SQLite.select().from(Tracking.class)
                .where(Tracking_Table.slug.is(slug)).and(Tracking_Table.trackingNumber.is(number))
                .querySingle();

        tracking.delete();

        return Observable.just(tracking);
    }
}