package com.example.gebruiker.parceltracer;

import android.app.Application;

import com.example.gebruiker.parceltracer.dagger.components.DaggerDataComponent;
import com.example.gebruiker.parceltracer.dagger.components.DataComponent;
import com.example.gebruiker.parceltracer.dagger.modules.AppModule;
import com.example.gebruiker.parceltracer.dagger.modules.DataModule;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class App extends Application {
    private final String baseUrl = "https://api.aftership.com/v4/";
    private final String apiKey = "104b6506-bd37-4f5b-ab69-22f107fe4802";

    private DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        dataComponent = DaggerDataComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule(baseUrl, apiKey))
                .build();

        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }
}