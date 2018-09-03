package me.mxcsyounes.archernotebook.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import me.mxcsyounes.archernotebook.database.ArcherDatabase
import me.mxcsyounes.archernotebook.database.dao.AdjustmentDao
import me.mxcsyounes.archernotebook.database.entities.Adjustment
import java.util.concurrent.Executors

class AdjustmentsRepository(application: Application) {

    private val mAdjDao: AdjustmentDao = ArcherDatabase.getInstance(application, false).mAdjDao()
    val allAdj: LiveData<MutableList<Adjustment>>
    private val executor = Executors.newSingleThreadExecutor()

    init {
        allAdj = mAdjDao.getAllDateDESC()
    }

    fun getAdjustment(id: Int): LiveData<Adjustment> {
        return mAdjDao.getAdjustmentById(id)
    }

    fun insertAdjustment(adjustment: Adjustment) {
        executor.execute {
            mAdjDao.insertAdjustment(adjustment)
        }
    }

    fun updateAdjustment(adjustment: Adjustment) {
        executor.execute {
            mAdjDao.updateAdjustment(adjustment)
        }
    }

    fun deleteAdjustment(adjustment: Adjustment) {
        executor.execute {
            mAdjDao.deleteAdjustment(adjustment)
        }
    }

    fun deleteAllAdjustments() {
        executor.execute {
            mAdjDao.deleteAll()
        }
    }
}
