package com.dicoding.moviecatalogue.utils;

import com.dicoding.moviecatalogue.BuildConfig;
import com.dicoding.moviecatalogue.data.source.remote.response.MovieResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.RetrofitMovResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.RetrofitTVShowResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.TVShowResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient jsonHelper = new ApiClient();
    private static GetService service;
    private static Retrofit retrofit;

    private ApiClient() {
        Retrofit retrofit = getRetrofitInstance();
        service = retrofit.create(GetService.class);
    }

    private Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiClient getInstance() {
        return jsonHelper;
    }

    public Call<RetrofitMovResponse> getMovies() {
        return service.getMovies();
    }

    public Call<MovieResponse> getMovieWithId(long id) {
        return service.getMovieWithId(id);
    }

    public Call<RetrofitTVShowResponse> getTVShows() {
        return service.getTVShows();
    }

    public Call<TVShowResponse> getTVShowWithId(long id) {
        return service.getTVShowWithId(id);
    }
}
