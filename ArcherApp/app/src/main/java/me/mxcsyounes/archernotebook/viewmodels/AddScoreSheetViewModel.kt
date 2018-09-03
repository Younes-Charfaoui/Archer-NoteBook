package me.mxcsyounes.archernotebook.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import me.mxcsyounes.archernotebook.database.entities.ScoreSheet
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
                score = totalScore, typeSheet = sheetType , date = Date()))
    }

}