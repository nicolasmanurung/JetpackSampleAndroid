package com.dicoding.moviecatalogue.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.vo.Resource;

import java.util.List;

public class TVShowViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    public TVShowViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<Resource<List<TVShowEntity>>> getTVShowList() {
        return catalogueRepository.getAllTVShow();
    }
}
