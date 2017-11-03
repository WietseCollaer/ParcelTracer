package com.example.gebruiker.parceltracer.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gebruiker.parceltracer.R;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.example.gebruiker.parceltracer.view.activities.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParcelListAdapter extends ArrayAdapter<Tracking> {
    static final String LOG_TAG = ParcelItemHolder.class.getSimpleName();
    private LayoutInflater inflater;

    public ParcelListAdapter(Context context, List<Tracking> parcels){
        super(context, 0, parcels);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tracking tracking = getItem(position);

        ParcelItemHolder itemHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.parcel_list_item, parent, false);
            itemHolder = new ParcelItemHolder(convertView);
            convertView.setTag(itemHolder);
        }
        else{
            itemHolder = (ParcelItemHolder) convertView.getTag();
        }
        Log.d(LOG_TAG, "Hier geweest" + tracking.getTitle());
        itemHolder.itemName.setText(tracking.getTitle());
        itemHolder.itemStatus.setText(tracking.getTag());
        itemHolder.trackingNumber = tracking.getTrackingNumber();

        return convertView;
    }

    static class ParcelItemHolder {
        @BindView(R.id.parcel_list_item_itemName)
        TextView itemName;

        @BindView(R.id.parcel_list_item_itemStatus)
        TextView itemStatus;

        String trackingNumber;

        public ParcelItemHolder(View view){
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.view_details_button)
        void goToDetails(View view){
            Intent intent = new Intent(view.getContext(), DetailsActivity.class)
                    .putExtra("TrackingId", trackingNumber);
            view.getContext().startActivity(intent);
        }
    }
}
