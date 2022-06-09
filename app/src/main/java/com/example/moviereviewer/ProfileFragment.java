package com.example.moviereviewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements ViewableFragment {

    public interface SignOutListener{
        void onSignOut();
    }
    private SignOutListener signOutListener;

    private String login;
    private StorageReference storageReference;
    private boolean favoritesShowed = false;

    private Button buttonSingOut;
    private TextView textLogin, textStatus;
    private CircleImageView imageProfile;
    private ConstraintLayout clFavoriteMovies;
    private RecyclerView recyclerViewFavorites;

    public ProfileFragment(String login) {
        this.login = login;
    }

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
        storageReference = FirebaseStorage.getInstance().getReference(login + "/Images");
        findViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        useViews();
    }

    @Override
    public void findViews(View view) {
        buttonSingOut = view.findViewById(R.id.buttonSignOut);
        textLogin = view.findViewById(R.id.textLogin);
        textStatus = view.findViewById(R.id.textStatus);
        imageProfile = view.findViewById(R.id.imageProfile);
        clFavoriteMovies = view.findViewById(R.id.clFavoriteMovies);
        recyclerViewFavorites = view.findViewById(R.id.recyclerViewFavorites);
    }

    @Override
    public void useViews() {
        buttonSingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutListener.onSignOut();
            }
        });

        imageProfile.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ImagePicker.Companion.with(ProfileFragment.this)
                        .cropSquare()
                        .galleryOnly()
                        .maxResultSize(500, 500)
                        .compress(1024)
                        .start();
                return false;
            }
        });

        clFavoriteMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!favoritesShowed){
                    recyclerViewFavorites.setVisibility(View.VISIBLE);
                    favoritesShowed = true;
                } else {
                    recyclerViewFavorites.setVisibility(View.GONE);
                    favoritesShowed = false;
                }
            }
        });

        textLogin.setText(login);
        Glide.with(getActivity()).load(FirebaseStorage.getInstance().getReference()
                .child(login + "/Images/ProfileImage"))
                .into(imageProfile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            imageProfile.setImageURI(selectedImage);
            storageReference.child("ProfileImage").putFile(selectedImage);
        }
    }
}