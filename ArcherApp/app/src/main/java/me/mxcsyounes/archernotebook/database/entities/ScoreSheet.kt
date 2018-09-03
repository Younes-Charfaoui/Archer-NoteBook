package me.mxcsyounes.archernotebook.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import me.mxcsyounes.archernotebook.database.converters.DateConverters
import me.mxcsyounes.archernotebook.model.ScoreRounds
import java.util.*


@Entity(tableName = "score_sheet")
class ScoreSheet(@PrimaryKey(autoGenerate = true)
                 @ColumnInfo(name = "_id")
                 val idScore: Int? = null,

                 @ColumnInfo(name = "date_score")
                 @TypeConverters(value = [(DateConverters::class)])
                 val date: Date? = null,

                 @ColumnInfo(name = "distance_score")
                 val distance: Int,
                 val typeSeries: Int,
                 val typeSheet: Int,

                 @ColumnInfo(name = "sheet_score")
                 val score: String) {


    fun getScores(): MutableList<ScoreRounds> {
        return if (typeSeries == 1) {

            val score = ScoreRounds(score)
            mutableListOf(score)
        } else {

            val scoresString = score.split("#")
            val scoreOne = ScoreRounds(scoresString[0])
            val scoreTwo = ScoreRounds(scoresString[1])
            mutableListOf(scoreOne, scoreTwo)
        }
    }
}



