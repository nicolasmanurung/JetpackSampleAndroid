package com.dicoding.moviecatalogue.ui.movie;

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

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MovieViewModel viewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);

    @Before
    public void setUp() {
        viewModel = new MovieViewModel(catalogueRepository);
    }

    @Test
    public void getMovies() {
        Resource<List<MovieEntity>> dummyMovie = Resource.success(FakeDataDummy.generateDummyMovie());
        MutableLiveData<Resource<List<MovieEntity>>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);
        when(catalogueRepository.getAllMovie()).thenReturn(movies);
        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);
        viewModel.getMovieList().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }
}