package com.example.moviereviewer;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class CustomAlertDialog {

    private DatabaseReference movieReference;
    private Movie movie;

    public CustomAlertDialog(Movie movie){
        movieReference = FirebaseDatabase.getInstance().getReference("KolyaFedorenko/Movies/" + movie.getTitle());
        this.movie = movie;
    }

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        TextView textEditMovie, textDeleteMovie;
        ShapeableImageView dialogMoviePoster;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        textEditMovie = dialog.findViewById(R.id.textEditMovie);
        textDeleteMovie = dialog.findViewById(R.id.textDeleteMovie);
        dialogMoviePoster = dialog.findViewById(R.id.dialogMoviePoster);

        if (movie.getImagePath().contains("w500")){
            Glide.with(activity).load(movie.getImagePath()).centerCrop().into(dialogMoviePoster);
        } else {
            Glide.with(activity)
                    .load(FirebaseStorage.getInstance().getReference()
                            .child("KolyaFedorenko/Images/" + movie.getImagePath()))
                    .centerCrop()
                    .into(dialogMoviePoster);
        }

        textEditMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textDeleteMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieReference.removeValue();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
