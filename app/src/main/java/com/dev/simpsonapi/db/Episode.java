package com.dev.simpsonapi.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "episodes")
public class Episode {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo()
    public String airDate;

    @ColumnInfo()
    public int episodeNumber;

    @ColumnInfo()
    public String name;

    @ColumnInfo()
    public int season;

    @ColumnInfo()
    public String synopsis;

    public Episode(String airDate, int episodeNumber, String name, int season, String synopsis) {
        this.airDate = airDate;
        this.episodeNumber = episodeNumber;
        this.name = name;
        this.season = season;
        this.synopsis = synopsis;
    }
}
