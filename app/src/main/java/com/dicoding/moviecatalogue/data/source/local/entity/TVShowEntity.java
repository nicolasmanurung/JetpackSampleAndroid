package com.dicoding.moviecatalogue.data.source.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dicoding.moviecatalogue.data.source.remote.response.TVShowResponse;

@Entity(tableName = "favtventity")
public class TVShowEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "name")
    private String tvName;

    @ColumnInfo(name = "rate")
    private String tvRate;

    @ColumnInfo(name = "description")
    private String tvDescription;

    @ColumnInfo(name = "date")
    private String tvDate;

    @ColumnInfo(name = "language")
    private String tvOriLang;

    @ColumnInfo(name = "photo")
    private String tvPhoto;

    @ColumnInfo(name = "isfavorite")
    private boolean isFavorite = false;

    public TVShowEntity(TVShowResponse tvShowResponse) {
        this.id = tvShowResponse.getId();
        this.tvName = tvShowResponse.getTvName();
        this.tvRate = tvShowResponse.getTvRate();
        this.tvDescription = tvShowResponse.getTvDescription();
        this.tvDate = tvShowResponse.getTvDate();
        this.tvOriLang = tvShowResponse.getTvOriLang();
        this.tvPhoto = tvShowResponse.getTvPhoto();
        this.isFavorite = false;
    }

    public TVShowEntity(long id, String tvName, String tvRate, String tvDescription, String tvDate, String tvOriLang, String tvPhoto, Boolean isFavorite) {
        this.id = id;
        this.tvName = tvName;
        this.tvRate = tvRate;
        this.tvDescription = tvDescription;
        this.tvDate = tvDate;
        this.tvOriLang = tvOriLang;
        this.tvPhoto = tvPhoto;
        if (isFavorite != null) {
            this.isFavorite = isFavorite;
        }
    }

    public long getId() {
        return id;
    }

    public String getTvName() {
        return tvName;
    }

    public String getTvRate() {
        return tvRate;
    }

    public String getTvDescription() {
        return tvDescription;
    }

    public String getTvDate() {
        return tvDate;
    }

    public String getTvOriLang() {
        return tvOriLang;
    }

    public String getTvPhoto() {
        return tvPhoto;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void favorite() {
        isFavorite = !isFavorite;
    }
}
