package com.dicoding.moviecatalogue.utils;

import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.data.source.remote.response.MovieResponse;
import com.dicoding.moviecatalogue.data.source.remote.response.TVShowResponse;

import java.util.ArrayList;

public class FakeDataDummy {
    public static ArrayList<MovieEntity> generateDummyMovie() {
        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity(1,
                "The Lion King",
                "7.2",
                "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
                "19 Juli 2019",
                "en",
                "https://image.tmdb.org/t/p/w185/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
                false));

        movies.add(new MovieEntity(2,
                "The Lion King",
                "7.2",
                "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
                "19 Juli 2019",
                "en",
                "https://image.tmdb.org/t/p/w185/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
                false));

        movies.add(new MovieEntity(3,
                "The Lion King",
                "7.2",
                "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
                "19 Juli 2019",
                "en",
                "https://image.tmdb.org/t/p/w185/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
                false));

        movies.add(new MovieEntity(4,
                "The Lion King",
                "7.2",
                "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
                "19 Juli 2019",
                "en",
                "https://image.tmdb.org/t/p/w185/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
                false));

        movies.add(new MovieEntity(5,
                "The Lion King",
                "7.2",
                "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
                "19 Juli 2019",
                "en",
                "https://image.tmdb.org/t/p/w185/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
                false));

        return movies;
    }

    public static ArrayList<MovieResponse> generateRemoteDummyMovie() {
        ArrayList<MovieResponse> movies = new ArrayList<>();

        movies.add(new MovieResponse(1,
                "Star Wars",
                "8.4",
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
                "25 May 1977",
                "en",
                "https://image.tmdb.org/t/p/w185/mEpQbsUSekbQRdffXMeQWjeHb34.jpg"));

        movies.add(new MovieResponse(2,
                "Star Wars",
                "8.4",
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
                "25 May 1977",
                "en",
                "https://image.tmdb.org/t/p/w185/mEpQbsUSekbQRdffXMeQWjeHb34.jpg"));

        movies.add(new MovieResponse(3,
                "Star Wars",
                "8.4",
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
                "25 May 1977",
                "en",
                "https://image.tmdb.org/t/p/w185/mEpQbsUSekbQRdffXMeQWjeHb34.jpg"));

        movies.add(new MovieResponse(4,
                "Star Wars",
                "8.4",
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
                "25 May 1977",
                "en",
                "https://image.tmdb.org/t/p/w185/mEpQbsUSekbQRdffXMeQWjeHb34.jpg"));

        movies.add(new MovieResponse(5,
                "Star Wars",
                "8.4",
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
                "25 May 1977",
                "en",
                "https://image.tmdb.org/t/p/w185/mEpQbsUSekbQRdffXMeQWjeHb34.jpg"));
        return movies;
    }

    public static ArrayList<TVShowEntity> generateDummyTVShow() {
        ArrayList<TVShowEntity> tvshows = new ArrayList<>();

        tvshows.add(new TVShowEntity(1,
                "Arrow",
                "5.8",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Oktober 10, 2012",
                "en",
                "https://image.tmdb.org/t/p/w185/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                false));

        tvshows.add(new TVShowEntity(2,
                "Arrow",
                "5.8",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Oktober 10, 2012",
                "en",
                "https://image.tmdb.org/t/p/w185/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                false));

        tvshows.add(new TVShowEntity(3,
                "Arrow",
                "5.8",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Oktober 10, 2012",
                "en",
                "https://image.tmdb.org/t/p/w185/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                false));

        tvshows.add(new TVShowEntity(4,
                "Arrow",
                "5.8",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Oktober 10, 2012",
                "en",
                "https://image.tmdb.org/t/p/w185/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                false));

        tvshows.add(new TVShowEntity(5,
                "Arrow",
                "5.8",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Oktober 10, 2012",
                "en",
                "https://image.tmdb.org/t/p/w185/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                false));
        return tvshows;
    }

    public static ArrayList<TVShowResponse> generatedRemoteDummyTVShow() {
        ArrayList<TVShowResponse> tvshows = new ArrayList<>();

        tvshows.add(new TVShowResponse(1,
                "The Boys",
                "6.5",
                "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                "July 25, 2019",
                "en",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/dzOxNbbz1liFzHU1IPvdgUR647b.jpg"));

        tvshows.add(new TVShowResponse(2,
                "The Boys",
                "6.5",
                "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                "July 25, 2019",
                "en",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/dzOxNbbz1liFzHU1IPvdgUR647b.jpg"));

        tvshows.add(new TVShowResponse(3,
                "The Boys",
                "6.5",
                "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                "July 25, 2019",
                "en",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/dzOxNbbz1liFzHU1IPvdgUR647b.jpg"));

        tvshows.add(new TVShowResponse(4,
                "The Boys",
                "6.5",
                "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                "July 25, 2019",
                "en",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/dzOxNbbz1liFzHU1IPvdgUR647b.jpg"));

        tvshows.add(new TVShowResponse(5,
                "The Boys",
                "6.5",
                "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                "July 25, 2019",
                "en",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/dzOxNbbz1liFzHU1IPvdgUR647b.jpg"));
        return tvshows;
    }

    public static TVShowEntity generateDummyTVDetail() {
        return new TVShowEntity(4,
                "Arrow",
                "5.8",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Oktober 10, 2012",
                "en",
                "https://image.tmdb.org/t/p/w185/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                false);
    }

    public static TVShowResponse generateRemoteDummyTVDetail() {
        return new TVShowResponse(4,
                "Arrow",
                "5.8",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Oktober 10, 2012",
                "en",
                "https://image.tmdb.org/t/p/w185/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg");
    }


    public static MovieEntity generateDummyMovieDetail() {
        return new MovieEntity(3,
                "Star Wars",
                "8.4",
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
                "25 May 1977",
                "en",
                "https://image.tmdb.org/t/p/w185/mEpQbsUSekbQRdffXMeQWjeHb34.jpg",
                false);
    }

    public static MovieResponse generateRemoteDummyMovieDetail() {
        return new MovieResponse(3,
                "Star Wars",
                "8.4",
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
                "25 May 1977",
                "en",
                "https://image.tmdb.org/t/p/w185/mEpQbsUSekbQRdffXMeQWjeHb34.jpg");
    }
}
