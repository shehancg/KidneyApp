package com.example.kidneyapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidneyapp.R;
import com.example.kidneyapp.model.KidneyRecipient;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<KidneyRecipient> kidneyRecipients;

    public RecyclerAdapter(List<KidneyRecipient> kidneyRecipients) {
        this.kidneyRecipients = kidneyRecipients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KidneyRecipient recipient = kidneyRecipients.get(position);
        holder.textViewId.setText("ID: " + recipient.getId());
        holder.textViewScore.setText("Compatibility Score: " + recipient.getCompatibilityScore());
    }

    @Override
    public int getItemCount() {
        return kidneyRecipients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId;
        public TextView textViewScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewScore = itemView.findViewById(R.id.textViewScore);
        }
    }
}
