package com.dicoding.moviecatalogue.ui.favorite.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavMovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private FavMovieViewModel favMovieViewModel;
    private CatalogueRepository catalogueRepository;

    @Before
    public void setUp() {
        catalogueRepository = Mockito.mock(CatalogueRepository.class);
        favMovieViewModel = new FavMovieViewModel(catalogueRepository);
    }

    @Test
    public void getFavMovie() {
        MutableLiveData<Resource<PagedList<MovieEntity>>> resourceMutableLiveData = new MutableLiveData<>();
        PagedList<MovieEntity> entityPagedList = Mockito.mock(PagedList.class);
        resourceMutableLiveData.setValue(Resource.success(entityPagedList));
        when(catalogueRepository.getFavoriteMovies()).thenReturn(resourceMutableLiveData);
        Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);
        favMovieViewModel.getFavMovies().observeForever(observer);
        verify(observer).onChanged(Resource.success(entityPagedList));
    }
}