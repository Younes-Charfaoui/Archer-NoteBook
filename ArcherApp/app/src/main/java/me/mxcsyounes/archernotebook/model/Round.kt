package me.mxcsyounes.archernotebook.model

class Round(val number: Int, var scores: Array<Int>) {

    companion object {
        fun sumRound(scores: Array<Int>): Int {
            var result = 0
            for (i in scores) {
                result += if (i == 11) 10 else i
            }
            return result
        }
    }
}