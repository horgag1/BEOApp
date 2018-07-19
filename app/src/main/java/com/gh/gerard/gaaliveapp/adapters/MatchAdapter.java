package com.gh.gerard.gaaliveapp.adapters;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gh.gerard.gaaliveapp.R;
import com.gh.gerard.gaaliveapp.model.Match;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchAdapter extends FirestoreAdapter<MatchAdapter.ViewHolder> {





    public interface OnMatchSelectedListener {

        void onMatchSelected(DocumentSnapshot match);

    }

    private OnMatchSelectedListener mListener;

    public MatchAdapter(Query query, OnMatchSelectedListener listener) {
        super(query);
        mListener=listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.match_item, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView homeTeam;

        @BindView(R.id.imageView2)
        ImageView awayTeam;

        @BindView(R.id.textView)
        TextView score;

        @BindView(R.id.textView2)
        TextView competition;

        @BindView(R.id.textView3)
        TextView game;

        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.tvTV)
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void bind(final DocumentSnapshot snapshot,
                         final OnMatchSelectedListener listener) {

            Match match = snapshot.toObject(Match.class);
            Resources resources = itemView.getResources();

            Glide.with(homeTeam.getContext())
                    .load(match.getHomeTeam())
                    .into(homeTeam);
            Glide.with(awayTeam.getContext())
                    .load(match.getAwayTeam())
                    .into(awayTeam);


            score.setText(match.getScore());
            competition.setText(match.getCompetition());
            game.setText(match.getGame());
            status.setText(match.getStatus());
            tv.setText(match.getTv());





            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onMatchSelected(snapshot);
                    }
                }
            });
        }


    }

}
