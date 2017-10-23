package com.example.gebruiker.parceltracer.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gebruiker.parceltracer.DummyClasses.ParcelDummy;
import com.example.gebruiker.parceltracer.R;
import com.example.gebruiker.parceltracer.view.adapters.ParcelListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParcelOverviewFragment extends Fragment {
    @BindView(R.id.parcel_list)
    ListView parcelList;

    public ParcelOverviewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overview_fragment, container, false);
        ButterKnife.bind(this, view);

        //TODO replace with real data from the api.
        ArrayList<ParcelDummy> dummies = new ArrayList<>();
        dummies.add(new ParcelDummy("Samsung s8", "received"));
        dummies.add(new ParcelDummy("Iphone 8", "sent"));

        ParcelListAdapter adapter = new ParcelListAdapter(getContext(), dummies);
        parcelList.setAdapter(adapter);

        return view;
    }
}
