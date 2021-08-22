package com.example.moviesapp.service;

import com.example.moviesapp.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("?apikey=da7a6543")
    Call<MovieResponse> getMovie(@Query("t") String movieName);
}
