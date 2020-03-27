package com.dicoding.moviecatalogue.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.di.Injection;
import com.dicoding.moviecatalogue.ui.detail.movie.DetailViewModel;
import com.dicoding.moviecatalogue.ui.detail.tvshow.DetailTvViewModel;
import com.dicoding.moviecatalogue.ui.favorite.movie.FavMovieViewModel;
import com.dicoding.moviecatalogue.ui.favorite.tvshow.FavTVViewModel;
import com.dicoding.moviecatalogue.ui.movie.MovieViewModel;
import com.dicoding.moviecatalogue.ui.tvshow.TVShowViewModel;


public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final CatalogueRepository catalogueRepository;

    private ViewModelFactory(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(TVShowViewModel.class)) {
            return (T) new TVShowViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(FavMovieViewModel.class)) {
            return (T) new FavMovieViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(FavTVViewModel.class)) {
            return (T) new FavTVViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(DetailTvViewModel.class)) {
            return (T) new DetailTvViewModel(catalogueRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
