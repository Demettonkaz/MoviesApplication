package com.example.moviesapp.ui.main;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieResponse;
import com.example.moviesapp.service.ApiClient;
import com.example.moviesapp.service.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private static ApiInterface apiInterface;
    private final Context context;
    List<MovieResponse> movieResponseList = new ArrayList<>();
    private MovieResponse moviesList;

    public MainPresenter(Context context) {
        this.context = context;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static String objectToString(Object o) {
        try {
            GsonBuilder builder = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .serializeSpecialFloatingPointValues();
            Gson gson = builder.create();
            return gson.toJson(o);
        } catch (Exception error) {
            return "";
        }
    }

    public List<MovieResponse> getMovies() {
        return movieResponseList;
    }


    public void fetchMovies(String movieTitle) {
        Call<MovieResponse> call = apiInterface.getMovie(movieTitle);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse responseBody = response.body();
                moviesList = responseBody;
                movieResponseList = Arrays.asList(moviesList);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
