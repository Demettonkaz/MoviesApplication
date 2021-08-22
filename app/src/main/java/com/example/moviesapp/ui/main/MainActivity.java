package com.example.moviesapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieResponse;
import com.example.moviesapp.ui.adapters.MovieAdapter;
import com.example.moviesapp.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ItemClickListener {

    @BindView(R.id.etSearchMovie)
    TextView etSearchMovie;

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private MovieAdapter movieAdapter;
    private MainPresenter mainPresenter;
    private List<MovieResponse> moviesList = new ArrayList<>();


    @OnClick(R.id.btnSearch)
    public void onClickSearch() {
        progressBar.setVisibility(View.VISIBLE);
        String searchMovie = etSearchMovie.getText().toString();
        if (searchMovie.length() >= 3)
            fetchMovie(searchMovie);

        new Handler().postDelayed(() -> {
            moviesList = mainPresenter.getMovies();
            setMovieAdapter(moviesList);
        }, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);
    }

    public void setMovieAdapter(List<MovieResponse> moviesList) {
        progressBar.setVisibility(View.GONE);
        movieAdapter = new MovieAdapter(moviesList, MainActivity.this);
        movieAdapter.setItemClickListener(MainActivity.this);
        rvMovies.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvMovies.setAdapter(movieAdapter);
    }

    public void fetchMovie(String searchMovie) {
        mainPresenter.fetchMovies(searchMovie);
    }

    @Override
    public void onClick(MovieResponse clickedMovie) {
        String sendMovieStr = MainPresenter.objectToString(clickedMovie);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("movie", sendMovieStr);
        startActivity(intent);
    }
}