package com.gh.gerard.gaaliveapp.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.gh.gerard.gaaliveapp.R;
import com.gh.gerard.gaaliveapp.adapters.CommentsAdapter;
import com.gh.gerard.gaaliveapp.fragments.CommentsDialogFragment;
import com.gh.gerard.gaaliveapp.model.Match;
import com.gh.gerard.gaaliveapp.model.Rating;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Transaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements EventListener<DocumentSnapshot>, CommentsDialogFragment.RatingListener {



    @BindView(R.id.tvLocation)
    TextView location;


    @BindView(R.id.tvTime)
    TextView time;

    @BindView(R.id.tvReferee)
    TextView referee;

    @BindView(R.id.tvExtraTime)
    TextView extraTime;

    @BindView(R.id.commentsRecycler)
    RecyclerView mRecycler;




    private CommentsDialogFragment mCommentsDialog;
    private CommentsAdapter mCommnetsAdapter;
    private FirebaseFirestore mFirestore;
    private DocumentReference mMatchRef;
    private ListenerRegistration mMatchRegistration;

    public static final String KEY_MATCH_ID = "key_match_id";
    private static final String TAG = "MatchDetail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);


        String matchId = getIntent().getExtras().getString(KEY_MATCH_ID);

        if (matchId == null) {
            throw new IllegalArgumentException("Must pass extra " + KEY_MATCH_ID);
        }

        //Initialise Firebase

        mFirestore = FirebaseFirestore.getInstance();
        mMatchRef = mFirestore.collection("match").document(matchId);

        // Get comments

        Query commentsQuery = mMatchRef
                .collection("comments")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(200);


        mCommnetsAdapter = new CommentsAdapter(commentsQuery) {

            @Override
            public void onDataChanged() {

                if (getItemCount() == 0) {
                    mRecycler.setVisibility(View.GONE);
                    //mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mRecycler.setVisibility(View.VISIBLE);
                    //mEmptyView.setVisibility(View.GONE);
                }
            }
        };



        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mCommnetsAdapter);



        mCommentsDialog= new CommentsDialogFragment();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mCommnetsAdapter.startListening();

        mMatchRegistration= mMatchRef.addSnapshotListener(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMatchRegistration !=null){

            mMatchRegistration.remove();
            mMatchRegistration=null;

        }
    }

    private Task<Void> addRating(final DocumentReference matchRef, final Rating rating) {

        final DocumentReference commentsRef=matchRef.collection("comments").document();


        return mFirestore.runTransaction(new Transaction.Function<Void>() {
            @Nullable
            @Override
            public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {

                Match match= transaction.get(matchRef).toObject(Match.class);

                transaction.set(matchRef, match);
                transaction.set(commentsRef, rating);

                return null;
            }
        });
    }



    @Override
    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {

        if (e != null) {
            Log.w(TAG, "restaurant:onEvent", e);
            return;
        }

        onMatchLoaded(snapshot.toObject(Match.class));

    }

    private void onMatchLoaded(Match match) {
        location.setText(match.getLocation());
        time.setText(match.getTime());
        referee.setText(match.getReferee());
        extraTime.setText(match.getExtraTime());

    }

    @OnClick(R.id.fab_show_rating_dialog)
    public void onAddRatingClicked(View view) {
        mCommentsDialog.show(getSupportFragmentManager(), CommentsDialogFragment.TAG);
    }









    @Override
    public void onRating(Rating rating) {

        addRating(mMatchRef, rating)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Rating added");

                        // Hide keyboard and scroll to top
                        hideKeyboard();
                        mRecycler.smoothScrollToPosition(0);

                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.w(TAG, "Add rating failed", e);


                hideKeyboard();
                Snackbar.make(findViewById(android.R.id.content), "Failed to add rating",
                        Snackbar.LENGTH_SHORT).show();

            }
        });


    }
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

