package me.mxcsyounes.archernotebook.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import me.mxcsyounes.archernotebook.asynck.*

import me.mxcsyounes.archernotebook.database.ArcherDatabase
import me.mxcsyounes.archernotebook.database.dao.AdjustmentDao
import me.mxcsyounes.archernotebook.database.entities.Adjustment

class AdjustmentsRepository(application: Application) {

    private val mAdjDao: AdjustmentDao = ArcherDatabase.getInstance(application, false).mAdjDao()
    val allAdj: LiveData<MutableList<Adjustment>>

    init {
        allAdj = mAdjDao.getAllDateDESC()
    }


    fun getAdj(id: Int): LiveData<Adjustment> {
        return mAdjDao.getAdjustmentById(id)
    }

    fun insertAdjustment(adjustment: Adjustment) {
        AdjDatabaseTask(INSERT, mAdjDao).execute(adjustment)
    }

    fun updateNote(adjustment: Adjustment) {
        AdjDatabaseTask(UPDATE, mAdjDao).execute(adjustment)
    }

    fun deleteAdjustment(adjustment: Adjustment) {
        AdjDatabaseTask(DELETE, mAdjDao).execute(adjustment)
    }

    fun deleteAllAdjustments() {
        AdjDatabaseTask(DELETE_ALL, mAdjDao).execute()
    }
}
