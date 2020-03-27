package com.dicoding.moviecatalogue.ui.favorite.movie;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.moviecatalogue.R;
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovie extends Fragment {
    private FavMovieAdapter movieAdapter;
    private RecyclerView rvMovie;
    private ProgressBar progressBar;

    public FavoriteMovie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progressBarFavMov);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            FavMovieViewModel viewModel = obtainViewModel(getActivity(), this);
            movieAdapter = new FavMovieAdapter();
            viewModel.getFavMovies().observe(this, pagedListResource -> {
                if (pagedListResource != null) {
                    switch (pagedListResource.status) {
                        case LOADING:
                            rvMovie.setVisibility(View.GONE);
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            rvMovie.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            movieAdapter.submitList(pagedListResource.data);
                            movieAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), R.string.errorToast, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            rvMovie.setAdapter(movieAdapter);
        }
    }

    @NonNull
    private FavMovieViewModel obtainViewModel(FragmentActivity activity, Fragment fragment) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(fragment, factory).get(FavMovieViewModel.class);
    }
}
