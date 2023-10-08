package com.example.kidneyapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidneyapp.R;
import com.example.kidneyapp.model.KidneyDataItem;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<KidneyDataItem> kidneyDataItems;

    public CardAdapter(List<KidneyDataItem> kidneyDataItems) {
        this.kidneyDataItems = kidneyDataItems;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        KidneyDataItem kidneyDataItem = kidneyDataItems.get(position);

        // Extract data from the KidneyDataItem object
        String id = kidneyDataItem.getId();
        String bloodGroup = kidneyDataItem.getBlood_type();
        String age = kidneyDataItem.getAge();

        // Bind data to the corresponding TextViews
        holder.textViewId.setText(" " + id);
        holder.textViewBloodGroup.setText(" " + bloodGroup);
        holder.textViewAge.setText(" " + age);
    }

    @Override
    public int getItemCount() {
        return kidneyDataItems.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId;
        TextView textViewBloodGroup;
        TextView textViewAge;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.id);
            textViewBloodGroup = itemView.findViewById(R.id.age);
            textViewAge = itemView.findViewById(R.id.type);
        }
    }
}
