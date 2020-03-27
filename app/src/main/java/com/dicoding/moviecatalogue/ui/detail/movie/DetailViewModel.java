package com.dicoding.moviecatalogue.ui.detail.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.vo.Resource;

public class DetailViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    private MutableLiveData<Long> idMovie = new MutableLiveData<>();

    LiveData<Resource<MovieEntity>> mvItem = Transformations.switchMap(idMovie, rMvId -> catalogueRepository.getMovieDetail(rMvId));

    public DetailViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    void setDetailMovie(long id) {
        this.idMovie.setValue(id);
    }

    void setFavoriteMovie() {
        catalogueRepository.favoritedMovie(mvItem.getValue().data);
    }

    public LiveData<Resource<MovieEntity>> getMovieDetail() {
        return mvItem;
    }


}
