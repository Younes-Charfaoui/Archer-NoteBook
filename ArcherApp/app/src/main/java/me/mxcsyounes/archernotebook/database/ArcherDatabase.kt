package me.mxcsyounes.archernotebook.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import me.mxcsyounes.archernotebook.database.dao.AdjustmentDao
import me.mxcsyounes.archernotebook.database.dao.ScoreDao
import me.mxcsyounes.archernotebook.database.entities.Adjustment
import me.mxcsyounes.archernotebook.database.entities.Score

@Database(entities = arrayOf(Adjustment::class, Score::class), version = 1, exportSchema = false)
abstract class ArcherDatabase : RoomDatabase() {

    abstract fun mAdjDao(): AdjustmentDao

    abstract fun mScoreDao(): ScoreDao

    companion object {

        private const val DB_NAME = "archer.db"
        private var sInstance: ArcherDatabase? = null

        fun getInstance(context: Context, inMemory: Boolean): ArcherDatabase {
            if (sInstance == null) {
                synchronized(ArcherDatabase::class.java) {
                    if (inMemory) {
                        sInstance = Room.inMemoryDatabaseBuilder(context,
                                ArcherDatabase::class.java)
                                .build()
                    } else
                        sInstance = Room.databaseBuilder(context,
                                ArcherDatabase::class.java, DB_NAME)

                                .build()
                }
            }
            return sInstance!!
        }
    }
}
