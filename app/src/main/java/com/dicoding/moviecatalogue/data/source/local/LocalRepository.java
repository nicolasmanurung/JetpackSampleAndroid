package com.dicoding.moviecatalogue.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.data.source.local.room.CatalogueDao;

import java.util.List;

public class LocalRepository {
    private static LocalRepository INSTANCE;
    private final CatalogueDao catalogueDao;

    private LocalRepository(CatalogueDao catalogueDao) {
        this.catalogueDao = catalogueDao;
    }

    public static LocalRepository getInstance(CatalogueDao catalogueDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(catalogueDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getAllMovies() {
        return catalogueDao.getAllMovies();
    }

    public LiveData<MovieEntity> getMovById(long id) {
        return catalogueDao.getMovieWithId(id);
    }

    public DataSource.Factory<Integer, MovieEntity> getFavMov() {
        return catalogueDao.getFavMovies();
    }

    public void insertFavMovie(List<MovieEntity> favMovieEntity) {
        catalogueDao.insertMovie(favMovieEntity);
    }


    public void update(MovieEntity movieEntity) {
        catalogueDao.updateMovie(movieEntity);
    }

    public void isFavoriteMov(MovieEntity movieEntity) {
        movieEntity.favorite();
        update(movieEntity);
    }

    public LiveData<List<TVShowEntity>> getAllTVShows() {
        return catalogueDao.getAllTVShows();
    }

    public LiveData<TVShowEntity> getTVById(long id) {
        return catalogueDao.getTVShowWithId(id);
    }

    public DataSource.Factory<Integer, TVShowEntity> getFavTVShow() {
        return catalogueDao.getFavTVShows();
    }

    public void insertFavTVShow(List<TVShowEntity> favTVSHowEntity) {
        catalogueDao.insertTVShows(favTVSHowEntity);
    }

    public void update(TVShowEntity tvShowEntity) {
        catalogueDao.updateTVShow(tvShowEntity);
    }

    public void isFavoriteTV(TVShowEntity tvShowEntity) {
        tvShowEntity.favorite();
        update(tvShowEntity);
    }
}
