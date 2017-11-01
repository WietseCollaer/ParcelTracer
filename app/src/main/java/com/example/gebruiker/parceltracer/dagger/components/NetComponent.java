package com.example.gebruiker.parceltracer.dagger.components;

import com.example.gebruiker.parceltracer.api.controllers.TrackingController;
import com.example.gebruiker.parceltracer.dagger.modules.AppModule;
import com.example.gebruiker.parceltracer.dagger.modules.NetModule;
import com.example.gebruiker.parceltracer.view.activities.HomeActivity;
import com.example.gebruiker.parceltracer.view.fragments.ParcelOverviewFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(TrackingController trackingController);
    void inject(HomeActivity activity);
}