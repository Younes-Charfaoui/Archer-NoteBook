package me.mxcsyounes.archernotebook.viewmodels

import android.arch.lifecycle.ViewModel
import me.mxcsyounes.archernotebook.model.Round
import me.mxcsyounes.archernotebook.model.ScoreRounds

class ScoreSheetViewModel : ViewModel() {

    private var counter: Int = 0
    private val rounds = mutableListOf<Round>()
    var distance = 0

    fun init(distance: Int) {
        counter = 0
        this.distance = distance
        if (distance == 6) {
            for (i in 1..10) {
                rounds.add(Round(i, arrayOf(-1, -1, -1)))
            }
        } else {
            for (i in 1..6) {
                rounds.add(Round(i, arrayOf(-1, -1, -1, -1, -1, -1)))
            }
        }

    }

    fun nextRound(): Boolean {
        if (distance == 6) {
            if (counter + 1 <= 9) {
                counter++
                return true
            }
        } else {
            if (counter + 1 <= 5) {
                counter++
                return true
            }
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

    val isFirstRound: Boolean
        get() = counter == 0

    val isLastRound: Boolean
        get() = counter == rounds.size - 1

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

    fun clear() {
        rounds.clear()
    }

    val finalScore: String
        get() = ScoreRounds(rounds).toString()

}