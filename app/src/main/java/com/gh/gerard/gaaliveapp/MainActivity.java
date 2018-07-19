package com.gh.gerard.gaaliveapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.gh.gerard.gaaliveapp.adapters.ViewPagerAdapter;
import com.gh.gerard.gaaliveapp.fragments.TeamFragment;
import com.gh.gerard.gaaliveapp.fragments.FixturesFragment;
import com.gh.gerard.gaaliveapp.fragments.NotificationsFragment;
import com.gh.gerard.gaaliveapp.fragments.TablesFragment;
import com.gh.gerard.gaaliveapp.viewmodel.MainActivityViewModel;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    ViewPagerAdapter adapter;
    private MainActivityViewModel mViewModel;
    private static final int RC_SIGN_IN = 9001;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout= findViewById(R.id.tablayout_id);
        viewPager= findViewById(R.id.viewpager_id);
        adapter= new ViewPagerAdapter(getSupportFragmentManager());

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // Add fragment here.

        adapter.AddFragment(new FixturesFragment(), "Live");
        adapter.AddFragment(new TeamFragment(),"Teamsheets");
        adapter.AddFragment(new TablesFragment(),"Tables");
        adapter.AddFragment(new NotificationsFragment(), "Notifications");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AuthUI.getInstance().signOut(this);
        startSignIn();
        return super.onOptionsItemSelected(item);
    }

    private void startSignIn() {
        // Sign in with FirebaseUI
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(Collections.singletonList(
                        new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                .setIsSmartLockEnabled(false)
                .build();

        startActivityForResult(intent, RC_SIGN_IN);
        mViewModel.setIsSigningIn(true);
    }
}
