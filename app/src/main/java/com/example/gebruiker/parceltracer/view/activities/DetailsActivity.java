package com.example.gebruiker.parceltracer.view.activities;

import android.content.Intent;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends ActionBarActivity implements OnMapReadyCallback {
    private String trackingId;
    private List<Checkpoint> checkpoints;
    private CheckpointListAdapter adapter;

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

        if(checkpoints == null){
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

    }
}
