package me.mxcsyounes.archernotebook.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_score_sheet.*
import kotlinx.android.synthetic.main.content_score_sheet.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.model.Round
import me.mxcsyounes.archernotebook.viewmodels.ScoreSheetViewModel

class ScoreSheetActivity : AppCompatActivity() {

    private val rounds = mutableListOf<Round>()
    private var counter = 1
    private lateinit var viewModel: ScoreSheetViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_sheet)
        setSupportActionBar(score_sheet_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this).get(ScoreSheetViewModel::class.java)
        viewModel.init()

        addClickListenerToArrow(arrowOne, arrowTwo, arrowThree, arrowFour, arrowFive, arrowSix)

        addClickListenerToMarks(imageX, imageTen, imageNine, imageEight,
                imageSeven, imageSix, imageFive, imageFour,
                imageThree, imageTwo, imageOne, imageMist)

        backArrowImage.setOnClickListener {
            if (viewModel.previousRound()) {
                showValuesInScreen()
            }
        }

        forwardArrowImage.setOnClickListener {
            if (viewModel.nextRound()) {
                showValuesInScreen()
            }
        }

        // initializing the score with 0
        scoreSumVoletTv.text = "0"

        // initializing the text with Round
        voletNumberTv.text = viewModel.currentRoundNumberTitle
    }

    private fun addClickListenerToArrow(vararg views: View?) {

        for (view in views) {
            view?.setOnClickListener {
                clearImageArrow(it)
                showValuesInScreen()
            }
        }
    }

    private fun addClickListenerToMarks(vararg views: View?) {

        for (view in views) {

            view?.setOnClickListener {
                val tag = it.tag as String
                val value = valueFromTag(tag)
                if (viewModel.currentRoundScore.contains(-1)) {
                    val index = viewModel.currentRoundScore.indexOf(-1)
                    viewModel.currentRoundScore[index] = value
                    showValuesInScreen()
                }
            }
        }
    }

    private fun showValuesInScreen() {

        voletNumberTv.text = viewModel.currentRoundNumberTitle

        viewModel.sortCurrent()

        for ((i, value) in viewModel.currentRoundScore.withIndex()) {
            val view = getViewByPosition(i)
            val valueOfView = valueFromTag(view?.tag.toString())
            if (value == -1) {
                view?.tag = "none"
                view?.setImageResource(R.drawable.ractangle_score)
            } else {
                if (value != valueOfView) {
                    val resourceId = getImageResourceByMark(value)
                    view?.tag = value.toString()
                    view?.setImageResource(resourceId)
                }
            }
        }

        showResultOfVolet()
    }

    private fun getImageResourceByMark(mark: Int): Int {
        return when (mark) {
            1 -> R.drawable.ic_one_1
            2 -> R.drawable.ic_two_2
            3 -> R.drawable.ic_three_3
            4 -> R.drawable.ic_four_4
            5 -> R.drawable.ic_five_5
            6 -> R.drawable.ic_six_6
            7 -> R.drawable.ic_seven_7
            8 -> R.drawable.ic_eight_8
            9 -> R.drawable.ic_nine_9
            10 -> R.drawable.ic_ten_10
            11 -> R.drawable.ic_ten_x
            0 -> R.drawable.ic_mist
            else -> R.drawable.ractangle_score
        }
    }

    /**
     * function to get the
     */
    private fun getViewByPosition(position: Int): ImageView? {
        return when (position) {
            0 -> arrowOne
            1 -> arrowTwo
            2 -> arrowThree
            3 -> arrowFour
            4 -> arrowFive
            5 -> arrowSix
            else -> null
        }
    }

    private fun clearImageArrow(it: View?) {
        if (it?.tag.toString() != "none") {
            (it as ImageView).setImageResource(R.drawable.ractangle_score)
            val value = valueFromTag(it.tag.toString())
            val index = viewModel.currentRoundScore.indexOf(value)
            viewModel.currentRoundScore[index] = -1
            it.tag = "none"
        }
    }

    private fun showResultOfVolet() {
        scoreSumVoletTv.text = viewModel.currentRoundScoreSum.toString()
    }

    companion object {

        fun valueFromTag(tag: String): Int {
            return try {
                tag.toInt()
            } catch (e: Exception) {
                -1
            }
        }

        const val TAG = "ScoreSheetActivity"
    }
}
