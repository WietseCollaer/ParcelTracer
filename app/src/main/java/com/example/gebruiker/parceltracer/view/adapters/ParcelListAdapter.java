package com.example.gebruiker.parceltracer.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gebruiker.parceltracer.App;
import com.example.gebruiker.parceltracer.R;
import com.example.gebruiker.parceltracer.data.repositories.TrackingRepository;
import com.example.gebruiker.parceltracer.model.Tracking;
import com.example.gebruiker.parceltracer.view.activities.DetailsActivity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParcelListAdapter extends ArrayAdapter<Tracking> {
    static final String LOG_TAG = ParcelItemHolder.class.getSimpleName();
    private LayoutInflater inflater;
    List<Tracking> parcels;

    @Inject
    TrackingRepository repository;

    public ParcelListAdapter(Context context, List<Tracking> parcels){
        super(context, 0, parcels);
        this.parcels = parcels;
        ((App)context.getApplicationContext()).getNetComponent().inject(this);
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
        Log.d(LOG_TAG, "Hier geweest: " + tracking.getTitle());
        itemHolder.categoryImage.setImageResource(getResourceId(tracking.getCustomFields()));
        itemHolder.itemName.setText(tracking.getTitle());
        itemHolder.itemStatus.setText(tracking.getTag());
        itemHolder.id = tracking.getId();
        itemHolder.removeButton.setTag(position);


        return convertView;
    }

    private int getResourceId(Map<String, String> customFields) {
        String category = customFields.get(Tracking.CATEGORY_KEY);
        return getContext().getResources().getIdentifier(category, "mipmap", getContext().getPackageName());
    }

    public void deleteItem(String id, int position){
        repository.deleteTrackingById(id);
        parcels.remove(position);
        notifyDataSetChanged();

    }

    public List<Tracking> getParcels() {
        return parcels;
    }

    class ParcelItemHolder {
        @BindView(R.id.category_image_view)
        ImageView categoryImage;

        @BindView(R.id.parcel_list_item_itemName)
        TextView itemName;

        @BindView(R.id.parcel_list_item_itemStatus)
        TextView itemStatus;

        @BindView(R.id.remove_item_button)
        Button removeButton;

        String id;

        public ParcelItemHolder(View view){
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.view_details_button)
        void goToDetails(View view){
            Intent intent = new Intent(view.getContext(), DetailsActivity.class)
                    .putExtra("TrackingId", id);
            view.getContext().startActivity(intent);
        }

        @OnClick(R.id.remove_item_button)
        void deleteButtonClick(View view){
            deleteItem(id, (Integer) view.getTag());
        }
    }
}
