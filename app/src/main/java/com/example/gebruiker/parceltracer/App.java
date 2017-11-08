package com.example.gebruiker.parceltracer;

import android.app.Application;

import com.example.gebruiker.parceltracer.dagger.components.DaggerNetComponent;
import com.example.gebruiker.parceltracer.dagger.components.NetComponent;
import com.example.gebruiker.parceltracer.dagger.modules.AppModule;
import com.example.gebruiker.parceltracer.dagger.modules.DataModule;

public class App extends Application {
    private final String baseUrl = "https://api.aftership.com/v4/";
    private final String apiKey = "104b6506-bd37-4f5b-ab69-22f107fe4802";

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new DataModule(baseUrl, apiKey))
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}