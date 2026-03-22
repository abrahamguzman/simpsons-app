package com.dev.simpsonapi.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dev.simpsonapi.dao.EpisodeDao;
import com.dev.simpsonapi.dao.UserDao;

@Database(entities = {User.class, Episode.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract EpisodeDao episodeDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration(true)
                            //.allowMainThreadQueries() Permite consultas en el hilo principal (no recomendado para producción)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}