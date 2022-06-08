package com.example.moviereviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public interface MovieAdapterInterface{
        void onItemLongPressed(Movie movie);
    }
    private MovieAdapterInterface movieAdapterInterface;

    private final LayoutInflater inflater;
    private final List<Movie> movies;
    private final Context context;

    public MovieAdapter(Context context, List<Movie> movies, MovieAdapterInterface movieAdapterInterface){
        this.movies = movies;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.movieAdapterInterface = movieAdapterInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movies_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        if (movie.getImagePath().contains("w500")){
            Glide.with(context)
                    .load(movie.getImagePath())
                    .into(holder.rvMovieImage);
        }
        else{
            Glide.with(context)
                    .load(FirebaseStorage.getInstance().getReference()
                            .child("KolyaFedorenko/Images/" + movie.getImagePath()))
                    .into(holder.rvMovieImage);
        }
        holder.rvCompositeRating.setProgress(movie.getCompositeRating(), true);
        holder.rvMovieTitle.setText(movie.getTitle());
        holder.rvMovieYear.setText(movie.getYear());
        holder.rvMovieDescription.setText(movie.getDescription());
        holder.rvVisualRating.setProgress(movie.getVisualRating(), true);
        holder.rvVisualRatingValue.setText(String.valueOf(movie.getVisualRating()));
        holder.rvCastRating.setProgress(movie.getCastRating(), true);
        holder.rvCastRatingValue.setText(String.valueOf(movie.getCastRating()));
        holder.rvPlotRating.setProgress(movie.getPlotRating(), true);
        holder.rvPlotRatingValue.setText(String.valueOf(movie.getPlotRating()));
        holder.rvYourAverageRating.setProgress(movie.getCompositeRating(), true);
        holder.rvYourAverageRatingValue.setText(String.valueOf(movie.getCompositeRating()));
        holder.rvAudienceAverageRating.setProgress(movie.getUsersAverageRating(), true);
        holder.rvAudienceAverageRatingValue.setText(String.valueOf(movie.getUsersAverageRating()));

        holder.rvConstraintDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.rvMovieDescription.getMaxLines() == 3){
                    holder.rvMovieDescription.setMaxLines(25);
                }
                else holder.rvMovieDescription.setMaxLines(3);
            }
        });

        holder.rvConstraintMovie.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                movieAdapterInterface.onItemLongPressed(movie);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final CircleImageView rvMovieImage;
        public final CircularProgressIndicator rvCompositeRating;
        public final TextView rvMovieTitle, rvMovieYear, rvMovieDescription;
        public final TextView rvVisualRatingValue, rvCastRatingValue, rvPlotRatingValue;
        public final TextView rvYourAverageRatingValue, rvAudienceAverageRatingValue;
        public final LinearProgressIndicator rvVisualRating, rvCastRating, rvPlotRating;
        public final LinearProgressIndicator rvYourAverageRating, rvAudienceAverageRating;
        public final ConstraintLayout rvConstraintDescription, rvConstraintMovie;
        public ViewHolder(View view){
            super(view);
            rvMovieImage = view.findViewById(R.id.rvMovieImage);
            rvCompositeRating = view.findViewById(R.id.rvCompositeRating);
            rvMovieTitle = view.findViewById(R.id.rvMovieTitle);
            rvMovieYear = view.findViewById(R.id.rvMovieYear);
            rvMovieDescription = view.findViewById(R.id.rvMovieDescription);
            rvVisualRatingValue = view.findViewById(R.id.rvVisualRatingValue);
            rvCastRatingValue = view.findViewById(R.id.rvCastRatingValue);
            rvPlotRatingValue = view.findViewById(R.id.rvPlotRatingValue);
            rvYourAverageRatingValue = view.findViewById(R.id.rvYourAverageRatingValue);
            rvAudienceAverageRatingValue = view.findViewById(R.id.rvAudienceAverageRatingValue);
            rvVisualRating = view.findViewById(R.id.rvVisualRating);
            rvCastRating = view.findViewById(R.id.rvCastRating);
            rvPlotRating = view.findViewById(R.id.rvPlotRating);
            rvYourAverageRating = view.findViewById(R.id.rvYourAverageRating);
            rvAudienceAverageRating = view.findViewById(R.id.rvAudienceAverageRating);
            rvConstraintDescription = view.findViewById(R.id.rvConstraintDescription);
            rvConstraintMovie = view.findViewById(R.id.rvConstraintMovie);
        }
    }
}
