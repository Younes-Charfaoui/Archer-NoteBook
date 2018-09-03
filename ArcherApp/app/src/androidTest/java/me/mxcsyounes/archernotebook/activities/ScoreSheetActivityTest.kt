package me.mxcsyounes.archernotebook.activities

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.mxcsyounes.archernotebook.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ScoreSheetActivityTestIns {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<ScoreSheetActivity>(ScoreSheetActivity::class.java)

    @Test
    fun test() {

        
    }
}