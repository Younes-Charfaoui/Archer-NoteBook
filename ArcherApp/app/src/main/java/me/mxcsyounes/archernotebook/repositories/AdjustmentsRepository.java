package me.mxcsyounes.archernotebook.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

import me.mxcsyounes.archernotebook.asynck.AdjDatabaseTask;
import me.mxcsyounes.archernotebook.database.ArcherDatabase;
import me.mxcsyounes.archernotebook.database.dao.AdjustmentDao;
import me.mxcsyounes.archernotebook.database.entities.Adjustment;

public class AdjustmentsRepository {

    private AdjustmentDao mAdjDao;
    private LiveData<List<Adjustment>> mAllAdj;

    public AdjustmentsRepository(Application application) {
        mAdjDao = ArcherDatabase.getInstance(application, false).mAdjDao();
        mAllAdj = mAdjDao.getAllDateDESC();
    }

    public LiveData<List<Adjustment>> getAllAdj() {
        return mAllAdj;
    }

    public LiveData<Adjustment> getAdj(int id) {
        return mAdjDao.getAdjustmentById(id);
    }

    public void insertAdjustment(Adjustment adjustment) {
        new AdjDatabaseTask(AdjDatabaseTask.INSERT, mAdjDao).execute(adjustment);
    }

    public void updateNote(Adjustment adjustment) {
        new AdjDatabaseTask(AdjDatabaseTask.UPDATE, mAdjDao).execute(adjustment);
    }

    public void deleteAdjustment(Adjustment adjustment) {
        new AdjDatabaseTask(AdjDatabaseTask.DELETE, mAdjDao).execute(adjustment);
    }

    public void deleteAllAdjustments() {
        new AdjDatabaseTask(AdjDatabaseTask.DELETE_ALL, mAdjDao).execute();
    }
}
