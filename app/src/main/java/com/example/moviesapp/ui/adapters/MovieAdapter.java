package com.example.moviesapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieResponse;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final List<MovieResponse> moviesList;
    public Context context;
    public ItemClickListener itemClickListener;

    public MovieAdapter(List<MovieResponse> movieResponse, Context context) {
        this.moviesList = movieResponse;
        this.context = context;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvMovieTitle.setText(moviesList.get(position).getTitle());
        holder.tvMoviePlot.setText(moviesList.get(position).getPlot());
        holder.tvMovieRating.setText(moviesList.get(position).getImdbRating());
        holder.tvMovieReleased.setText(moviesList.get(position).getReleased());
        holder.tvMovieActors.setText(context.getString(R.string.actors_title) + " " + moviesList.get(position).getActors());

        Picasso.get().load(moviesList.get(position).getPoster()).into(holder.ivMoviePoster);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public interface ItemClickListener {
        void onClick(MovieResponse clickedMovie);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivMoviePoster;
        public TextView tvMovieTitle, tvMovieRating, tvMovieActors, tvMovieReleased, tvMoviePlot;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivMoviePoster = itemView.findViewById(R.id.ivMoviePoster);
            this.tvMovieTitle = itemView.findViewById(R.id.tvMovieTitle);
            this.tvMovieRating = itemView.findViewById(R.id.tvMovieRating);
            this.tvMovieActors = itemView.findViewById(R.id.tvMovieActors);
            this.tvMovieReleased = itemView.findViewById(R.id.tvMovieReleased);
            this.tvMoviePlot = itemView.findViewById(R.id.tvMoviePlot);

            itemView.setOnClickListener(v -> {
                MovieResponse clickedResponse = moviesList.get(getAdapterPosition());
                itemClickListener.onClick(clickedResponse);
            });


        }
    }
}

