package com.example.gebruiker.parceltracer.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gebruiker.parceltracer.R;
import com.example.gebruiker.parceltracer.model.Checkpoint;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gebruiker on 4/11/2017.
 */

public class CheckpointListAdapter extends ArrayAdapter<Checkpoint> {
    private LayoutInflater inflater;

    public CheckpointListAdapter(@NonNull Context context, List<Checkpoint> checkpoints) {
        super(context, 0, checkpoints);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Checkpoint checkpoint = getItem(position);

        CheckpointItemHolder itemHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.checkpoint_list_item, parent, false);
            itemHolder = new CheckpointItemHolder(convertView);
            convertView.setTag(itemHolder);
        }
        else{
            itemHolder= (CheckpointItemHolder) convertView.getTag();
        }

        itemHolder.checkpointMessage.setText(checkpoint.getMessage());
        itemHolder.checkpointLocation.setText(checkpoint.getLocation());
        itemHolder.checkpointDate.setText(checkpoint.getCheckpointTime());

        return convertView;
    }

    static class CheckpointItemHolder{
        @BindView(R.id.checkpoint_message)
        TextView checkpointMessage;

        @BindView(R.id.checkpoint_location)
        TextView checkpointLocation;

        @BindView(R.id.checkpoint_date)
        TextView checkpointDate;

        public CheckpointItemHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
