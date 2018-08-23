package me.mxcsyounes.archernotebook.repositories

import android.app.Application
import android.arch.lifecycle.LiveData


import me.mxcsyounes.archernotebook.asynck.ScoreDatabaseTask
import me.mxcsyounes.archernotebook.asynck.*
import me.mxcsyounes.archernotebook.database.ArcherDatabase
import me.mxcsyounes.archernotebook.database.dao.ScoreDao
import me.mxcsyounes.archernotebook.database.entities.Score

class ScoresRepository(application: Application) {

    private val mScoreDao: ScoreDao = ArcherDatabase.getInstance(application, false).mScoreDao()
    val mScores: LiveData<MutableList<Score>>

    init {
        mScores = mScoreDao.allScoresDESC
    }


    fun deleteAllScores() =
            ScoreDatabaseTask(DELETE_ALL, mScoreDao).execute()!!


    fun insertScore(score: Score) =
            ScoreDatabaseTask(INSERT, mScoreDao).execute(score)


    fun updateScore(score: Score) =
            ScoreDatabaseTask(UPDATE, mScoreDao).execute(score)


    fun deleteScore(score: Score) =
            ScoreDatabaseTask(DELETE, mScoreDao).execute(score)

}
