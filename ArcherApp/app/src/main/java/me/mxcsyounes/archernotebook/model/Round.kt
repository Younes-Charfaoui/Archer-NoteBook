package me.mxcsyounes.archernotebook.model

class Round(val number: Int, var scores: Array<Int>)

class Score(private val rounds: MutableList<Round>) {

    override fun toString(): String {

        return scoreToString(this)
    }

    companion object {

        fun scoreToString(score: Score): String {
            val stringBuilder = StringBuilder()
            for ((indexRound, round) in score.rounds.withIndex()) {
                for ((index, value) in round.scores.withIndex()) {
                    stringBuilder.append(value)
                    if (index != round.scores.lastIndex)
                        stringBuilder.append("$")

                }
                if (indexRound != score.rounds.lastIndex)
                    stringBuilder.append(";")
            }
            return stringBuilder.toString()
        }
    }
}