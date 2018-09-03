package me.mxcsyounes.archernotebook.repositories


import android.app.Application
import android.arch.lifecycle.LiveData
import me.mxcsyounes.archernotebook.database.ArcherDatabase
import me.mxcsyounes.archernotebook.database.dao.ScoreDao
import me.mxcsyounes.archernotebook.database.entities.Score
import java.util.concurrent.Executors

class ScoresRepository(application: Application) {

    private val mScoreDao: ScoreDao = ArcherDatabase.getInstance(application, false).mScoreDao()
    val mScores: LiveData<MutableList<Score>>
    private val executor = Executors.newSingleThreadExecutor()

    init {
        mScores = mScoreDao.allScoresDESC
    }

    fun deleteAllScores() = executor.execute{mScoreDao.deleteAllScores()}

    fun insertScore(score: Score) = executor.execute{mScoreDao.insertScore(score)}

    fun updateScore(score: Score) = executor.execute{mScoreDao.updateScore(score)}

    fun deleteScore(score: Score) = executor.execute{mScoreDao.deleteScore(score)}

}
