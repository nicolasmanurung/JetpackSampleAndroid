package com.dicoding.moviecatalogue.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    @SerializedName("id")
    private long mvId;

    @SerializedName("title")
    private String mvName;

    @SerializedName("vote_average")
    private String mvRate;

    @SerializedName("overview")
    private String mvDescription;

    @SerializedName("release_date")
    private String mvDate;

    @SerializedName("original_language")
    private String mvOri_lang;

    @SerializedName("poster_path")
    private String mvPhoto;

    public MovieResponse(long mvId, String mvName, String mvRate, String mvDescription, String mvDate, String mvOri_lang, String mvPhoto) {
        this.mvId = mvId;
        this.mvName = mvName;
        this.mvRate = mvRate;
        this.mvDescription = mvDescription;
        this.mvDate = mvDate;
        this.mvOri_lang = mvOri_lang;
        this.mvPhoto = mvPhoto;
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

    public String getMvOri_lang() {
        return mvOri_lang;
    }

    public String getMvPhoto() {
        return mvPhoto;
    }
}
