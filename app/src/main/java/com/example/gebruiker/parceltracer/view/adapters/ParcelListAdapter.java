package com.example.gebruiker.parceltracer.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gebruiker.parceltracer.DummyClasses.ParcelDummy;
import com.example.gebruiker.parceltracer.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParcelListAdapter extends ArrayAdapter<ParcelDummy> {
    static final String LOG_TAG = ParcelItemHolder.class.getSimpleName();
    private LayoutInflater inflater;

    public ParcelListAdapter(Context context, ArrayList<ParcelDummy> parcels){
        super(context, 0, parcels);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParcelDummy dummy = getItem(position);

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
        Log.d(LOG_TAG, "Hier geweest" + dummy.getName());
        itemHolder.itemName.setText(dummy.getName());
        itemHolder.itemStatus.setText(dummy.getStatus());

        return convertView;
    }

    static class ParcelItemHolder {
        @BindView(R.id.parcel_list_item_itemName)
        TextView itemName;

        @BindView(R.id.parcel_list_item_itemStatus)
        TextView itemStatus;

        public ParcelItemHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
