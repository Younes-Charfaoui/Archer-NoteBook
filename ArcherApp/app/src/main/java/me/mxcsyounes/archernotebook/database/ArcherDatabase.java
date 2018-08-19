package me.mxcsyounes.archernotebook.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import me.mxcsyounes.archernotebook.database.dao.AdjustmentDao;
import me.mxcsyounes.archernotebook.database.dao.ScoreDao;
import me.mxcsyounes.archernotebook.database.entities.Adjustment;
import me.mxcsyounes.archernotebook.database.entities.Score;

@Database(entities = {Adjustment.class, Score.class}, version = 2, exportSchema = false)
public abstract class ArcherDatabase extends RoomDatabase {

    private static final String DB_NAME = "archer.db";
    private static ArcherDatabase sInstance;

    @NonNull
    public static ArcherDatabase getInstance(final Context context, boolean inMemory) {
        if (sInstance == null) {
            synchronized (ArcherDatabase.class) {
                if (inMemory) {
                    sInstance = Room.inMemoryDatabaseBuilder(context,
                            ArcherDatabase.class)
                            .build();
                } else sInstance = Room.databaseBuilder(context,
                        ArcherDatabase.class, DB_NAME)

                        .build();
            }
        }
        return sInstance;
    }

    public abstract AdjustmentDao mAdjDao();

    public abstract ScoreDao mScoreDao();
}
