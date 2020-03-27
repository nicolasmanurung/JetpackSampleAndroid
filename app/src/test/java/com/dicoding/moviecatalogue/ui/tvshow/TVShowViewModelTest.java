package com.dicoding.moviecatalogue.ui.tvshow;

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

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TVShowViewModel viewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private Resource<TVShowEntity> tvShowEntityResource = Resource.success(FakeDataDummy.generateDummyTVDetail());

    @Before
    public void setUp() {
        viewModel = new TVShowViewModel(catalogueRepository);

    }

    @Test
    public void getTVShow() {
        Resource<List<TVShowEntity>> dummyMovie = Resource.success(FakeDataDummy.generateDummyTVShow());
        MutableLiveData<Resource<List<TVShowEntity>>> tvshow = new MutableLiveData<>();
        tvshow.setValue(dummyMovie);
        when(catalogueRepository.getAllTVShow()).thenReturn(tvshow);
        Observer<Resource<List<TVShowEntity>>> observer = mock(Observer.class);
        viewModel.getTVShowList().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }

}