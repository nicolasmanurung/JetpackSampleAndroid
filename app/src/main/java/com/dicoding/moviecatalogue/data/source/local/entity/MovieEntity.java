package com.dicoding.moviecatalogue.data.source.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dicoding.moviecatalogue.data.source.remote.response.MovieResponse;

@Entity(tableName = "favmoventity")
public class MovieEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private long mvId;

    @ColumnInfo(name = "name")
    private String mvName;

    @ColumnInfo(name = "rate")
    private String mvRate;

    @ColumnInfo(name = "description")
    private String mvDescription;

    @ColumnInfo(name = "date")
    private String mvDate;

    @ColumnInfo(name = "language")
    private String mvOriLang;

    @ColumnInfo(name = "photo")
    private String mvPhoto;

    @ColumnInfo(name = "isfavorite")
    private boolean isFavorite = false;

    public MovieEntity(MovieResponse movieResponse) {
        this.mvId = movieResponse.getMvId();
        this.mvName = movieResponse.getMvName();
        this.mvRate = movieResponse.getMvRate();
        this.mvDescription = movieResponse.getMvDescription();
        this.mvDate = movieResponse.getMvDate();
        this.mvOriLang = movieResponse.getMvOri_lang();
        this.mvPhoto = movieResponse.getMvPhoto();
        this.isFavorite = false;
    }

    public MovieEntity(long mvId, String mvName, String mvRate, String mvDescription, String mvDate, String mvOriLang, String mvPhoto, Boolean isFavorite) {
        this.mvId = mvId;
        this.mvName = mvName;
        this.mvRate = mvRate;
        this.mvDescription = mvDescription;
        this.mvDate = mvDate;
        this.mvOriLang = mvOriLang;
        this.mvPhoto = mvPhoto;
        if (isFavorite != null) {
            this.isFavorite = isFavorite;
        }
    }

    public long getMvId() {
        return mvId;
    }

    public String getMvName() {
        return mvName;
    }

    public String getMvRate() {
        return mvRate;
    }

    public String getMvDescription() {
        return mvDescription;
    }

    public String getMvDate() {
        return mvDate;
    }

    public String getMvOriLang() {
        return mvOriLang;
    }

    public String getMvPhoto() {
        return mvPhoto;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void favorite() {
        isFavorite = !isFavorite;
    }
}
