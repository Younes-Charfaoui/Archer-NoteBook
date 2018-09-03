package me.mxcsyounes.archernotebook.viewmodels

import android.arch.lifecycle.ViewModel
import me.mxcsyounes.archernotebook.model.Round
import me.mxcsyounes.archernotebook.model.Score

class ScoreSheetViewModel : ViewModel() {

    private var counter: Int = 0
    private val rounds = mutableListOf<Round>()
    private val scores = mutableListOf<Score>()

    fun init() {
        counter = 0
        for (i in 1..6) {
            rounds.add(Round(i, arrayOf(-1, -1, -1, -1, -1, -1)))
        }
    }

    fun nextRound(): Boolean {
        if (counter + 1 <= 5) {
            counter++
            return true
        }
        return false
    }

    fun previousRound(): Boolean {
        if (counter - 1 >= 0) {
            counter--
            return true
        }
        return false
    }

    fun goToRound(round: Int) {
        counter = round
    }

    val currentRoundNumberTitle: String
        get() = "Round #${rounds[counter].number}"

    val currentRoundNumber: Int
        get() = counter

    companion object {

        fun sumOfVolet(marks: Array<Int>): Int {
            var result = 0
            for (i in marks) {
                if (i == 11)
                    result += 10
                else if (i != -1)
                    result += i
            }
            return result
        }
    }

    val currentRoundScore: Array<Int>
        get() = rounds[counter].scores

    fun sortCurrent() {
        rounds[counter].scores = rounds[counter].scores.sortedArrayDescending()
    }

    val currentRoundScoreSum: Int
        get() = sumOfVolet(currentRoundScore)

    fun validateSeries(): Int {
        for (round in rounds) {
            if (round.scores.contains(-1))
                return round.number - 1
        }
        return -1
    }

    fun addScore() {
        scores.add(Score(rounds))
    }
}