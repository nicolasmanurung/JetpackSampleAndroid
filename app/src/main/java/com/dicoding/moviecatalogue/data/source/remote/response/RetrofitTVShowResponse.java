package com.dicoding.moviecatalogue.data.source.remote.response;

import java.util.ArrayList;

public class RetrofitTVShowResponse {
    private ArrayList<TVShowResponse> results;

    public RetrofitTVShowResponse(ArrayList<TVShowResponse> tvShowResponses) {
        this.results = tvShowResponses;
    }

    public ArrayList<TVShowResponse> getTVShows() {
        return results;
    }
}
