package com.dicoding.moviecatalogue.data.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;

import java.util.List;

@Dao
public interface CatalogueDao {

    @Query("SELECT * FROM favmoventity")
    LiveData<List<MovieEntity>> getAllMovies();

    @Query("SELECT * FROM favmoventity WHERE isfavorite = 1")
    DataSource.Factory<Integer, MovieEntity> getFavMovies();

    @Query("SELECT * FROM favmoventity WHERE id = :id")
    LiveData<MovieEntity> getMovieWithId(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(List<MovieEntity> favMovieEntity);

    @Delete()
    void deleteMovie(MovieEntity favMovieEntity);

    @Update()
    void updateMovie(MovieEntity favMovieEntity);

    @Query("SELECT * FROM favtventity")
    LiveData<List<TVShowEntity>> getAllTVShows();

    @Query("SELECT * FROM favtventity WHERE isfavorite = 1")
    DataSource.Factory<Integer, TVShowEntity> getFavTVShows();

    @Query("SELECT * FROM favtventity WHERE id = :id")
    LiveData<TVShowEntity> getTVShowWithId(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTVShows(List<TVShowEntity> favTVShowEntity);

    @Delete()
    void deleteTVSHows(TVShowEntity favTVEntity);

    @Update
    void updateTVShow(TVShowEntity favTVShowEntity);
}
