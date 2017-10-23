package com.example.gebruiker.parceltracer.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gebruiker.parceltracer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gebruiker on 19/10/2017.
 */

public class ParcelItemAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParcelItemHolder holder;
        if(convertView != null){
            holder = (ParcelItemHolder) convertView.getTag();
        }
        else{

        }

        return null;
    }

    static class ParcelItemHolder {
        @BindView(R.id.parcel_list_item_itemName)
        TextView itemName;

        @BindView(R.id.parcel_list_item_itemStatus)
        TextView itemStatus;

        public ParcelItemHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
