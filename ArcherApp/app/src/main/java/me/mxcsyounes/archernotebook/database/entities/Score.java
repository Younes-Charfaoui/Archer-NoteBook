package me.mxcsyounes.archernotebook.database.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

import me.mxcsyounes.archernotebook.database.converters.DateConverters;

@Entity(tableName = "scores")
public class Score {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "score_id", index = true)
    public int id;

    @ColumnInfo(name = "score_distance")
    public int distance;

    @ColumnInfo(name = "score_type")
    public int type;

    @ColumnInfo(name = "score_photos")
    public String paths;

    @ColumnInfo(name = "score_description")
    public String description;

    @NonNull
    @ColumnInfo(name = "score_date")
    @TypeConverters(DateConverters.class)
    public Date date;

    public Score(int id, @NonNull int distance, @NonNull int type, String paths, String description, @NonNull Date date) {
        this.id = id;
        this.distance = distance;
        this.type = type;
        this.paths = paths;
        this.description = description;
        this.date = date;
    }

    @Ignore
    public Score() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public int getDistance() {
        return distance;
    }

    public void setDistance(@NonNull int distance) {
        this.distance = distance;
    }

    @NonNull
    public int getType() {
        return type;
    }

    public void setType(@NonNull int type) {
        this.type = type;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }
}
