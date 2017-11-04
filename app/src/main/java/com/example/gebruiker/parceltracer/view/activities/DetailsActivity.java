package com.example.gebruiker.parceltracer.view.activities;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gebruiker.parceltracer.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends ActionBarActivity implements OnMapReadyCallback {
    /*@BindView(R.id.details_parcel_name)
    TextView parcelName;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

/*        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("TrackingId")){
            parcelName.setText(intent.getStringExtra("TrackingId"));
        }*/
        //TODO trackingId uit intent gebruiken voor detailweergave met kaart

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
