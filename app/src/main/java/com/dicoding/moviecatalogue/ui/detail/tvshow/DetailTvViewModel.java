package com.dicoding.moviecatalogue.ui.detail.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.vo.Resource;

public class DetailTvViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    private MutableLiveData<Long> idTVShow = new MutableLiveData<>();

    private LiveData<Resource<TVShowEntity>> tvItem = Transformations.switchMap(idTVShow, rTVId -> catalogueRepository.getTVShowDetail(rTVId));

    public DetailTvViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    void setDetailTV(long id) {
        this.idTVShow.setValue(id);
    }

    LiveData<Resource<TVShowEntity>> getTVDetail() {
        return tvItem;
    }

    //Make favorite
    void setFavoriteTVShow() {
        catalogueRepository.favoritedTVShow(tvItem.getValue().data);
    }
}
