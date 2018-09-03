package me.mxcsyounes.archernotebook.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import me.mxcsyounes.archernotebook.database.ArcherDatabase
import me.mxcsyounes.archernotebook.database.dao.ScoreSheetDao
import me.mxcsyounes.archernotebook.database.entities.ScoreSheet
import java.util.concurrent.Executors

class ScoreSheetRepository(application: Application) {

    private val scoreDao: ScoreSheetDao = ArcherDatabase.getInstance(application, false).scoreSheetDao()
    private val executor = Executors.newSingleThreadExecutor()
    val allSheet: LiveData<MutableList<ScoreSheet>>

    init {
        allSheet = scoreDao.getAllScores()
    }

    fun getScoreSheet(id: Int): LiveData<ScoreSheet> {
        return scoreDao.getScoreById(id)
    }

    fun insertScoreSheet(score: ScoreSheet) {
        executor.execute {
            scoreDao.addScoreSheet(score)
        }
    }

    fun deleteScoreSheet(score: ScoreSheet) {
        executor.execute {
            scoreDao.deleteScoreSheet(score)
        }
    }

    fun deleteScoreSheet(id: Int) {
        executor.execute {
            scoreDao.deleteScoreById(id)
        }
    }

    fun deleteAllAdjustments() {
        executor.execute {
            scoreDao.deleteAllScores()
        }
    }
}
