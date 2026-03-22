package com.dev.simpsonapi.db;

public class Episode  {

    private  int id;
    private  String airDate;
    private  int episodeNumber;
    private  String name;
    private  String season;

    public Episode(int id, int episodeNumber, String name, String season) {
        this.id = id;
        this.episodeNumber = episodeNumber;
        this.name = name;
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
