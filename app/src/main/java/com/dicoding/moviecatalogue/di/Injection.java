package com.dicoding.moviecatalogue.di;

import android.app.Application;

import com.dicoding.moviecatalogue.data.source.CatalogueRepository;
import com.dicoding.moviecatalogue.data.source.local.LocalRepository;
import com.dicoding.moviecatalogue.data.source.local.room.CatalogueDatabase;
import com.dicoding.moviecatalogue.data.source.remote.RemoteRepository;
import com.dicoding.moviecatalogue.utils.ApiClient;
import com.dicoding.moviecatalogue.utils.AppExecutors;

public class Injection {
    public static CatalogueRepository provideRepository(Application application) {

        CatalogueDatabase database = CatalogueDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(database.catalogueDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(ApiClient.getInstance());
        AppExecutors appExecutors = new AppExecutors();
        return CatalogueRepository.getInstance(remoteRepository, localRepository, appExecutors);
    }
}
