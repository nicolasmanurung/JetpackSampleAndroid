package com.dicoding.moviecatalogue.ui.favorite.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavTVViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private FavTVViewModel favTVViewModel;
    private CatalogueRepository catalogueRepository;

    @Before
    public void setUp() {
        catalogueRepository = mock(CatalogueRepository.class);
        favTVViewModel = new FavTVViewModel(catalogueRepository);
    }

    @Test
    public void getFavTVShow() {
        MutableLiveData<Resource<PagedList<TVShowEntity>>> resourceMutableLiveData = new MutableLiveData<>();
        PagedList<TVShowEntity> entityPagedList = mock(PagedList.class);
        resourceMutableLiveData.setValue(Resource.success(entityPagedList));
        when(catalogueRepository.getFavoriteTVShows()).thenReturn(resourceMutableLiveData);
        Observer<Resource<PagedList<TVShowEntity>>> observer = mock(Observer.class);
        favTVViewModel.getFavTVShow().observeForever(observer);
        verify(observer).onChanged(Resource.success(entityPagedList));

    }
}