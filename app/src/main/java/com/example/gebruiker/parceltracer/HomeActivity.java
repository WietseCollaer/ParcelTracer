package com.example.gebruiker.parceltracer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.example.gebruiker.parceltracer.fragments.ParcelOverviewFragment;

public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container, new ParcelOverviewFragment()).commit();
        }
    }
}
