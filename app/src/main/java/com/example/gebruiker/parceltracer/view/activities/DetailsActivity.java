package com.example.gebruiker.parceltracer.view.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gebruiker.parceltracer.App;
import com.example.gebruiker.parceltracer.R;
import com.example.gebruiker.parceltracer.api.repositories.TrackingRepository;
import com.example.gebruiker.parceltracer.model.Checkpoint;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.example.gebruiker.parceltracer.view.adapters.CheckpointListAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends ActionBarActivity implements OnMapReadyCallback {
    private String trackingId;
    private List<Checkpoint> checkpoints;
    private CheckpointListAdapter adapter;
    public final String LOG_TAG = DetailsActivity.class.getSimpleName();

    @Inject
    TrackingRepository repository;

    @BindView(R.id.details_parcel_title)
    TextView parcelTitle;

    @BindView(R.id.details_parcel_current_state)
    TextView parcelState;

    @BindView(R.id.details_parcel_history)
    ListView parcelHistory;

    @BindView(R.id.parcel_detail_pending)
    TextView pendingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ((App)this.getApplication()).getNetComponent().inject(this);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("TrackingId")){
            trackingId = intent.getStringExtra("TrackingId");
            Log.d("TrackingId", trackingId);
        }

        Tracking tracking = repository.getTrackingById(trackingId);

        setTrackingInfo(tracking);

        checkpoints = repository.getCheckpointsById(trackingId);

        if(checkpoints.isEmpty()){
            pendingTextView.setText(getString(R.string.pending_text));
        }
        else {
            adapter = new CheckpointListAdapter(getBaseContext(), checkpoints);
            adapter.setNotifyOnChange(true);
            parcelHistory.setAdapter(adapter);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setTrackingInfo(Tracking tracking) {
        parcelTitle.setText(tracking.getTitle());
        parcelState.setText(tracking.getSlug());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Polyline parcelRoute = googleMap.addPolyline(new PolylineOptions()
                .clickable(false)
                .add(getLocations()));

        parcelRoute.setColor(getResources().getColor(R.color.map_line_color));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getCoordinatesOfLocation(), 4));

    }

    private LatLng getCoordinatesOfLocation() {
        Geocoder geocoder = new Geocoder(getApplicationContext());
        List<Address> result;
        try{
            Checkpoint last = checkpoints.get(checkpoints.size()-1);
            if(hasALocation(last)){
                result = geocoder.getFromLocationName(last.getLocation(), 1);
                if(isLocationFound(result)){
                    Address address = result.get(0);
                    return new LatLng(address.getLatitude(), address.getLongitude());
                }
            }
            else{
                last = checkpoints.get(checkpoints.size()-2);
                result = geocoder.getFromLocationName(last.getLocation(), 1);
                if(isLocationFound(result)){
                    Address address = result.get(0);
                    return new LatLng(address.getLatitude(), address.getLongitude());
                }
            }
        }
        catch (IOException ex){
            Log.e(LOG_TAG, ex.getMessage());
        }
        //Locatie brussel
        return new LatLng(50.8503463, 4.3517211);
    }

    private LatLng[] getLocations() {
        List<LatLng> coordinates = new ArrayList<>();
        Geocoder geocoder = new Geocoder(getApplicationContext());
        List<Address> result;
        for (Checkpoint checkpoint : checkpoints) {
            try {
                if(hasALocation(checkpoint)) {
                    result = geocoder.getFromLocationName(checkpoint.getLocation(), 1);
                    if (isLocationFound(result)){
                        Address address = result.get(0);
                        coordinates.add(new LatLng(address.getLatitude(), address.getLongitude()));
                    }
                }
            }
            catch (IOException ex){
                Log.e(LOG_TAG, ex.getMessage());
            }
        }
        return coordinates.toArray(new LatLng[coordinates.size()]);
    }

    private boolean isLocationFound(List<Address> result) {
        return result != null && !result.isEmpty();
    }

    private boolean hasALocation(Checkpoint checkpoint) {
        return checkpoint.getLocation() != null && !checkpoint.getLocation().isEmpty();
    }
}
