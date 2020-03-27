package com.dicoding.moviecatalogue.ui.favorite.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.vo.Resource;

public class FavMovieViewModel extends ViewModel {
    private CatalogueRepository repository;

    public FavMovieViewModel(CatalogueRepository repository) {
        this.repository = repository;
    }

    LiveData<Resource<PagedList<MovieEntity>>> getFavMovies() {
        return repository.getFavoriteMovies();
    }
}
