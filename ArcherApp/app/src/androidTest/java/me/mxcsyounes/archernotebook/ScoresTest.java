package me.mxcsyounes.archernotebook;


import android.arch.lifecycle.LiveData;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import me.mxcsyounes.archernotebook.database.ArcherDatabase;
import me.mxcsyounes.archernotebook.database.dao.ScoreDao;
import me.mxcsyounes.archernotebook.database.entities.Score;

@RunWith(AndroidJUnit4.class)
public class ScoresTest {

    private ArcherDatabase db;
    private ScoreDao dao;
    private Score simpleScore, secondScore;
    private List<Score> scores;
    private CountDownLatch responseLatch;

    @Before
    public void setUp() {
        db = ArcherDatabase.getInstance(InstrumentationRegistry.getTargetContext(), true);
        dao = db.mScoreDao();
        responseLatch = new CountDownLatch(1);
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void basics() throws InterruptedException {

        final LiveData<List<Score>> liveResult = dao.getAllScoresDESC();

        liveResult.observeForever(scores -> {
            ScoresTest.this.scores = scores;
            responseLatch.countDown();
        });

        responseLatch.await();

        Assert.assertEquals(0, scores.size());

        Score score = new Score(1, 1, 1, "", "this is description", new Date());

        dao.insertScore(score);
        final LiveData<List<Score>> liveResult4 = dao.getAllScoresDESC();

        liveResult4.observeForever(scores -> {
            ScoresTest.this.scores = scores;
            responseLatch.countDown();
        });

        responseLatch.await();

        Assert.assertEquals(1, scores.size());

        final LiveData<Score> liveScore = dao.getScoreWithId(1);

        liveScore.observeForever(score1 -> {
            simpleScore = score1;
            responseLatch.countDown();
        });


        responseLatch.await();

        Assert.assertTrue(areIdentical(simpleScore, score));

        Score score1 = new Score(1, 2, 1, "", "Another one", new Date());


        dao.updateScore(score1);

        final LiveData<Score> liveScoreTwo = dao.getScoreWithId(1);

        liveScoreTwo.observeForever(score2 -> {
            secondScore = score2;
            responseLatch.countDown();
        });

        responseLatch.await();

        Assert.assertEquals(2, secondScore.getDistance());

        Score score3 = new Score(2, 2, 1, "", "Another one", new Date());
        Score score4 = new Score(3, 2, 1, "", "Another one", new Date());

        dao.insertScore(score3);
        dao.insertScore(score4);

        final LiveData<List<Score>> liveResult1 = dao.getAllScoresDESC();

        liveResult1.observeForever(scores1 -> {
            scores = scores1;
            responseLatch.countDown();
        });

        responseLatch.await();

        Assert.assertEquals(3, scores.size());

        dao.deleteScore(score3);

        final LiveData<List<Score>> liveResult2 = dao.getAllScoresDESC();

        liveResult2.observeForever(scores1 -> {
            scores = scores1;
            responseLatch.countDown();
        });

        responseLatch.await();

        Assert.assertEquals(2, scores.size());


        dao.deleteAllScores();


        final LiveData<List<Score>> liveResult3 = dao.getAllScoresDESC();

        liveResult3.observeForever(scores1 -> {
            scores = scores1;
            responseLatch.countDown();
        });

        responseLatch.await();

        Assert.assertEquals(0, scores.size());
    }

    private boolean areIdentical(Score one, Score two) {
        return one.getDate() == two.getDate() && one.getDescription().equals(two.getDescription())
                && one.getDistance() == two.getDistance() && one.getId() == two.getId()
                && one.getPaths().equals(two.getPaths()) && one.getType() == two.getType();
    }
}

