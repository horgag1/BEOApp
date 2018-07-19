package com.gh.gerard.gaaliveapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gh.gerard.gaaliveapp.R;
import com.gh.gerard.gaaliveapp.model.Rating;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsAdapter extends FirestoreAdapter<CommentsAdapter.ViewHolder> {


    public CommentsAdapter(Query query) {
        super(query);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comments, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getSnapshot(position).toObject(Rating.class));

        }


    static class ViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.rating_item_name)
        TextView nameView;

        @BindView(R.id.rating_item_text)
        TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Rating rating) {
            nameView.setText(rating.getUserName());
            textView.setText(rating.getText());
        }

    }
}
