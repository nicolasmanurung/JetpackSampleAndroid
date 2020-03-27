package com.dicoding.moviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.dicoding.moviecatalogue.data.source.local.LocalRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.data.source.remote.RemoteRepository;
import com.dicoding.moviecatalogue.data.source.remote.response.MovieResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.TVShowResponse;
import com.dicoding.moviecatalogue.utils.FakeDataDummy;
import com.dicoding.moviecatalogue.utils.InstantAppExecutors;
import com.dicoding.moviecatalogue.utils.LiveDataTestUtil;
import com.dicoding.moviecatalogue.utils.PagedListUtil;
import com.dicoding.moviecatalogue.vo.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CatalogueRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remote = Mockito.mock(RemoteRepository.class);
    private LocalRepository localRepository = mock(LocalRepository.class);
    private InstantAppExecutors appExecutors = mock(InstantAppExecutors.class);
    private FakeCatalogueRepository catalogueRepository = new FakeCatalogueRepository(remote, appExecutors, localRepository);

    private List<MovieResponse> movieResponses = FakeDataDummy.generateRemoteDummyMovie();
    private List<TVShowResponse> tvShowResponses = FakeDataDummy.generatedRemoteDummyTVShow();

    private MovieResponse movieResponse = FakeDataDummy.generateRemoteDummyMovieDetail();
    private TVShowResponse tvShowResponse = FakeDataDummy.generateRemoteDummyTVDetail();


    @Test
    public void getListMovie() {
        MutableLiveData<List<MovieEntity>> movieDummy = new MutableLiveData<>();
        movieDummy.setValue(FakeDataDummy.generateDummyMovie());
        when(localRepository.getAllMovies()).thenReturn(movieDummy);
        Resource<List<MovieEntity>> rMovie = LiveDataTestUtil.getValue(catalogueRepository.getAllMovie());
        verify(localRepository).getAllMovies();

        assertNotNull(rMovie.data);
        assertEquals(movieResponses.size(), rMovie.data.size());
    }

    @Test
    public void getListTVShow() {
        MutableLiveData<List<TVShowEntity>> tvDummy = new MutableLiveData<>();
        tvDummy.setValue(FakeDataDummy.generateDummyTVShow());
        when(localRepository.getAllTVShows()).thenReturn(tvDummy);
        Resource<List<TVShowEntity>> rTVShow = LiveDataTestUtil.getValue(catalogueRepository.getAllTVShow());
        verify(localRepository).getAllTVShows();

        assertNotNull(rTVShow.data);
        assertEquals(tvShowResponses.size(), rTVShow.data.size());
    }

    @Test
    public void getDetailMovie() {
        MutableLiveData<MovieEntity> mvDummy = new MutableLiveData<>();
        mvDummy.setValue(FakeDataDummy.generateDummyMovieDetail());
        when(localRepository.getMovById(movieResponse.getMvId())).thenReturn(mvDummy);
        Resource<MovieEntity> rMovie = LiveDataTestUtil.getValue(catalogueRepository.getMovieDetail(movieResponse.getMvId()));
        verify(localRepository).getMovById(movieResponse.getMvId());

        assertNotNull(rMovie);
        assertNotNull(rMovie.data);
        assertEquals(rMovie.data.getMvId(), movieResponse.getMvId());
    }

    @Test
    public void getDetailTVShow() {
        MutableLiveData<TVShowEntity> tvDummy = new MutableLiveData<>();
        tvDummy.setValue(FakeDataDummy.generateDummyTVDetail());
        when(localRepository.getTVById(tvShowResponse.getId())).thenReturn(tvDummy);
        Resource<TVShowEntity> rTVShow = LiveDataTestUtil.getValue(catalogueRepository.getTVShowDetail(tvShowResponse.getId()));
        verify(localRepository).getTVById(tvShowResponse.getId());

        assertNotNull(rTVShow);
        assertNotNull(rTVShow.data);
        assertEquals(tvShowResponse.getId(), rTVShow.data.getId());
    }

    @Test
    public void getFavMovie() {
        DataSource.Factory<Integer, MovieEntity> dataFactory = mock(DataSource.Factory.class);

        when(localRepository.getFavMov()).thenReturn(dataFactory);

        catalogueRepository.getFavoriteMovies();
        Resource<PagedList<MovieEntity>> rMovie = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.generateDummyMovie()));

        verify(localRepository).getFavMov();
        assertNotNull(rMovie.data);
        assertEquals(FakeDataDummy.generateDummyMovie().size(), rMovie.data.size());

    }

    @Test
    public void getFavTVShow() {
        DataSource.Factory<Integer, TVShowEntity> dataFactory = mock(DataSource.Factory.class);

        when(localRepository.getFavTVShow()).thenReturn(dataFactory);

        catalogueRepository.getFavoriteTVShows();
        Resource<PagedList<TVShowEntity>> rTVShow = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.generateDummyTVShow()));

        verify(localRepository).getFavTVShow();
        assertNotNull(rTVShow.data);
        assertEquals(FakeDataDummy.generateDummyTVShow().size(), rTVShow.data.size());
    }
}