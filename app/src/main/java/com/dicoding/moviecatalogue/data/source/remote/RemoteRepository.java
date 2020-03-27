package com.dicoding.moviecatalogue.data.source.remote;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.moviecatalogue.data.source.remote.response.MovieResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.RetrofitMovResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.RetrofitTVShowResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.TVShowResponse;
import com.dicoding.moviecatalogue.utils.ApiClient;
import com.dicoding.moviecatalogue.utils.EspressoIdlingResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private ApiClient apiClient;

    private RemoteRepository(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public static RemoteRepository getInstance(ApiClient apiClient) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(apiClient);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieResponse>>> loadAllMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieResponse>>> rMovie = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> apiClient.getMovies().enqueue(new Callback<RetrofitMovResponse>() {
            @Override
            public void onResponse(@NonNull Call<RetrofitMovResponse> call, @NonNull Response<RetrofitMovResponse> response) {
                assert response.body() != null;
                rMovie.setValue(ApiResponse.success(response.body().getMovies()));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow())
                    EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<RetrofitMovResponse> call, Throwable t) {
                EspressoIdlingResource.decrement();
            }
        }), 2000);
        return rMovie;
    }

    public LiveData<ApiResponse<MovieResponse>> loadDetailMovie(long id) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<MovieResponse>> rMovie = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> apiClient.getMovieWithId(id).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                rMovie.setValue(ApiResponse.success(response.body()));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow())
                    EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                EspressoIdlingResource.decrement();
            }
        }), 2000);
        return rMovie;
    }

    public LiveData<ApiResponse<List<TVShowResponse>>> loadAllTVShow() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TVShowResponse>>> rTVShow = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> apiClient.getTVShows().enqueue(new Callback<RetrofitTVShowResponse>() {
            @Override
            public void onResponse(Call<RetrofitTVShowResponse> call, Response<RetrofitTVShowResponse> response) {
                assert response.body() != null;
                rTVShow.setValue(ApiResponse.success(response.body().getTVShows()));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow())
                    EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<RetrofitTVShowResponse> call, Throwable t) {
                EspressoIdlingResource.decrement();
            }
        }), 2000);
        return rTVShow;
    }

    public LiveData<ApiResponse<TVShowResponse>> loadDetailTVShow(long id) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<TVShowResponse>> rTVShow = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> apiClient.getTVShowWithId(id).enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                rTVShow.setValue(ApiResponse.success(response.body()));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow())
                    EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {
                EspressoIdlingResource.decrement();
            }
        }), 2000);
        return rTVShow;
    }
}

