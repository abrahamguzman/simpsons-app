package com.dev.simpsonapi.dao;

import android.content.ContentValues;

import com.dev.simpsonapi.db.DbHelper;
import com.dev.simpsonapi.db.Episode;

public class EpisodeDao {
    private DbHelper  dbHelper;

    public EpisodeDao(DbHelper dbHelper){
        this.dbHelper=dbHelper;
    }

    public void insert (Episode episode){
        ContentValues values = new ContentValues();

        values.put("id",episode.getId());
        values.put("airDate",episode.)
    }




}
