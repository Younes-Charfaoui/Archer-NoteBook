package me.mxcsyounes.archernotebook.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_score_sheet.*
import kotlinx.android.synthetic.main.content_score_sheet.*
import me.mxcsyounes.archernotebook.R

class ScoreSheetActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {


    }

    val marks: Array<Int> = arrayOf(-1, -1, -1, -1, -1, -1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_sheet)
        setSupportActionBar(score_sheet_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addClickListenerToArrow(arrowOne, arrowTwo, arrowThree, arrowFour, arrowFive, arrowSix)

        addClickListenerToMarks()

    }


    private fun addClickListenerToMarks(vararg views: View?) {

        for (view in views) {
            val tag = view?.tag as String
            val value = valueFromTag(tag)
        }
    }

    companion object {

        fun valueFromTag(tag: String): Int{
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
    }


    private fun clearImageArrow(it: View?) {
        if (it?.tag != "none") {
            (it as ImageView).setImageResource(R.drawable.ractangle_score)
            it.tag = "none"
        }
    }

    private fun addClickListenerToArrow(vararg views: View?) {
        for (view in views) {
            view?.setOnClickListener {
                clearImageArrow(it)
            }
        }
    }

}
