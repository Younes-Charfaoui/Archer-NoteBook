package me.mxcsyounes.archernotebook


import android.arch.lifecycle.LiveData

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import java.util.Date
import java.util.concurrent.CountDownLatch

import me.mxcsyounes.archernotebook.database.ArcherDatabase
import me.mxcsyounes.archernotebook.database.dao.ScoreDao
import me.mxcsyounes.archernotebook.database.entities.Score

@RunWith(AndroidJUnit4::class)
class ScoresTest {

    private var db: ArcherDatabase? = null
    private var dao: ScoreDao? = null
    private var simpleScore: Score? = null
    private var secondScore: Score? = null
    private var scores: List<Score>? = null
    private var responseLatch: CountDownLatch? = null

    @Before
    fun setUp() {
        db = ArcherDatabase.getInstance(InstrumentationRegistry.getTargetContext(), true)
        dao = db!!.mScoreDao()
        responseLatch = CountDownLatch(1)
    }

    @After
    fun tearDown() {
        db!!.close()
    }

}

