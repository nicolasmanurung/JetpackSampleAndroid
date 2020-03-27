package com.dicoding.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.dicoding.moviecatalogue.data.source.local.LocalRepository;
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.data.source.remote.ApiResponse;
import com.dicoding.moviecatalogue.data.source.remote.RemoteRepository;
import com.dicoding.moviecatalogue.data.source.remote.response.MovieResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.TVShowResponse;
import com.dicoding.moviecatalogue.utils.AppExecutors;
import com.dicoding.moviecatalogue.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class FakeCatalogueRepository implements CatalogueDataResource {
    private volatile static FakeCatalogueRepository INSTANCE = null;
    private final RemoteRepository remoteRepository;
    private final AppExecutors appExecutors;
    private final LocalRepository localRepository;

    public FakeCatalogueRepository(RemoteRepository remoteRepository, AppExecutors appExecutors, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
        this.localRepository = localRepository;
    }

    public static FakeCatalogueRepository getInstance(RemoteRepository remoteData, AppExecutors appExecutors, LocalRepository localRepository) {
        if (INSTANCE == null) {
            synchronized (FakeCatalogueRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FakeCatalogueRepository(remoteData, appExecutors, localRepository);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getAllMovie() {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {

            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteRepository.loadAllMovies();
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                for (MovieResponse movieResponse : data) {
                    movieEntities.add(new MovieEntity(movieResponse));
                }
                localRepository.insertFavMovie(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovieDetail(long id) {
        return new NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getMovById(id);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return remoteRepository.loadDetailMovie(id);
            }

            @Override
            protected void saveCallResult(MovieResponse data) {
                MovieEntity movieEntity = new MovieEntity(data);
                List<MovieEntity> movieEntityList = new ArrayList<>();
                movieEntityList.add(movieEntity);
                localRepository.insertFavMovie(movieEntityList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TVShowEntity>>> getAllTVShow() {
        return new NetworkBoundResource<List<TVShowEntity>, List<TVShowResponse>>(appExecutors) {

            @Override
            protected LiveData<List<TVShowEntity>> loadFromDB() {
                return localRepository.getAllTVShows();
            }

            @Override
            protected Boolean shouldFetch(List<TVShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TVShowResponse>>> createCall() {
                return remoteRepository.loadAllTVShow();
            }

            @Override
            protected void saveCallResult(List<TVShowResponse> data) {
                List<TVShowEntity> tvShowEntityList = new ArrayList<>();
                for (TVShowResponse tvShowResponse : data) {
                    tvShowEntityList.add(new TVShowEntity(tvShowResponse));
                }
                localRepository.insertFavTVShow(tvShowEntityList);
            }
        }.asLiveData();
    }


    @Override
    public LiveData<Resource<TVShowEntity>> getTVShowDetail(long id) {
        return new NetworkBoundResource<TVShowEntity, TVShowResponse>(appExecutors) {

            @Override
            protected LiveData<TVShowEntity> loadFromDB() {
                return localRepository.getTVById(id);
            }

            @Override
            protected Boolean shouldFetch(TVShowEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<TVShowResponse>> createCall() {
                return remoteRepository.loadDetailTVShow(id);
            }

            @Override
            protected void saveCallResult(TVShowResponse data) {
                TVShowEntity tvShowEntity = new TVShowEntity(data);
                List<TVShowEntity> tvShowEntityList = new ArrayList<>();
                tvShowEntityList.add(tvShowEntity);
                localRepository.insertFavTVShow(tvShowEntityList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMovies() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavMov(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TVShowEntity>>> getFavoriteTVShows() {
        return new NetworkBoundResource<PagedList<TVShowEntity>, List<TVShowResponse>>(appExecutors) {
            @Override
            protected LiveData<PagedList<TVShowEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavTVShow(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TVShowEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TVShowResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TVShowResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public void favoritedMovie(MovieEntity movieEntity) {
        Runnable runnable = () -> localRepository.isFavoriteMov(movieEntity);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void favoritedTVShow(TVShowEntity tvShowEntity) {
        Runnable runnable = () -> localRepository.isFavoriteTV(tvShowEntity);
        appExecutors.diskIO().execute(runnable);
    }
}
