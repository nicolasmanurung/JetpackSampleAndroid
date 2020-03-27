package com.dicoding.moviecatalogue.utils;


import com.dicoding.moviecatalogue.BuildConfig;
import com.dicoding.moviecatalogue.data.source.remote.response.MovieResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.RetrofitMovResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.RetrofitTVShowResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetService {

    @GET("discover/movie?api_key=" + BuildConfig.API_URL_KEY)
    Call<RetrofitMovResponse> getMovies();

    @GET("movie/{id}?api_key=" + BuildConfig.API_URL_KEY)
    Call<MovieResponse> getMovieWithId(@Path("id") long id);

    @GET("discover/tv?api_key=" + BuildConfig.API_URL_KEY)
    Call<RetrofitTVShowResponse> getTVShows();

    @GET("tv/{id}?api_key=" + BuildConfig.API_URL_KEY)
    Call<TVShowResponse> getTVShowWithId(@Path("id") long id);
}
