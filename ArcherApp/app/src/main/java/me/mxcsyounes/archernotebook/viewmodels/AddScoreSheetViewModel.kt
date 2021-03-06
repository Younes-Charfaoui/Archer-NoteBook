package me.mxcsyounes.archernotebook.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.jjoe64.graphview.series.DataPoint
import me.mxcsyounes.archernotebook.database.entities.ScoreSheet
import me.mxcsyounes.archernotebook.model.Round
import me.mxcsyounes.archernotebook.model.ScoreRounds
import me.mxcsyounes.archernotebook.repositories.ScoreSheetRepository
import java.util.*

class AddScoreSheetViewModel(application: Application) : AndroidViewModel(application) {

    var distance: Int = 2
    var sheetType: Int = 2
    var seriesType: Int = 2

    var scores = mutableListOf<ScoreRounds>()
    private val repository = ScoreSheetRepository(application)

    fun state(): Int {
        return if (seriesType == 2) {
            when (scores.size) {
                0 -> 2
                1 -> 1
                else -> 0
            }
        } else {
            if (scores.size == 1) 0
            else 1
        }

    }

    fun saveScore() {
        var totalScore = ""
        for ((i, score) in scores.withIndex()) {
            totalScore += score.toString()
            if (i != scores.lastIndex)
                totalScore += "#"
        }
        repository.insertScoreSheet(ScoreSheet(distance = distance, typeSeries = seriesType,
                score = totalScore, typeSheet = sheetType, date = Date()))
    }

    val firstScore: String
        get() = "${scores[0].total} / ${scores[0].over}"

    val secondScore: String
        get() = "${scores[1].total} / ${scores[1].over}"


    fun scoreSeries(number: Int): Array<out DataPoint>? {
        val dataPoints = mutableListOf<DataPoint>()
        for (round in scores[number - 1].rounds) {
            dataPoints.add(DataPoint(round.number.toDouble(), Round.sumRound(round.scores)
                    .toDouble()))
        }
        return dataPoints.toTypedArray()
    }
}