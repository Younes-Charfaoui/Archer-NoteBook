package me.mxcsyounes.archernotebook.activities

import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreSheetActivityTest {

    @Test
    fun valueFromTagTest() {
        var value = ScoreSheetActivity.valueFromTag("X")
        assertEquals(11, value)

        value  = ScoreSheetActivity.valueFromTag("9")
        assertEquals(9, value)

        value  = ScoreSheetActivity.valueFromTag("3")
        assertEquals(3, value)

        value  = ScoreSheetActivity.valueFromTag("M")
        assertEquals(0, value)

        value  = ScoreSheetActivity.valueFromTag("hello")
        assertEquals(-1, value)
    }

}