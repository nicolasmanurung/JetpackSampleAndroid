package com.dicoding.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.vo.Resource;

import java.util.List;

public interface CatalogueDataResource {

    LiveData<Resource<List<MovieEntity>>> getAllMovie();

    LiveData<Resource<List<TVShowEntity>>> getAllTVShow();

    LiveData<Resource<MovieEntity>> getMovieDetail(long id);

    LiveData<Resource<TVShowEntity>> getTVShowDetail(long id);

    LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMovies();

    LiveData<Resource<PagedList<TVShowEntity>>> getFavoriteTVShows();

    void favoritedMovie(MovieEntity movieEntity);

    void favoritedTVShow(TVShowEntity tvShowEntity);
}
