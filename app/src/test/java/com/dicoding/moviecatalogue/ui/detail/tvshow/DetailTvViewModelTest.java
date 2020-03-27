package com.dicoding.moviecatalogue.ui.detail.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.utils.FakeDataDummy;
import com.dicoding.moviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailTvViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DetailTvViewModel viewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private Resource<TVShowEntity> tvshowDummy = Resource.success(FakeDataDummy.generateDummyTVDetail());

    @Before
    public void setUp() {
        viewModel = new DetailTvViewModel(catalogueRepository);
        viewModel.setDetailTV(tvshowDummy.data.getId());
    }

    @Test
    public void getDetailTV() {
        MutableLiveData<Resource<TVShowEntity>> tvItem = new MutableLiveData<>();
        tvItem.setValue(tvshowDummy);
        when(catalogueRepository.getTVShowDetail(tvshowDummy.data.getId())).thenReturn(tvItem);
        Observer<Resource<TVShowEntity>> observer = mock(Observer.class);
        viewModel.getTVDetail().observeForever(observer);
        verify(observer).onChanged(tvshowDummy);
    }

}