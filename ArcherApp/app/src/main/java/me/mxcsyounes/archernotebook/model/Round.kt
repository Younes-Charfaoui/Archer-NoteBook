package me.mxcsyounes.archernotebook.model

class Round(val number: Int, var scores: Array<Int>)

class ScoreRounds {

    val rounds: MutableList<Round>

    val total : Int
    get() {
        var result = 0
        for (round in rounds){
            result += round.scores.sum()
        }
        return result
    }

    val over : Int
        get() {
            return rounds[0].scores.size * rounds.size * 10
        }

    constructor(rounds: MutableList<Round>) {
        this.rounds = rounds
    }

    constructor(raw: String) {
        val rounds = mutableListOf<Round>()
        val roundsString = raw.split(";")
        for ((index, roundString) in roundsString.withIndex()) {
            val arrows = roundString.split("$")
            val arrowsRounds = mutableListOf<Int>()
            for (i in arrows) {
                arrowsRounds.add(i.toInt())
            }
            rounds.add(Round(index, arrowsRounds.toTypedArray()))
        }

        this.rounds = rounds
    }

    override fun toString(): String {

        return scoreToString(this)
    }

    companion object {

        fun scoreToString(scoreRounds: ScoreRounds): String {
            val stringBuilder = StringBuilder()
            for ((indexRound, round) in scoreRounds.rounds.withIndex()) {
                for ((index, value) in round.scores.withIndex()) {
                    stringBuilder.append(value)
                    if (index != round.scores.lastIndex)
                        stringBuilder.append("$")

                }
                if (indexRound != scoreRounds.rounds.lastIndex)
                    stringBuilder.append(";")
            }
            return stringBuilder.toString()
        }
    }
}