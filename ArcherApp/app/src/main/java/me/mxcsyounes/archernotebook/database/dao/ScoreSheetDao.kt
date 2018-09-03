package me.mxcsyounes.archernotebook.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import me.mxcsyounes.archernotebook.database.entities.ScoreSheet

@Dao
interface ScoreSheetDao {

    @Insert
    fun addScoreSheet(score: ScoreSheet)

    @Delete
    fun deleteScoreSheet(score: ScoreSheet)

    @Query("SELECT * FROM score_sheet ORDER BY date_score ASC")
    fun getAllScores(): LiveData<MutableList<ScoreSheet>>

    @Query("SELECT * FROM score_sheet WHERE _id = :id")
    fun getScoreById(id: Int): LiveData<ScoreSheet>

    @Query("DELETE FROM score_sheet WHERE _id = :id")
    fun deleteScoreById(id: Int)

    @Query("DELETE FROM score_sheet")
    fun deleteAllScores()
}