package com.dicoding.moviecatalogue.ui.detail.movie;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.utils.FakeDataDummy;
import com.dicoding.moviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DetailViewModel viewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private Resource<MovieEntity> movieDummy = Resource.success(FakeDataDummy.generateDummyMovieDetail());

    @Before
    public void setUp() {
        viewModel = new DetailViewModel(catalogueRepository);
        viewModel.setDetailMovie(movieDummy.data.getMvId());
    }

    @Test
    public void getDetailMovie() {
        MutableLiveData<Resource<MovieEntity>> mvItem = new MutableLiveData<>();
        mvItem.setValue(movieDummy);

        when(catalogueRepository.getMovieDetail(movieDummy.data.getMvId())).thenReturn(mvItem);
        Observer<Resource<MovieEntity>> observer = mock(Observer.class);
        viewModel.mvItem.observeForever(observer);
        verify(observer).onChanged(movieDummy);
    }
}