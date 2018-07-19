package com.gh.gerard.gaaliveapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import com.gh.gerard.gaaliveapp.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationsFragment extends Fragment {

    String topic;
    Button btnSubscribe;
    Button btnUnsubscribe;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_notifications,container,false);


        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.counties, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        btnSubscribe= view.findViewById(R.id.btnSubscribe);
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ddd","button clicked");
                FirebaseMessaging.getInstance().subscribeToTopic(topic);

                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "We'll update you on all the latest news about " + topic, Snackbar.LENGTH_LONG).show();

            }
        });

        btnUnsubscribe= view.findViewById(R.id.btnUnsubscribe);
        btnUnsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);

                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "You'll no longer get updates about " + topic, Snackbar.LENGTH_LONG).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                topic= parent.getItemAtPosition(pos).toString();
                

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        return view;
    }





}
