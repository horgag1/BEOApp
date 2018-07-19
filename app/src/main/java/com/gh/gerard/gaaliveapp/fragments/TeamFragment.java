package com.gh.gerard.gaaliveapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gh.gerard.gaaliveapp.R;
import com.gh.gerard.gaaliveapp.adapters.TeamAdapter;
import com.gh.gerard.gaaliveapp.model.Team;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import butterknife.BindView;

public class TeamFragment extends Fragment {

    private static final String TAG = "MainActivity";
    RecyclerView mTeamRecycler;

    private FirebaseFirestore mFirestore;
    private Query mQuery;
    private TeamAdapter mAdapter;
    private TextView dateTeam;
    View v;

    private static final int LIMIT = 50;

    public TeamFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFirestore();
        }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.team_fragment, container, false);
        dateTeam= v.findViewById(R.id.dateTeamSelects);
        mTeamRecycler = v.findViewById(R.id.team_recyclerview);
        ReadSingleContact();
        initRecyclerView();


        return v;
    }

    private void initRecyclerView() {
        if (mQuery == null) {
            Log.w(TAG, "No query, not initializing RecyclerView");
        }

        mAdapter = new TeamAdapter(mQuery) {


            @Override
            public void onDataChanged() {
                // Show/hide content if the query returns empty.
                if (getItemCount() == 0) {
                    mTeamRecycler.setVisibility(View.GONE);
                } else {
                    mTeamRecycler.setVisibility(View.VISIBLE);
                }
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                // Show a snackbar on errors
                Snackbar.make(v.findViewById(android.R.id.content),
                        "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
            }
        };

        mTeamRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTeamRecycler.addItemDecoration(
                new DividerItemDecoration(mTeamRecycler.getContext(), DividerItemDecoration.HORIZONTAL));
        mTeamRecycler.setAdapter(mAdapter);
    }


    private void initFirestore() {
        mFirestore = FirebaseFirestore.getInstance();
        mQuery = mFirestore.collection("team")
                .limit(LIMIT);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }

    }

    private void ReadSingleContact() {
        DocumentReference user = mFirestore.collection("date").document("date");
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    String date= doc.getString("date");
                    dateTeam.setText(date);

                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }




}
