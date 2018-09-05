package me.mxcsyounes.archernotebook.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.mxcsyounes.archernotebook.database.entities.ScoreSheet
import me.mxcsyounes.archernotebook.repositories.ScoreSheetRepository

class PreviousScoresViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ScoreSheetRepository(application)

    val allScores: LiveData<MutableList<ScoreSheet>>

    init {
        allScores = repository.allSheet
    }

    fun insertScoresSheet(scoreSheet: ScoreSheet) =
            repository.insertScoreSheet(scoreSheet);


    fun deleteScoresSheet(scoreSheet: ScoreSheet) =
            repository.deleteScoreSheet(scoreSheet);


    fun deleteAllScoresSheet() =
            repository.deleteAllScores();
}