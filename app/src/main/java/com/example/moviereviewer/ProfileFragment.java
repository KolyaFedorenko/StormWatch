package com.example.moviereviewer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfileFragment extends Fragment {

    public interface SignOutListener{
        void onSignOut();
    }
    private SignOutListener signOutListener;

    private Button buttonSingOut;

    public ProfileFragment() { }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        signOutListener = (SignOutListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        buttonSingOut = view.findViewById(R.id.buttonSignOut);
        buttonSingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutListener.onSignOut();
            }
        });
        return view;
    }
}