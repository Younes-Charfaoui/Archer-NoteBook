package me.mxcsyounes.archernotebook.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import me.mxcsyounes.archernotebook.database.entities.Score

@Dao
interface ScoreDao {

    @get:Query("SELECT * FROM scores ORDER BY score_date DESC")
    val allScoresDESC: LiveData<MutableList<Score>>

    @Delete
    fun deleteScore(score: Score)

    @Update
    fun updateScore(score: Score)

    @Insert
    fun insertScore(score: Score)

    @Query("SELECT * FROM scores WHERE score_id=:id")
    fun getScoreWithId(id: Int): LiveData<Score>

    @Query("SELECT * FROM scores WHERE score_date > :date")
    fun getScoresAboveDate(date: Long): LiveData<MutableList<Score>>

    @Query("DELETE FROM scores")
    fun deleteAllScores()

}
