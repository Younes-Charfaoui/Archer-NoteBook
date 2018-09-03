package me.mxcsyounes.archernotebook.activities

import me.mxcsyounes.archernotebook.viewmodels.ScoreSheetViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreSheetActivityTest {

    @Test
    fun valueFromTagTest() {
        var value = ScoreSheetActivity.valueFromTag("X")
        assertEquals(11, value)

        value = ScoreSheetActivity.valueFromTag("9")
        assertEquals(9, value)

        value = ScoreSheetActivity.valueFromTag("3")
        assertEquals(3, value)

        value = ScoreSheetActivity.valueFromTag("M")
        assertEquals(0, value)

        value = ScoreSheetActivity.valueFromTag("hello")
        assertEquals(-1, value)
    }

    @Test
    fun sumOfVoletTest() {
        var array = arrayOf(10, 9, 8, 7, 6, 5)
        assertEquals(45, ScoreSheetViewModel.sumOfVolet(array))

        array = arrayOf(11, 9, -1, -1,-1, -1)
        assertEquals(19, ScoreSheetViewModel.sumOfVolet(array))

        array = arrayOf(11, 11, 2, 1, 0, 0)
        assertEquals(23, ScoreSheetViewModel.sumOfVolet(array))

        array = arrayOf(10, 10, 9, 2, 2, -1)
        assertEquals(33, ScoreSheetViewModel.sumOfVolet(array))
    }
}