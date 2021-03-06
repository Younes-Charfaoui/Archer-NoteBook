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
                 var idScore: Int? = null,

                 @ColumnInfo(name = "date_score")
                 @TypeConverters(value = [(DateConverters::class)])
                 var date: Date? = null,

                 @ColumnInfo(name = "distance_score")
                 var distance: Int,
                 var typeSeries: Int,
                 var typeSheet: Int,

                 @ColumnInfo(name = "sheet_score")
                 var score: String) {


    private fun getScores(): MutableList<ScoreRounds> {
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

    fun sumOfAll(): Int {
        var result = 0
        val scores = this.getScores()
        for (score in scores) {
            result += score.total
        }
        return result
    }

    val numberOfArrows: Int
        get() {
            var result = 0
            val scores = this.getScores()
            for (score in scores) {
                result += score.rounds.size * score.rounds[0].scores.size
            }
            return result
        }
}



