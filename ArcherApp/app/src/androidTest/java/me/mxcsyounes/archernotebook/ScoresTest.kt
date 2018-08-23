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

    @Test
    @Throws(InterruptedException::class)
    fun basics() {

        val liveResult = dao!!.allScoresDESC

        liveResult.observeForever { scores ->
            this@ScoresTest.scores = scores
            responseLatch!!.countDown()
        }

        responseLatch!!.await()

        Assert.assertEquals(0, scores!!.size.toLong())

        val score = Score(1, 1, 1, "", "this is description", Date())

        dao!!.insertScore(score)
        val liveResult4 = dao!!.allScoresDESC

        liveResult4.observeForever { scores ->
            this@ScoresTest.scores = scores
            responseLatch!!.countDown()
        }

        responseLatch!!.await()

        Assert.assertEquals(1, scores!!.size.toLong())

        val liveScore = dao!!.getScoreWithId(1)

        liveScore.observeForever { score1 ->
            simpleScore = score1
            responseLatch!!.countDown()
        }


        responseLatch!!.await()

        Assert.assertTrue(areIdentical(simpleScore!!, score))

        val score1 = Score(1, 2, 1, "", "Another one", Date())


        dao!!.updateScore(score1)

        val liveScoreTwo = dao!!.getScoreWithId(1)

        liveScoreTwo.observeForever { score2 ->
            secondScore = score2
            responseLatch!!.countDown()
        }

        responseLatch!!.await()

        Assert.assertEquals(2, secondScore!!.distance.toLong())

        val score3 = Score(2, 2, 1, "", "Another one", Date())
        val score4 = Score(3, 2, 1, "", "Another one", Date())

        dao!!.insertScore(score3)
        dao!!.insertScore(score4)

        val liveResult1 = dao!!.allScoresDESC

        liveResult1.observeForever { scores1 ->
            scores = scores1
            responseLatch!!.countDown()
        }

        responseLatch!!.await()

        Assert.assertEquals(3, scores!!.size.toLong())

        dao!!.deleteScore(score3)

        val liveResult2 = dao!!.allScoresDESC

        liveResult2.observeForever { scores1 ->
            scores = scores1
            responseLatch!!.countDown()
        }

        responseLatch!!.await()

        Assert.assertEquals(2, scores!!.size.toLong())


        dao!!.deleteAllScores()


        val liveResult3 = dao!!.allScoresDESC

        liveResult3.observeForever { scores1 ->
            scores = scores1
            responseLatch!!.countDown()
        }

        responseLatch!!.await()

        Assert.assertEquals(0, scores!!.size.toLong())
    }

    private fun areIdentical(one: Score, two: Score): Boolean {
        return (one.date === two.date && one.description == two.description
                && one.distance == two.distance && one.id == two.id
                && one.paths == two.paths && one.type == two.type)
    }
}

