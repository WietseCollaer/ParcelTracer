package com.example.gebruiker.parceltracer.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gebruiker.parceltracer.App;
import com.example.gebruiker.parceltracer.R;
import com.example.gebruiker.parceltracer.api.repositories.TrackingRepository;
import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.example.gebruiker.parceltracer.view.activities.HomeActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gebruiker on 3/11/2017.
 */

public class InputFragment extends Fragment {
    @Inject
    TrackingRepository repository;

    @BindView(R.id.parcel_input_name)
    EditText nameTextField;

    @BindView(R.id.parcel_input_category)
    Spinner categorySelection;

    @BindView(R.id.parcel_input_tracking_id)
    EditText trackinIdTextField;

    @BindView(R.id.parcel_input_courier)
    Spinner courierSelection;

    @BindView(R.id.parcel_input_error)
    TextView errorTextView;

    public InputFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getActivity().getApplication()).getNetComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_fragment, container, false);
        ButterKnife.bind(this, view);

        categorySelection.setAdapter(getItemsForSelection(R.array.categories));
        courierSelection.setAdapter(getItemsForSelection(R.array.courier_options));

        return view;
    }

    @NonNull
    private ArrayAdapter<CharSequence> getItemsForSelection(@ArrayRes int items) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    @OnClick(R.id.add_parcel_save_button)
    public void saveParcel(){
        if(isEditTextFieldEmpty(nameTextField)){
            setError(getString(R.string.name_required));
        } else if(isEditTextFieldEmpty(trackinIdTextField)){
            setError(getString(R.string.trackingid_required));
        }
        else{
            Tracking trackingToAdd = getTrackingFromInput();
            repository.addTracking(new AftershipResource(trackingToAdd));
            Toast.makeText(getContext(), getString(R.string.parcel_saved), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), HomeActivity.class));
        }
    }

    private Tracking getTrackingFromInput() {
        String title = nameTextField.getText().toString().trim();
        String courier = courierSelection.getSelectedItem().toString().toLowerCase();
        String trackingId = trackinIdTextField.getText().toString().trim();
        String category = categorySelection.getSelectedItem().toString().toLowerCase();
        Map<String, String> customFields = new HashMap<>();
        customFields.put("category", category);

        return new Tracking(title, courier, trackingId, customFields);
    }

    private void setError(String errorMessage) {
        errorTextView.setText(errorMessage);
    }

    private boolean isEditTextFieldEmpty(EditText editTextToCheck) {
        return editTextToCheck.getText().toString().trim().equals("");
    }
}
