package com.example.gebruiker.parceltracer.view.activities;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gebruiker.parceltracer.R;
import com.example.gebruiker.parceltracer.view.fragments.InputFragment;

public class AddParcelActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parcel);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.input_fragment_container, new InputFragment()).commit();
        }
    }


}
