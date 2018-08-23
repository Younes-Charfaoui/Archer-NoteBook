package me.mxcsyounes.archernotebook.asynck;


import android.os.AsyncTask
import me.mxcsyounes.archernotebook.database.dao.AdjustmentDao
import me.mxcsyounes.archernotebook.database.entities.Adjustment


class AdjDatabaseTask(@param: DatabaseOperation val operation: Int, val dao: AdjustmentDao) : AsyncTask<Adjustment, Void, Void>() {




    override fun doInBackground(vararg adjustments: Adjustment): Void? {
        when (operation) {
            DELETE -> dao.deleteAdjustment(adjustments[0])

            UPDATE -> dao.updateAdjustment(adjustments[0])

            INSERT -> dao.insertAdjustment(adjustments[0])

            DELETE_ALL -> dao.deleteAll()
        }
        return null
    }

}
