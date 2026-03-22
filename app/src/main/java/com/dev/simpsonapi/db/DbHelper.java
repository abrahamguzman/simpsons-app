package com.dev.simpsonapi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "simpson.sdb";
    private static final int DB_VERSION = 2;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCharaters = "CREATE TABLE IF NOT EXISTS characters (id INTEGER PRIMARY KEY, name TEXT, age INTEGER, birthdate TEXT, image TEXT, occupation TEXT, status TEXT)";
        sqLiteDatabase.execSQL(queryCharaters);

        String queryEpisodes ="CREATE TABLE IF NOT EXISTS episodes (id INTEGER PRIMARY KEY,airDate TEXT,episodeNumber INTEGER,name TEXT,season INTEGER)";
        sqLiteDatabase.execSQL(queryEpisodes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS characters";
        sqLiteDatabase.execSQL(query);
        String queryEpisodes="DROP TABLE IF EXISTS episodes";
        sqLiteDatabase.execSQL(queryEpisodes);
        onCreate(sqLiteDatabase);


    }


}
