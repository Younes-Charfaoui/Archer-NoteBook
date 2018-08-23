package me.mxcsyounes.archernotebook.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import me.mxcsyounes.archernotebook.database.entities.Score
import me.mxcsyounes.archernotebook.repositories.ScoresRepository

class ScoresViewModel(application: Application) : AndroidViewModel(application) {

    val allScores: LiveData<MutableList<Score>>
    private val mRepository: ScoresRepository = ScoresRepository(application)

    init {
        allScores = mRepository.mScores
    }

    fun deleteAllScores() {
        mRepository.deleteAllScores()
    }

    fun deleteScore(score: Score) {
        mRepository.deleteScore(score)
    }

    fun updateScore(score: Score) {
        mRepository.updateScore(score)
    }

    fun insertScore(score: Score) {
        mRepository.insertScore(score)
    }

}
