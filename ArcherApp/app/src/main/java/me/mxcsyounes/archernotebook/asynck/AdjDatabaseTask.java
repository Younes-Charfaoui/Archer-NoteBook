package me.mxcsyounes.archernotebook.asynck;

import android.os.AsyncTask;
import android.support.annotation.IntDef;


import java.lang.annotation.Retention;

import me.mxcsyounes.archernotebook.database.dao.AdjustmentDao;
import me.mxcsyounes.archernotebook.database.entities.Adjustment;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class AdjDatabaseTask extends AsyncTask<Adjustment, Void, Void> {

    public static final int UPDATE = 2;
    public static final int DELETE = 3;
    public static final int INSERT = 1;
    public static final int DELETE_ALL = 4;

    private int mOperation;
    private AdjustmentDao mDao;

    public AdjDatabaseTask(@DatabaseOperation int mOperation, AdjustmentDao mDao) {
        this.mOperation = mOperation;
        this.mDao = mDao;
    }

    @Override
    protected Void doInBackground(Adjustment... adjustments) {
        switch (mOperation) {
            case DELETE:
                mDao.deleteAdjustment(adjustments[0]);
                break;
            case UPDATE:
                mDao.updateAdjustment(adjustments[0]);
                break;
            case INSERT:
                mDao.insertAdjustment(adjustments[0]);
                break;
            case DELETE_ALL:
                mDao.deleteAll();
                break;
        }
        return null;
    }

    @IntDef({UPDATE, DELETE, DELETE_ALL, INSERT})
    @Retention(SOURCE)
    private @interface DatabaseOperation {
    }
}
