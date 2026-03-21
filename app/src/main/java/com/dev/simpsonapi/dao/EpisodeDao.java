package com.dev.simpsonapi.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dev.simpsonapi.db.Episode;

import java.util.List;

@Dao
public interface EpisodeDao {
    @Query("SELECT * FROM episodes")
    List<Episode> getAll();

    @Insert
    void insert(Episode episode);

    @Query("DELETE FROM episodes")
    void deleteAll();
}
