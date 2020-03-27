package com.dicoding.moviecatalogue.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;

@Database(entities = {MovieEntity.class, TVShowEntity.class},
        version = 1,
        exportSchema = false)
public abstract class CatalogueDatabase extends RoomDatabase {

    private static final Object sLock = new Object();
    private static CatalogueDatabase INSTANCE;

    public static CatalogueDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        CatalogueDatabase.class, "CatalogueMovie.db")
                        .build();
            }
            return INSTANCE;
        }
    }

    public abstract CatalogueDao catalogueDao();
}
