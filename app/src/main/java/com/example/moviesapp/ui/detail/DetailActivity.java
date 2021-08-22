package com.example.moviesapp.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieResponse;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.ivPoster)
    ImageView ivPoster;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvYear)
    TextView tvYear;
    @BindView(R.id.tvReleased)
    TextView tvReleased;
    @BindView(R.id.tvImdbRating)
    TextView tvImdbRating;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvGenre)
    TextView tvGenre;
    @BindView(R.id.tvActors)
    TextView tvActors;
    @BindView(R.id.tvPlot)
    TextView tvPlot;
    @BindView(R.id.tvProduction)
    TextView tvProduction;

    private DetailPresenter detailPresenter;
    private MovieResponse movieObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String movieStr = intent.getStringExtra("movie");
        movieObj = DetailPresenter.stringToObject(movieStr, MovieResponse.class);
        init();
    }

    public void init() {
        detailPresenter = new DetailPresenter(this);
        Picasso.get().load(movieObj.getPoster()).into(ivPoster);

        tvTitle.setText(movieObj.getTitle());
        tvYear.setText(getString(R.string.year_title) + movieObj.getYear());
        tvReleased.setText(getString(R.string.released_title) + movieObj.getReleased());
        tvImdbRating.setText(getString(R.string.imdb_rating_title) + movieObj.getImdbRating());
        tvType.setText(getString(R.string.type_title) + movieObj.getType());
        tvGenre.setText(getString(R.string.genre_title) + movieObj.getGenre());
        tvActors.setText(getString(R.string.actors_title) + movieObj.getActors());
        tvPlot.setText(getString(R.string.plot_title) + movieObj.getPlot());
        tvProduction.setText(getString(R.string.production_title) + movieObj.getProduction());
    }

}
