package com.dicoding.moviecatalogue.ui.favorite.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.vo.Resource;

public class FavTVViewModel extends ViewModel {
    private CatalogueRepository repository;

    public FavTVViewModel(CatalogueRepository catalogueRepository) {
        this.repository = catalogueRepository;
    }

    LiveData<Resource<PagedList<TVShowEntity>>> getFavTVShow() {
        return repository.getFavoriteTVShows();
    }
}
