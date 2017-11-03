package com.example.gebruiker.parceltracer.dagger.components;

import com.example.gebruiker.parceltracer.api.repositories.TrackingRepository;
import com.example.gebruiker.parceltracer.dagger.modules.AppModule;
import com.example.gebruiker.parceltracer.dagger.modules.NetModule;
import com.example.gebruiker.parceltracer.view.fragments.InputFragment;
import com.example.gebruiker.parceltracer.view.fragments.ParcelOverviewFragment;

import javax.inject.Singleton;

import dagger.Component;

//TODO is dit de juiste manier?
@Singleton
@Component(modules = {AppModule.class, NetModule.class, ParcelOverviewFragment.class})
public interface NetComponent {
    void inject(TrackingRepository trackingRepository);
    void inject(ParcelOverviewFragment fragment);
    void inject(InputFragment fragment);
}