package me.mxcsyounes.archernotebook.asynck

import android.os.AsyncTask
import me.mxcsyounes.archernotebook.database.dao.ScoreDao
import me.mxcsyounes.archernotebook.database.entities.Score


class ScoreDatabaseTask(@param:DatabaseOperation private val operation: Int, private val dao: ScoreDao)
    : AsyncTask<Score, Void, Void>() {

    override fun doInBackground(vararg scores: Score): Void? {
        when (operation) {
            DELETE -> dao.deleteScore(scores[0])

            UPDATE -> dao.updateScore(scores[0])

            INSERT -> dao.insertScore(scores[0])

            DELETE_ALL -> dao.deleteAllScores()
        }
        return null
    }

}
