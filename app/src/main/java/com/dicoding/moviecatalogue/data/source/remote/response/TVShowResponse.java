package com.dicoding.moviecatalogue.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class TVShowResponse {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String tvName;

    @SerializedName("vote_average")
    private String tvRate;

    @SerializedName("overview")
    private String tvDescription;

    @SerializedName("first_air_date")
    private String tvDate;

    @SerializedName("original_language")
    private String tvOriLang;

    @SerializedName("poster_path")
    private String tvPhoto;


    public TVShowResponse(long id, String tvName, String tvRate, String tvDescription, String tvDate, String tvOriLang, String tvPhoto) {
        this.id = id;
        this.tvName = tvName;
        this.tvRate = tvRate;
        this.tvDescription = tvDescription;
        this.tvDate = tvDate;
        this.tvOriLang = tvOriLang;
        this.tvPhoto = tvPhoto;
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
}
