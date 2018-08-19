package me.mxcsyounes.archernotebook.asynck;

import android.os.AsyncTask;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import me.mxcsyounes.archernotebook.database.dao.AdjustmentDao;
import me.mxcsyounes.archernotebook.database.dao.ScoreDao;
import me.mxcsyounes.archernotebook.database.entities.Adjustment;
import me.mxcsyounes.archernotebook.database.entities.Score;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class ScoreDatabaseTask extends AsyncTask<Score, Void, Void> {

    public static final int UPDATE = 2;
    public static final int DELETE = 3;
    public static final int INSERT = 1;
    public static final int DELETE_ALL = 4;

    private int mOperation;
    private ScoreDao mDao;

    public ScoreDatabaseTask(@DatabaseOperation int mOperation, ScoreDao mDao) {
        this.mOperation = mOperation;
        this.mDao = mDao;
    }

    @Override
    protected Void doInBackground(Score... scores) {
        switch (mOperation) {
            case DELETE:
                mDao.deleteScore(scores[0]);
                break;
            case UPDATE:
                mDao.updateScore(scores[0]);
                break;
            case INSERT:
                mDao.insertScore(scores[0]);
                break;
            case DELETE_ALL:
                mDao.deleteAllScores();
                break;
        }
        return null;
    }

    @IntDef({UPDATE, DELETE, DELETE_ALL, INSERT})
    @Retention(SOURCE)
    private @interface DatabaseOperation {
    }
}
