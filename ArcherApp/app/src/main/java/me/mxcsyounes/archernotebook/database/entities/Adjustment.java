package me.mxcsyounes.archernotebook.database.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

import me.mxcsyounes.archernotebook.database.converters.DateConverters;

@Entity(tableName = "adjustment")
public class Adjustment {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int distance;

    public String v_adj;
    public String h_adj;

    @NonNull
    @TypeConverters({DateConverters.class})
    public Date date;

    @ColumnInfo(name = "path_one")
    public String pathOne;
    @ColumnInfo(name = "path_two")
    public String pathTwo;
    @ColumnInfo(name = "path_three")
    public String PathThree;

    public String description;

    public Adjustment(int id, int distance, String v_adj, String h_adj, @NonNull Date date, String pathOne, String pathTwo, String pathThree, String description) {
        this.id = id;
        this.distance = distance;
        this.v_adj = v_adj;
        this.h_adj = h_adj;
        this.date = date;
        this.pathOne = pathOne;
        this.pathTwo = pathTwo;
        PathThree = pathThree;
        this.description = description;
    }
}
