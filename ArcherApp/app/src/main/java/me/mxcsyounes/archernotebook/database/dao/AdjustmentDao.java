package me.mxcsyounes.archernotebook.database.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import me.mxcsyounes.archernotebook.database.entities.Adjustment;

@Dao
public interface AdjustmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAdjustment(Adjustment adjustment);

    @Update
    void updateAdjustment(Adjustment adjustment);

    @Delete
    void deleteAdjustment(Adjustment adjustment);

    @Query("DELETE FROM adjustment")
    void deleteAll();

    @Query("SELECT * FROM adjustment ORDER BY date ASC")
    LiveData<List<Adjustment>> getAllDateASC();

    @Query("SELECT * FROM adjustment ORDER BY date DESC")
    LiveData<List<Adjustment>> getAllDateDESC();

    @Query("SELECT * FROM adjustment ORDER BY distance ASC")
    LiveData<List<Adjustment>> getAllDistanceASC();

    @Query("SELECT * FROM adjustment ORDER BY distance DESC")
    LiveData<List<Adjustment>> getAllDistanceDESC();

    @Query("SELECT * FROM adjustment WHERE id=:id")
    LiveData<Adjustment> getNoteById(int id);
}
