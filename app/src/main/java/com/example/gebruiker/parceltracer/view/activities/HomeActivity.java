package com.example.gebruiker.parceltracer.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gebruiker.parceltracer.R;
import com.example.gebruiker.parceltracer.view.fragments.ParcelOverviewFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container, new ParcelOverviewFragment()).commit();
        }

        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_parcel_button)
    public void showAddParcelPage(){
        Intent intent = new Intent(getApplicationContext(), AddParcelActivity.class);
        startActivity(intent);
    }


}