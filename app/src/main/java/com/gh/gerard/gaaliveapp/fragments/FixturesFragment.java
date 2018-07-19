package com.gh.gerard.gaaliveapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.gh.gerard.gaaliveapp.R;
import com.gh.gerard.gaaliveapp.activities.DetailsActivity;
import com.gh.gerard.gaaliveapp.adapters.MatchAdapter;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;


public class FixturesFragment extends Fragment implements MatchAdapter.OnMatchSelectedListener {

    private static final String TAG = "MainActivity";
    RecyclerView mMatchRecycler;

    private FirebaseFirestore mFirestore;
    private Query mQuery;
    private MatchAdapter mAdapter;
    View v;


    private static final int LIMIT = 50;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFirestore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_fixtures, container, false);
        mMatchRecycler = v.findViewById(R.id.contact_recyclerview);
        initRecyclerView();


        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }

    }


    public FixturesFragment(){

    }

    private void initRecyclerView() {
        if (mQuery == null) {
            Log.w(TAG, "No query, not initializing RecyclerView");
        }

        mAdapter = new MatchAdapter(mQuery, this) {


            @Override
            public void onDataChanged() {
                // Show/hide content if the query returns empty.
                if (getItemCount() == 0) {
                    mMatchRecycler.setVisibility(View.GONE);
                } else {
                    mMatchRecycler.setVisibility(View.VISIBLE);
                }
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                // Show a snackbar on errors
                Snackbar.make(v.findViewById(android.R.id.content),
                        "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
            }
        };

        mMatchRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMatchRecycler.addItemDecoration(
                new DividerItemDecoration(mMatchRecycler.getContext(), DividerItemDecoration.HORIZONTAL));
        mMatchRecycler.setAdapter(mAdapter);



    }


    @Override
    public void onMatchSelected(DocumentSnapshot matches) {
        Intent intent= new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.KEY_MATCH_ID, matches.getId());



        startActivity(intent);

    }
    private void initFirestore() {
        mFirestore = FirebaseFirestore.getInstance();
        mQuery = mFirestore.collection("match")
                .limit(LIMIT);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }
}