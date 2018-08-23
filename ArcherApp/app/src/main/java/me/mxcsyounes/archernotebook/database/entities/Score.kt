package me.mxcsyounes.archernotebook.database.entities;


import android.arch.persistence.room.*
import android.support.annotation.NonNull
import me.mxcsyounes.archernotebook.database.converters.DateConverters
import java.util.*

@Entity(tableName = "scores")
class Score {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "score_id", index = true)
    var id: Int = 0

    @ColumnInfo(name = "score_distance")
    var distance: Int = 0

    @ColumnInfo(name = "score_type")
    var type: Int = 0

    @ColumnInfo(name = "score_photos")
    var paths: String? = null

    @ColumnInfo(name = "score_description")
    var description: String? = null

    @ColumnInfo(name = "score_date")
    @TypeConverters(value = [(DateConverters::class)])
    var date: Date? = null

    constructor(id: Int, @NonNull distance: Int, @NonNull type: Int, paths: String, description: String,
                @NonNull date: Date) {
        this.id = id;
        this.distance = distance;
        this.type = type;
        this.paths = paths;
        this.description = description;
        this.date = date;
    }

    @Ignore
    constructor()

}
