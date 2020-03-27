package com.dicoding.moviecatalogue.data.source.remote.response;

import java.util.ArrayList;

public class RetrofitMovResponse {
    private ArrayList<MovieResponse> results;

    public RetrofitMovResponse(ArrayList<MovieResponse> movieResponses) {
        this.results = movieResponses;
    }

    public ArrayList<MovieResponse> getMovies() {
        return results;
    }
}
