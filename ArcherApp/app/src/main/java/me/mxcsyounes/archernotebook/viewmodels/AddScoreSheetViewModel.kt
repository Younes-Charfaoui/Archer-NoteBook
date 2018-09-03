package me.mxcsyounes.archernotebook.viewmodels

import android.arch.lifecycle.ViewModel
import me.mxcsyounes.archernotebook.model.ScoreRounds

class AddScoreSheetViewModel : ViewModel() {

    var distance: Int = 2
    var sheetType: Int = 2
    var seriesType: Int = 2

    var scores = mutableListOf<ScoreRounds>()

    fun state(): Int {
        return if (seriesType == 2) {
            when (scores.size) {
                0 -> 2
                1 -> 1
                else -> 0
            }
        } else {
            if (scores.size == 1) 0
            else 1
        }

    }

}