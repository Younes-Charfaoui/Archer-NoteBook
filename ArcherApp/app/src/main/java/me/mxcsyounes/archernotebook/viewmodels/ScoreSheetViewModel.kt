package me.mxcsyounes.archernotebook.viewmodels

import android.arch.lifecycle.ViewModel
import me.mxcsyounes.archernotebook.model.Round

class ScoreSheetViewModel : ViewModel() {

    private var counter: Int = 0
    private val rounds = mutableListOf<Round>()

    fun init() {
        counter = 0
        for (i in 1..6) {
            rounds.add(Round(i, arrayOf(-1, -1, -1, -1, -1, -1)))
        }
    }

    fun nextRound(): Boolean {
        if (counter + 1 <= 6) {
            counter++
            return true
        }
        return false
    }

    fun previousRound(): Boolean {
        if (counter - 1 >= 1) {
            counter--
            return true
        }
        return false
    }

    val currentRoundNumberTitle: String
        get() = "Round #${rounds[counter].number}"

    private fun sumOfVolet(marks: Array<Int>): Int {
        var result = 0
        for (i in marks) {
            if (i == 11)
                result += 10
            else if (i != -1)
                result += i
        }
        return result
    }

    val currentRoundScore: Array<Int>
        get() = rounds[counter].scores

    fun sortCurrent() {
        rounds[counter].scores = rounds[counter].scores.sortedArrayDescending()
    }

    val currentRoundScoreSum: Int
        get() = sumOfVolet(currentRoundScore)
}