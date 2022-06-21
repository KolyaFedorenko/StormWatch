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
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.movieView.setImagePath(movie.getImagePath());
        holder.movieView.setCompositeRating(movie.getCompositeRating());
        holder.movieView.setMovieTitle(movie.getTitle());
        holder.movieView.setMovieYear(movie.getYear());
        holder.movieView.setMovieDescription(movie.getDescription());
        holder.movieView.setVisualRating(movie.getVisualRating());
        holder.movieView.setCastRating(movie.getCastRating());
        holder.movieView.setPlotRating(movie.getPlotRating());
        holder.movieView.setAudienceRating(movie.getUsersAverageRating());

        holder.movieView.setMovieLongClickListener(new MovieView.MovieLongClickListener() {
            @Override
            public void onMovieLongClicked() {
                movieAdapterInterface.onItemLongPressed(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final MovieView movieView;
        public ViewHolder(View view){
            super(view);
            movieView = view.findViewById(R.id.movieView);
        }
    }
}
