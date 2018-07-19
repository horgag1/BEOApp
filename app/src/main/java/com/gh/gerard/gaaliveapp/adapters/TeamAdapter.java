package com.gh.gerard.gaaliveapp.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.gh.gerard.gaaliveapp.R;
import com.gh.gerard.gaaliveapp.model.Team;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamAdapter extends FirestoreAdapter<TeamAdapter.ViewHolder> {


    public TeamAdapter(Query query) {
        super(query);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new TeamAdapter.ViewHolder(inflater.inflate(R.layout.team_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getSnapshot(position));

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "Yeah";

        @BindView(R.id.tvGoalKeeper)
        TextView goalie ;

        @BindView(R.id.ivGoalkeeper)
        ImageView imageGoalie;

        @BindView(R.id.tvRCB)
        TextView rcb;

        @BindView(R.id.ivRCB)
        ImageView imageRCB;


        @BindView(R.id.tvFullBack)
        TextView fullBack;
        @BindView(R.id.ivFullback)
        ImageView imageFullback;

        @BindView(R.id.tvLCB)
        TextView lcb;

        @BindView(R.id.ivLCB)
        ImageView imageLCB;


        @BindView(R.id.tvRHB)
        TextView rhb;
        @BindView(R.id.ivRHB)
        ImageView imageRHB;

        @BindView(R.id.tvCenterBack)
        TextView centerBack;
        @BindView(R.id.ivCenterBack)
        ImageView imageCenterBack;

        @BindView(R.id.tvLHB)
        TextView lhb;
        @BindView(R.id.ivLHB)
        ImageView imageLHB;

        @BindView(R.id.tvEight)
        TextView eight;
        @BindView(R.id.ivEight)
        ImageView imageEight;

        @BindView(R.id.tvNine)
        TextView nine;
        @BindView(R.id.ivNine)
        ImageView imageNine;

        @BindView(R.id.tvRHF)
        TextView rhf;
        @BindView(R.id.ivRHF)
        ImageView imageRHF;

        @BindView(R.id.tvCenterForward)
        TextView centerForward;
        @BindView(R.id.ivCeterForward)
        ImageView imageCenterForward;


        @BindView(R.id.tvLHF)
        TextView lhf;
        @BindView(R.id.ivLHF)
        ImageView imageLHF;

        @BindView(R.id.tvRCF)
        TextView rcf;
        @BindView(R.id.ivRCF)
        ImageView imageRCF;

        @BindView(R.id.tvFullForward)
        TextView fullForward;
        @BindView(R.id.ivFullForward)
        ImageView imageFullForward;

        @BindView(R.id.tvLCF)
        TextView lcf;
        @BindView(R.id.ivLCF)
        ImageView imageLCF;

        @BindView(R.id.tvSubs)
        TextView subs;

        @BindView(R.id.tvName)
        TextView name;
















































        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(DocumentSnapshot snapshot) {

            Team team= snapshot.toObject(Team.class);


            Glide.with(imageGoalie.getContext())
                    .load(team.getLogo())
                    .into(imageGoalie);

            Glide.with(imageRCB.getContext())
                    .load(team.getLogo())
                    .into(imageRCB);
            Glide.with(imageFullback.getContext())
                    .load(team.getLogo())
                    .into(imageFullback);
            Glide.with(imageLCB.getContext())
                    .load(team.getLogo())
                    .into(imageLCB);
            Glide.with(imageRHB.getContext())
                    .load(team.getLogo())
                    .into(imageRHB);
            Glide.with(imageCenterBack.getContext())
                    .load(team.getLogo())
                    .into(imageCenterBack);
            Glide.with(imageLHB.getContext())
                    .load(team.getLogo())
                    .into(imageLHB);
            Glide.with(imageEight.getContext())
                    .load(team.getLogo())
                    .into(imageEight);
            Glide.with(imageNine.getContext())
                    .load(team.getLogo())
                    .into(imageNine);
            Glide.with(imageRHF.getContext())
                    .load(team.getLogo())
                    .into(imageRHF);
            Glide.with(imageCenterForward.getContext())
                    .load(team.getLogo())
                    .into(imageCenterForward);
            Glide.with(imageLHF.getContext())
                    .load(team.getLogo())
                    .into(imageLHF);
            Glide.with(imageRCF.getContext())
                    .load(team.getLogo())
                    .into(imageRCF);
            Glide.with(imageFullForward.getContext())
                    .load(team.getLogo())
                    .into(imageFullForward);
            Glide.with(imageRCF.getContext())
                    .load(team.getLogo())
                    .into(imageRCF);
            Glide.with(imageLCF.getContext())
                    .load(team.getLogo())
                    .into(imageLCF);




            //String temp= team.getPlayers();

            //Log.d(TAG, "Pts returned" + temp);
            //String temp1= temp.replace("qpz", "\n");


           // players.setText(temp1);

            goalie.setText(team.getGoalie());
            rcb.setText(team.getRcb());
            lcb.setText(team.getLcb());
            fullBack.setText(team.getFb());
            rhb.setText(team.getRhb());
            centerBack.setText(team.getCb());
            lhb.setText(team.getLhb());
            eight.setText(team.getEight());
            nine.setText(team.getNine());
            rhf.setText(team.getRhf());
            centerForward.setText(team.getCf());
            lhf.setText(team.getLhf());
            rcf.setText(team.getRcf());
            fullForward.setText(team.getFf());
            lcf.setText(team.getLcf());

            subs.setText(team.getSubs());
            name.setText(team.getName());






        }
    }
}
