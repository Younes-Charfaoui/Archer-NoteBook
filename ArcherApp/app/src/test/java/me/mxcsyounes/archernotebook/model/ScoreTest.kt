package me.mxcsyounes.archernotebook.model

import me.mxcsyounes.archernotebook.database.entities.Score
import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreTest {

    @Test
    fun testToString() {
        val rounds = mutableListOf<Round>()
        rounds.add(Round(1, arrayOf(10, 9, 8, 6, 5, 4)))
        rounds.add(Round(2, arrayOf(10, 9, 8, 6, 5, 4)))
        rounds.add(Round(3, arrayOf(10, 9, 8, 6, 5, 4)))
        rounds.add(Round(4, arrayOf(10, 10, 10, 0, 0, 0)))
        rounds.add(Round(5, arrayOf(10, 9, 8, 6, 5, 4)))
        rounds.add(Round(6, arrayOf(10, 9, 8, 6, 5, 4)))

        val score = ScoreRounds(rounds)
        assertEquals("10$9$8$6$5$4;10$9$8$6$5$4;10$9$8$6$5$4" +
                ";10$10$10$0$0$0;10$9$8$6$5$4;10$9$8$6$5$4"
                , score.toString())

        val roundsAfter = score.toString().split(";")
        assertEquals(6, roundsAfter.size)

        var round = roundsAfter[0]
        assertEquals("10$9$8$6$5$4", round)

        var arrows = round.split("$")
        assertEquals(6, arrows.size)

        var result = 0
        for (i in arrows) {
            result += i.toInt()
        }

        assertEquals(42, result)

        round = roundsAfter[3]
        assertEquals("10$10$10$0$0$0", round)

        arrows = round.split("$")
        assertEquals(6, arrows.size)

        result = 0
        for (i in arrows) {
            result += i.toInt()
        }

        assertEquals(30, result)
    }

    @Test
    fun testConstructor() {
        val score= ScoreRounds("10$9$8$6$5$4;10$9$8$6$5$4;10$9$8$6$5$4" +
                ";10$10$10$0$0$0;10$9$8$6$5$4;10$9$8$6$5$4")

        assertEquals(42, score.rounds[0].scores.sum())
        assertEquals(30, score.rounds[3].scores.sum())
    }


}