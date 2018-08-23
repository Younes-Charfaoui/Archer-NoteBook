package me.mxcsyounes.archernotebook.database.dao;


import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import me.mxcsyounes.archernotebook.database.entities.Adjustment

@Dao
interface AdjustmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAdjustment(adjustment: Adjustment)

    @Update
    fun updateAdjustment(adjustment: Adjustment)

    @Delete
    fun deleteAdjustment(adjustment: Adjustment)

    @Query("DELETE FROM adjustment")
    fun deleteAll();

    @Query("SELECT * FROM adjustment ORDER BY date ASC")
    fun getAllDateASC(): LiveData<MutableList<Adjustment>>

    @Query("SELECT * FROM adjustment ORDER BY date DESC")
    fun getAllDateDESC(): LiveData<MutableList<Adjustment>>

    @Query("SELECT * FROM adjustment ORDER BY distance ASC")
    fun getAllDistanceASC(): LiveData<MutableList<Adjustment>>

    @Query("SELECT * FROM adjustment ORDER BY distance DESC")
    fun getAllDistanceDESC(): LiveData<MutableList<Adjustment>>

    @Query("SELECT * FROM adjustment WHERE id=:id")
    fun getAdjustmentById(id: Int): LiveData<Adjustment>
}
