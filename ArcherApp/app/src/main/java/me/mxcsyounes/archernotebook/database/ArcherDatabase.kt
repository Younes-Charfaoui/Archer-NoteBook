package me.mxcsyounes.archernotebook.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import me.mxcsyounes.archernotebook.database.converters.DateConverters

import me.mxcsyounes.archernotebook.database.dao.AdjustmentDao
import me.mxcsyounes.archernotebook.database.dao.ScoreDao
import me.mxcsyounes.archernotebook.database.dao.ScoreSheetDao
import me.mxcsyounes.archernotebook.database.entities.Adjustment
import me.mxcsyounes.archernotebook.database.entities.Score
import me.mxcsyounes.archernotebook.database.entities.ScoreSheet

@Database(entities = [(Adjustment::class), (Score::class) ,(ScoreSheet::class)],
        version = 1, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class ArcherDatabase : RoomDatabase() {

    abstract fun mAdjDao(): AdjustmentDao

    abstract fun mScoreDao(): ScoreDao

    abstract fun scoreSheetDao(): ScoreSheetDao

    companion object {

        private const val DB_NAME = "archer.db"
        private var sInstance: ArcherDatabase? = null

        fun getInstance(context: Context, inMemory: Boolean = false): ArcherDatabase {
            if (sInstance == null) {
                synchronized(ArcherDatabase::class.java) {
                    sInstance = if (inMemory) {
                        Room.inMemoryDatabaseBuilder(context,
                                ArcherDatabase::class.java)
                                .build()
                    } else
                        Room.databaseBuilder(context,
                                ArcherDatabase::class.java, DB_NAME)
                                .build()
                }
            }
            return sInstance!!
        }
    }
}
