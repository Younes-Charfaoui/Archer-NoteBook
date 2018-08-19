package me.mxcsyounes.archernotebook.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

import me.mxcsyounes.archernotebook.asynck.ScoreDatabaseTask;
import me.mxcsyounes.archernotebook.database.ArcherDatabase;
import me.mxcsyounes.archernotebook.database.dao.ScoreDao;
import me.mxcsyounes.archernotebook.database.entities.Score;

public class ScoresRepository {

    private ScoreDao mScoreDao;
    private LiveData<List<Score>> mScores;

    public ScoresRepository(Application application) {
        mScoreDao = ArcherDatabase.getInstance(application, false).mScoreDao();
        mScores = mScoreDao.getAllScoresDESC();
    }

    public LiveData<List<Score>> getScores() {
        return mScores;
    }

    public void deleteAllScores() {
        new ScoreDatabaseTask(ScoreDatabaseTask.DELETE_ALL, mScoreDao).execute();
    }

    public void insertScore(Score score) {
        new ScoreDatabaseTask(ScoreDatabaseTask.INSERT, mScoreDao).execute(score);
    }

    public void updateScore(Score score) {
        new ScoreDatabaseTask(ScoreDatabaseTask.UPDATE, mScoreDao).execute(score);
    }

    public void deleteScore(Score score) {
        new ScoreDatabaseTask(ScoreDatabaseTask.DELETE, mScoreDao).execute(score);
    }
}
