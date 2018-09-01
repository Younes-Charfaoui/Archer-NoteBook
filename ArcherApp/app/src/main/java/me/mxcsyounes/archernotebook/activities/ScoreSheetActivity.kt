package me.mxcsyounes.archernotebook.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_score_sheet.*
import kotlinx.android.synthetic.main.content_score_sheet.*
import me.mxcsyounes.archernotebook.R

class ScoreSheetActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {


    }

    val marks = arrayOf(-1, -1, -1, -1, -1, -1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_sheet)
        setSupportActionBar(score_sheet_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addClickListenerToArrow(arrowOne, arrowTwo, arrowThree, arrowFour, arrowFive, arrowSix)

        addClickListenerToMarks(imageX, imageTen, imageNine, imageEight,
                imageSeven, imageSix, imageFive, imageFour,
                imageThree, imageTwo, imageOne, imageMist)

    }


    private fun addClickListenerToMarks(vararg views: View?) {

        for (view in views) {
            view?.setOnClickListener {
                val tag = it.tag as String
                val value = valueFromTag(tag)
                if (marks.contains(-1)) {
                    val index = marks.indexOf(-1)
                    marks[index] = value
                    showValuesInScreen()
                }
            }
        }
    }

    private fun showValuesInScreen() {
        Log.i(TAG, "showValuesInScreen: array is $marks")
        marks.sortedArrayDescending()
        for ((i, value) in marks.withIndex()) {
            if (value == -1) continue
            val view = getViewByPosition(i)
            val valueOfView = valueFromTag(view?.tag.toString())
            Log.i(TAG, "the tag is $valueOfView")
            if (value != valueOfView) {
                val resourceId = getImageResourceByMark(value)
                view?.tag = value.toString()
                view?.setImageResource(resourceId)
            }
        }
    }

    private fun getImageResourceByMark(mark: Int): Int {
        Log.i(TAG, "the tag is $mark")
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

    private fun getViewByPosition(position: Int): ImageView? {
        Log.i(TAG, "getViewByPosition: position is $position")
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
        Log.i(TAG, "clearImageArrow: the tag before is ${it?.tag}")
        if (it?.tag.toString() != "none") {
            (it as ImageView).setImageResource(R.drawable.ractangle_score)
            val value = valueFromTag(it.tag.toString())
            val index = marks.indexOf(value)
            marks[index] = -1
            it.tag = "none"
            Log.i(TAG, "clearImageArrow: the tag after is ${it.tag}")
        }
    }

    private fun addClickListenerToArrow(vararg views: View?) {

        for (view in views) {
            view?.setOnClickListener {
                clearImageArrow(it)
                showValuesInScreen()
            }
        }
    }

    companion object {

        fun valueFromTag(tag: String): Int {
            Log.i(TAG, "valueFromTag: tag is $tag")
            return when (tag) {
                "X" -> 11
                "M" -> 0
                else -> {
                    try {
                        tag.toInt()
                    } catch (e: Exception) {
                        -1
                    }
                }
            }
        }

        const val TAG = "ScoreSheetActivity"
    }
}
