package me.mxcsyounes.archernotebook.database.entities


import android.arch.persistence.room.*
import android.os.Parcel
import android.os.Parcelable
import me.mxcsyounes.archernotebook.database.converters.DateConverters
import java.util.*

@Entity(tableName = "adjustment")
class Adjustment : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

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

    constructor(parcel: Parcel) {
        id = null
        distance = parcel.readInt()
        verticalAdjustment = parcel.readString()
        horizontalAdjustment = parcel.readString()
        path = parcel.readString()
        date = Date(parcel.readLong())
        description = parcel.readString()
    }

    constructor(id: Int, distance: Int, verticalAdjustment: String?, horizontalAdjustment: String?, date: Date?, path: String?, description: String?) {
        this.id = id
        this.distance = distance
        this.verticalAdjustment = verticalAdjustment
        this.horizontalAdjustment = horizontalAdjustment
        this.date = date
        this.path = path
        this.description = description
    }


    @Ignore
    constructor()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(distance)
        parcel.writeString(verticalAdjustment)
        parcel.writeString(horizontalAdjustment)
        parcel.writeString(path)
        parcel.writeLong(date!!.time)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Adjustment> {
        override fun createFromParcel(parcel: Parcel): Adjustment {
            return Adjustment(parcel)
        }

        override fun newArray(size: Int): Array<Adjustment?> {
            return arrayOfNulls(size)
        }
    }

}