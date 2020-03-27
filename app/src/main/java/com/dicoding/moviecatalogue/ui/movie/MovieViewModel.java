package com.dicoding.moviecatalogue.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.vo.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    public MovieViewModel(CatalogueRepository repository) {
        this.catalogueRepository = repository;
    }

    LiveData<Resource<List<MovieEntity>>> getMovieList() {
        return catalogueRepository.getAllMovie();
    }
}
