package com.example.gebruiker.parceltracer.view.viewHolders;

import android.view.View;
import android.widget.TextView;

import com.example.gebruiker.parceltracer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParcelItemHolder {
    @BindView(R.id.parcel_list_item_itemName)
    TextView itemName;

    @BindView(R.id.parcel_list_item_itemStatus)
    TextView itemStatus;

    public ParcelItemHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
