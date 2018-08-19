package me.mxcsyounes.archernotebook.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import me.mxcsyounes.archernotebook.database.entities.Score;

@Dao
public interface ScoreDao {

    @Delete
    void deleteScore(Score score);

    @Update
    void updateScore(Score score);

    @Insert
    void insertScore(Score score);

    @Query("SELECT * FROM scores ORDER BY score_date DESC")
    LiveData<List<Score>> getAllScoresDESC();

    @Query("SELECT * FROM scores WHERE score_id=:id")
    LiveData<Score> getScoreWithId(int id);

    @Query("SELECT * FROM scores WHERE score_date > :date")
    LiveData<List<Score>> getScoresAboveDate(long date);

    @Query("DELETE FROM scores")
    void deleteAllScores();

}
