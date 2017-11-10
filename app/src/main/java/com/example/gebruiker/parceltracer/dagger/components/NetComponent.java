package com.example.gebruiker.parceltracer.dagger.components;

import com.example.gebruiker.parceltracer.dagger.modules.NetModule;
import com.example.gebruiker.parceltracer.data.remote.datasources.RemoteCourierDataSource;
import com.example.gebruiker.parceltracer.data.remote.datasources.RemoteTrackingDataSource;
import com.example.gebruiker.parceltracer.data.repositories.CourierRepository;
import com.example.gebruiker.parceltracer.data.repositories.TrackingRepository;
import com.example.gebruiker.parceltracer.dagger.modules.AppModule;
import com.example.gebruiker.parceltracer.view.activities.DetailsActivity;
import com.example.gebruiker.parceltracer.view.adapters.ParcelListAdapter;
import com.example.gebruiker.parceltracer.view.fragments.InputFragment;
import com.example.gebruiker.parceltracer.view.fragments.ParcelOverviewFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(RemoteTrackingDataSource trackingDataSource);
    void inject(RemoteCourierDataSource courierDataSource);
    void inject(TrackingRepository trackingRepository);
    void inject(CourierRepository courierRepository);
    void inject(ParcelOverviewFragment fragment);
    void inject(InputFragment fragment);
    void inject(DetailsActivity detailsActivity);
    void inject(ParcelListAdapter parcelListAdapter);
}