package com.gh.gerard.gaaliveapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gh.gerard.gaaliveapp.R;
import com.gh.gerard.gaaliveapp.model.Table;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TableAdapter extends FirestoreAdapter<TableAdapter.ViewHolder> {



    public TableAdapter(Query query) {
        super(query);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        return new TableAdapter.ViewHolder(inflater.inflate(R.layout.table_item, parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getSnapshot(position));



    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "Yipee" ;
        @BindView(R.id.tableTitle)
        TextView tvTableTitle;

        @BindView(R.id.played)
        TextView tvPlayed;

        @BindView(R.id.won)
        TextView tvWon;

        @BindView(R.id.drew)
        TextView tvDrew;

        @BindView(R.id.lost)
        TextView tvLost;

        @BindView(R.id.ptsDiff)
        TextView tvPtsDiff;

        @BindView(R.id.pts)
        TextView tvPts;
        
        @BindView(R.id.firstTeam)
        TextView tvFirstTeam;
        
        @BindView(R.id.firstPlayed)
        TextView tvFirstPlayed;
        
        @BindView(R.id.firstWon)
        TextView tvFirstWon;
        
        @BindView(R.id.firstDrew)
        TextView tvFirstDrew;
        
        @BindView(R.id.firstLost)
        TextView tvFirstLost;
        
        @BindView(R.id.firstPtsDiff)
        TextView tvFirstPtsDiff;
        
        @BindView(R.id.firstPts)
        TextView tvFirstPts;


        @BindView(R.id.secondTeam)
        TextView tvSecondTeam;

        @BindView(R.id.secondPlayed)
        TextView tvSecondPlayed;

        @BindView(R.id.secondWon)
        TextView tvSecondWon;

        @BindView(R.id.secondDrew)
        TextView tvSecondDrew;

        @BindView(R.id.secondLost)
        TextView tvSecondLost;

        @BindView(R.id.secondPtsDiff)
        TextView tvSecondPtsDiff;

        @BindView(R.id.secondPts)
        TextView tvSecondPts;


        @BindView(R.id.thirdTeam)
        TextView tvThirdTeam;

        @BindView(R.id.thirdPlayed)
        TextView tvThirdPlayed;

        @BindView(R.id.thirdWon)
        TextView tvThirdWon;

        @BindView(R.id.thirdDrew)
        TextView tvThirdDrew;

        @BindView(R.id.thirdLost)
        TextView tvThirdLost;

        @BindView(R.id.thirdPtsDiff)
        TextView tvThirdPtsDiff;

        @BindView(R.id.thirdPts)
        TextView tvThirdPts;



        @BindView(R.id.fourthTeam)
        TextView tvFourthTeam;

        @BindView(R.id.fourthPlayed)
        TextView tvFourthPlayed;

        @BindView(R.id.fourthWon)
        TextView tvFourthWon;

        @BindView(R.id.fourthDrew)
        TextView tvFourthDrew;

        @BindView(R.id.fourthLost)
        TextView tvFourthLost;

        @BindView(R.id.fourthPtsDiff)
        TextView tvFourthPtsDiff;

        @BindView(R.id.fourthPts)
        TextView tvFourthPts;


        @BindView(R.id.fifthTeam)
        TextView tvFifthTeam;

        @BindView(R.id.fifthPlayed)
        TextView tvFifthPlayed;

        @BindView(R.id.fifthWon)
        TextView tvFifthWon;

        @BindView(R.id.fifthDrew)
        TextView tvFifthDrew;

        @BindView(R.id.fifthLost)
        TextView tvFifthLost;

        @BindView(R.id.fifthPtsDiff)
        TextView tvFifthPtsDiff;

        @BindView(R.id.fifthPts)
        TextView tvFifthPts;




        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(DocumentSnapshot snapshot) {

            Table table= snapshot.toObject(Table.class);

            String tableTitle= table.getTableTitle();

            String firstTeam =table.getFirstTeam();
            String firstPlayed =table.getFirstPlayed();
            String firstWon= table.getFirstWon();
            String firstDrew= table.getFirstDrew();
            String firstLost= table.getFirstLost();
            String firstPtsDiff= table.getFirstPtsDiff();
            String firstPts= table.getFirstPts();

            String secondTeam =table.getSecondTeam();
            String secondPlayed =table.getSecondPlayed();
            String secondWon= table.getSecondWon();
            String secondDrew= table.getSecondDrew();
            String secondLost= table.getSecondLost();
            String secondPtsDiff= table.getSecondPtsDiff();
            String secondPts= table.getSecondPts();

            String thirdTeam =table.getThirdTeam();
            String thirdPlayed =table.getThirdPlayed();
            String thirdWon= table.getThirdWon();
            String thirdDrew= table.getThirdDrew();
            String thirdLost= table.getThirdLost();
            String thirdPtsDiff= table.getThirdPtsDiff();
            String thirdPts= table.getThirdPts();


            String fourthTeam =table.getFourthTeam();
            String fourthPlayed =table.getFourthPlayed();
            String fourthWon= table.getFourthWon();
            String fourthDrew= table.getFourthDrew();
            String fourthLost= table.getFourthLost();
            String fourthPtsDiff= table.getFourthPtsDiff();
            String fourthPts= table.getFourthPts();

            String fifthTeam =table.getFifthTeam();
            String fifthPlayed= table.getFifthPlayed();
            String fifthWon= table.getFifthWon();
            String fifthDrew= table.getFifthDrew();
            String fifthLost= table.getFifthLost();
            String fifthPtsDiff= table.getFifthPtsDiff();
            String fifthPts= table.getFifthPts();


            tvTableTitle.setText(tableTitle);
            
            tvFirstTeam.setText(firstTeam);
            tvFirstPlayed.setText(firstPlayed);
            tvFirstWon.setText(firstWon);
            tvFirstDrew.setText(firstDrew);
            tvFirstLost.setText(firstLost);
            tvFirstPtsDiff.setText(firstPtsDiff);
            tvFirstPts.setText(firstPts);

            tvSecondTeam.setText(secondTeam);
            tvSecondPlayed.setText(secondPlayed);
            tvSecondWon.setText(secondWon);
            tvSecondDrew.setText(secondDrew);
            tvSecondLost.setText(secondLost);
            tvSecondPtsDiff.setText(secondPtsDiff);
            tvSecondPts.setText(secondPts);


            tvThirdTeam.setText(thirdTeam);
            tvThirdPlayed.setText(thirdPlayed);
            tvThirdWon.setText(thirdWon);
            tvThirdDrew.setText(thirdDrew);
            tvThirdLost.setText(thirdLost);
            tvThirdPtsDiff.setText(thirdPtsDiff);
            tvThirdPts.setText(thirdPts);


            tvFourthTeam.setText(fourthTeam);
            tvFourthPlayed.setText(fourthPlayed);
            tvFourthWon.setText(fourthWon);
            tvFourthDrew.setText(fourthDrew);
            tvFourthLost.setText(fourthLost);
            tvFourthPtsDiff.setText(fourthPtsDiff);
            tvFourthPts.setText(fourthPts);


            tvFifthTeam.setText(fifthTeam);
            tvFifthPlayed.setText(fifthPlayed);
            tvFifthWon.setText(fifthWon);
            tvFifthDrew.setText(fifthDrew);
            tvFifthLost.setText(fifthLost);
            tvFifthPtsDiff.setText(fifthPtsDiff);
            tvFifthPts.setText(fifthPts);

        }
    }
}
