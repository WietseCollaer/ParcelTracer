package com.example.gebruiker.parceltracer.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gebruiker.parceltracer.App;
import com.example.gebruiker.parceltracer.R;
import com.example.gebruiker.parceltracer.api.repositories.TrackingRepository;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.example.gebruiker.parceltracer.view.adapters.ParcelListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Module;

@Module
public class ParcelOverviewFragment extends Fragment {
    @BindView(R.id.parcel_list)
    ListView parcelList;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout refreshLayout;

    @Inject
    public TrackingRepository repository;

    private ParcelListAdapter adapter;

    public ParcelOverviewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getActivity().getApplication()).getNetComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overview_fragment, container, false);
        ButterKnife.bind(this, view);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        adapter = new ParcelListAdapter(getContext(), getAllTrackings());
        adapter.setNotifyOnChange(true);
        parcelList.setAdapter(adapter);

        return view;
    }

    private void refreshContent() {
        adapter = new ParcelListAdapter(getContext(), getAllTrackings());
        parcelList.setAdapter(adapter);
        refreshLayout.setRefreshing(false);
    }

    private List<Tracking> getAllTrackings() {
        return repository.getTrackings();
    }
}
