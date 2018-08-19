package me.mxcsyounes.archernotebook.database.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Date;

import me.mxcsyounes.archernotebook.database.converters.DateConverters;

@Entity(tableName = "adjustment")
public class Adjustment implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int distance;

    public String v_adj;
    public String h_adj;

    @NonNull
    @TypeConverters({DateConverters.class})
    public Date date;

    @ColumnInfo(name = "path")
    public String path;


    public String description;

    public Adjustment(int id, int distance, String v_adj, String h_adj, @NonNull Date date, String path, String description) {
        this.id = id;
        this.distance = distance;
        this.v_adj = v_adj;
        this.h_adj = h_adj;
        this.date = date;
        this.path = path;
        this.description = description;
    }


    @Ignore
    public Adjustment(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.distance);
        dest.writeString(this.v_adj);
        dest.writeString(this.h_adj);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeString(this.path);
        dest.writeString(this.description);
    }

    protected Adjustment(Parcel in) {
        this.id = in.readInt();
        this.distance = in.readInt();
        this.v_adj = in.readString();
        this.h_adj = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.path = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Adjustment> CREATOR = new Parcelable.Creator<Adjustment>() {
        @Override
        public Adjustment createFromParcel(Parcel source) {
            return new Adjustment(source);
        }

        @Override
        public Adjustment[] newArray(int size) {
            return new Adjustment[size];
        }
    };
}
