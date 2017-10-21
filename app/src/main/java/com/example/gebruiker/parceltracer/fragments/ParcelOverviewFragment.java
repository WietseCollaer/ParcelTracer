package com.example.gebruiker.parceltracer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gebruiker.parceltracer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gebruiker on 17/10/2017.
 */

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
//        ButterKnife.bind(this, view);

        return view;
    }
}
