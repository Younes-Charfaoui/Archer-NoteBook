package me.mxcsyounes.archernotebook.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*


@Entity(tableName = "score_sheet")
class ScoreSheet(@PrimaryKey(autoGenerate = true)
                 @ColumnInfo(name = "_id")
                 val idScore: Int,

                 @ColumnInfo(name = "date_score")
                 val date : Date = Date(),

                 @ColumnInfo(name = "distance_score")
                 val distance : Int ,
                 val typeSeries : Int,
                 val score : String){


    fun getScore(){

    }
}



