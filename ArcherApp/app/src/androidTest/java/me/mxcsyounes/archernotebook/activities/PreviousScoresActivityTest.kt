package me.mxcsyounes.archernotebook.activities

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.mxcsyounes.archernotebook.viewmodels.PreviousScoresViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class PreviousScoresActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<PreviousScoresActivity>(PreviousScoresActivity::class.java,
            true, false)

    @Test
    fun test() {

    }
}