package me.mxcsyounes.archernotebook.database.entities

import me.mxcsyounes.archernotebook.model.Round
import me.mxcsyounes.archernotebook.model.ScoreRounds
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class ScoreSheetTest {

    @Test
    fun testNumberOfArrow() {
        val rounds = mutableListOf<Round>()
        rounds.add(Round(1, arrayOf(10, 9, 8, 6, 5, 4)))
        rounds.add(Round(2, arrayOf(10, 9, 8, 6, 5, 4)))
        rounds.add(Round(3, arrayOf(10, 9, 8, 6, 5, 4)))
        rounds.add(Round(4, arrayOf(10, 10, 10, 0, 0, 0)))
        rounds.add(Round(5, arrayOf(10, 9, 8, 6, 5, 4)))
        rounds.add(Round(6, arrayOf(10, 9, 8, 6, 5, 4)))

        val roundsTwo = mutableListOf<Round>()
        roundsTwo.add(Round(1, arrayOf(10, 9, 8, 6, 5, 4)))
        roundsTwo.add(Round(2, arrayOf(10, 9, 8, 6, 5, 4)))
        roundsTwo.add(Round(3, arrayOf(10, 9, 8, 6, 5, 4)))
        roundsTwo.add(Round(4, arrayOf(10, 10, 10, 0, 0, 0)))
        roundsTwo.add(Round(5, arrayOf(10, 9, 8, 6, 5, 4)))
        roundsTwo.add(Round(6, arrayOf(10, 9, 8, 6, 5, 4)))

        val scoreOne = ScoreRounds(rounds)
        val scoreTwo = ScoreRounds(roundsTwo)

        val all = scoreOne.toString() + "#" + scoreTwo.toString()

        val scoreSheet = ScoreSheet(1, Date(), 1, 2, 1, all)

        assertEquals(72, scoreSheet.numberOfArrows)

        val scoreSheetTwo  = ScoreSheet(1, Date(), 1, 1, 1, scoreOne.toString())

        assertEquals(36, scoreSheetTwo.numberOfArrows)

    }
}