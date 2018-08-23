package me.mxcsyounes.archernotebook.database.entities


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.os.Parcel
import android.os.Parcelable

import java.util.Date

import me.mxcsyounes.archernotebook.database.converters.DateConverters

@Entity(tableName = "adjustment")
class Adjustment : Parcelable{

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var distance: Int = 0

    @ColumnInfo(name = "v_adj")
    var verticalAdjustment: String? = null

    @ColumnInfo(name = "h_adj")
    var horizontalAdjustment: String? = null

    @TypeConverters(DateConverters::class)
    var date: Date? = null

    @ColumnInfo(name = "path")
    var path: String? = null


    var description: String? = null

    constructor(id: Int, distance: Int, v_adj: String, h_adj: String, date: Date, path: String, description: String) {
        this.id = id
        this.distance = distance
        this.verticalAdjustment = v_adj
        this.horizontalAdjustment = h_adj
        this.date = date
        this.path = path
        this.description = description
    }


    @Ignore
    constructor()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeInt(this.distance)
        dest.writeString(this.verticalAdjustment)
        dest.writeString(this.horizontalAdjustment)
        dest.writeLong(if (this.date != null) this.date!!.time else -1)
        dest.writeString(this.path)
        dest.writeString(this.description)
    }

    private constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        this.distance = `in`.readInt()
        this.verticalAdjustment = `in`.readString()
        this.horizontalAdjustment = `in`.readString()
        val tmpDate = `in`.readLong()
        this.date = if (tmpDate == -1L) null else Date(tmpDate)
        this.path = `in`.readString()
        this.description = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<Adjustment> = object : Parcelable.Creator<Adjustment> {
            override fun createFromParcel(source: Parcel): Adjustment {
                return Adjustment(source)
            }

            override fun newArray(size: Int): Array<Adjustment?> {
                return arrayOfNulls(size)
            }
        }
    }

}