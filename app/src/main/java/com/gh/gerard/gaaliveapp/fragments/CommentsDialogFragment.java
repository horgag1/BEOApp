package com.gh.gerard.gaaliveapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gh.gerard.gaaliveapp.R;
import com.gh.gerard.gaaliveapp.model.Rating;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentsDialogFragment extends DialogFragment {

    public static final String TAG = "CommentsDialog";
    private static final int RC_SIGN_IN = 9001;

    @BindView(R.id.restaurant_form_text)
    EditText mRatingText;

    public interface RatingListener {

        void onRating(Rating rating);

    }

    private RatingListener mRatingListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_dialog_comments,container,false);
        ButterKnife.bind(this, v);
        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof RatingListener) {
            mRatingListener = (RatingListener) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

    }






    @OnClick(R.id.restaurant_form_button)
    public void onSubmitClicked(View view) {

        if(FirebaseAuth.getInstance().getCurrentUser()==null){

            startSignIn();

            }else{

        Rating rating = new Rating(
                FirebaseAuth.getInstance().getCurrentUser(),
                mRatingText.getText().toString());

        if (mRatingListener != null) {
            mRatingListener.onRating(rating);
        }
        }

        dismiss();
    }

    @OnClick(R.id.restaurant_form_cancel)
    public void onCancelClicked(View view) {
        dismiss();
    }

    private void startSignIn() {
        // Sign in with FirebaseUI
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(Collections.singletonList(
                        new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                .setIsSmartLockEnabled(false)
                .build();

        startActivityForResult(intent, RC_SIGN_IN);
       // mViewModel.setIsSigningIn(true);
    }
}
