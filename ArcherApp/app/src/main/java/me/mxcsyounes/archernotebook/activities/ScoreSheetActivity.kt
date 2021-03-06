package me.mxcsyounes.archernotebook.activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.activity_score_sheet.*
import kotlinx.android.synthetic.main.content_score_sheet.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.viewmodels.ScoreSheetViewModel

class ScoreSheetActivity : AppCompatActivity() {

    private lateinit var viewModel: ScoreSheetViewModel
    private lateinit var interstitialAd: InterstitialAd
    private lateinit var marks: Array<View>
    private lateinit var arrows: Array<View>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_sheet)
        setSupportActionBar(score_sheet_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val distance = intent?.getIntExtra(AddScoreSheetActivity.KEY_DISTANCE, -1)

        viewModel = ViewModelProviders.of(this).get(ScoreSheetViewModel::class.java)
        viewModel.init(distance!!)

        interstitialAd = InterstitialAd(this)
        interstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        interstitialAd.loadAd(AdRequest.Builder().build())

        setupScreen(distance)

        addClickListenerToMarks()

        backArrowImage.visibility = View.INVISIBLE

        backArrowImage.setOnClickListener {
            if (viewModel.previousRound()) {
                updateScreen()
            }
        }

        forwardArrowImage.setOnClickListener {

            if (forwardArrowImage.tag == "back") {
                if (viewModel.nextRound()) {
                    updateScreen()
                }
            } else {
                val missing = viewModel.validateSeries()
                if (missing == -1) {
                    val data = Intent()
                    data.putExtra(AddScoreSheetActivity.KEY_SCORE, viewModel.finalScore)
                    setResult(Activity.RESULT_OK, data)
                    viewModel.clear()
                    if (interstitialAd.isLoaded) interstitialAd.show()
                    finish()
                } else {
                    viewModel.goToRound(missing)
                    updateScreen()
                }
            }
        }

        // initializing the score with 0
        scoreSumVoletTv.text = "0"

        // initializing the text with Round
        voletNumberTv.text = viewModel.currentRoundNumberTitle
    }

    private fun setupScreen(distance: Int) {
        if (distance == 6) {
            hideViews(arrowFour, arrowFive, arrowSix,
                    imageFive, imageFour,
                    imageThree, imageTwo, imageOne)
            arrows = arrayOf(arrowOne, arrowTwo, arrowThree)
            marks = arrayOf(imageX, imageTen, imageNine, imageEight,
                    imageSeven, imageSix, imageMist)

            makeConstraintFor18Meters()
        } else {
            arrows = arrayOf(arrowOne, arrowTwo, arrowThree, arrowFour, arrowFive, arrowSix)
            marks = arrayOf(imageX, imageTen, imageNine, imageEight,
                    imageSeven, imageSix, imageFive, imageFour,
                    imageThree, imageTwo, imageOne, imageMist)
        }
    }

    private fun makeConstraintFor18Meters() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(scoreSheetConstraint)
        constraintSet.clear(R.id.imageX, ConstraintSet.BOTTOM)
        constraintSet.clear(R.id.imageTen, ConstraintSet.BOTTOM)
        constraintSet.clear(R.id.imageNine, ConstraintSet.BOTTOM)
        constraintSet.clear(R.id.imageNine, ConstraintSet.END)
        constraintSet.clear(R.id.imageEight, ConstraintSet.BOTTOM)
        constraintSet.clear(R.id.imageEight, ConstraintSet.LEFT)
        constraintSet.clear(R.id.imageEight, ConstraintSet.RIGHT)
        constraintSet.clear(R.id.imageEight, ConstraintSet.START)
        constraintSet.clear(R.id.imageEight, ConstraintSet.END)
        constraintSet.clear(R.id.imageSeven, ConstraintSet.BOTTOM)
        constraintSet.clear(R.id.imageSix, ConstraintSet.BOTTOM)
        constraintSet.clear(R.id.imageMist, ConstraintSet.BOTTOM)
        constraintSet.clear(R.id.imageMist, ConstraintSet.END)
        constraintSet.clear(R.id.imageMist, ConstraintSet.START)
        constraintSet.clear(R.id.imageMist, ConstraintSet.END)
        constraintSet.connect(R.id.imageEight, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
        constraintSet.connect(R.id.imageEight, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)
        constraintSet.connect(R.id.imageMist, ConstraintSet.START, R.id.imageSix, ConstraintSet.END, 16)
        constraintSet.connect(R.id.imageMist, ConstraintSet.TOP, R.id.tapBellowTv, ConstraintSet.BOTTOM)
        constraintSet.connect(R.id.imageNine, ConstraintSet.END, R.id.imageEight, ConstraintSet.START, 16)
        constraintSet.applyTo(scoreSheetConstraint)
    }

    private fun hideViews(vararg views: View) {
        for (view in views) {
            view.visibility = View.GONE
        }
    }

    private fun updateScreen() {
        showValuesInScreen()
        setupListeners()
    }

    private fun addClickListenerToArrow(vararg views: View?) {

        for (view in views) {
            view?.setOnClickListener {
                clearImageArrow(it)
                showValuesInScreen()
            }
        }
    }

    private fun removeAllClickListenerToMarks() {
        removeClickListener(*marks)
    }

    private fun addClickListenerToMarks() {

        for (view in marks) {

            view.setOnClickListener {
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

    private fun removeClickListener(vararg views: View?) {

        for (view in views) {
            view?.setOnClickListener(null)
        }
    }

    private fun setupListeners() {
        if (!viewModel.currentRoundScore.contains(-1)) {
            removeAllClickListenerToMarks()
            addClickListenerToArrow(*arrows)
        } else {
            addClickListenerToMarks()
        }
    }

    private fun showValuesInScreen() {

        when {
            viewModel.isFirstRound -> {
                if (forwardArrowImage.tag == "done")
                    forwardArrowImage.setImageResource(R.drawable.ic_arrow_back_accent_24dp)

                backArrowImage.visibility = View.INVISIBLE
                forwardArrowImage.tag = "back"
            }
            viewModel.isLastRound -> {
                forwardArrowImage.setImageResource(R.drawable.ic_done_accent_24dp)
                if (forwardArrowImage.rotation == 180f) forwardArrowImage.rotation = 0f
                forwardArrowImage.tag = "done"
            }
            else -> {
                backArrowImage.visibility = View.VISIBLE
                forwardArrowImage.setImageResource(R.drawable.ic_arrow_back_accent_24dp)
                if (forwardArrowImage.rotation == 0f) forwardArrowImage.rotation = 180f
                forwardArrowImage.tag = "back"
            }
        }

        voletNumberTv.text = viewModel.currentRoundNumberTitle

        viewModel.sortCurrent()

        for ((i, value) in viewModel.currentRoundScore.withIndex()) {
            val view = getViewByPosition(i)
            val valueOfView = valueFromTag(view?.tag.toString())
            if (value == -1) {
                view?.tag = "none"
                view?.setImageResource(R.drawable.ractangle_score)
                removeClickListener(view)
            } else {
                if (value != valueOfView) {
                    val resourceId = getImageResourceByMark(value)
                    view?.tag = value.toString()
                    view?.setImageResource(resourceId)
                    addClickListenerToArrow(view)
                }
            }
        }

        if (!viewModel.currentRoundScore.contains(-1)) {
            removeAllClickListenerToMarks()
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
     * function to get the arrowImageView from the position
     * @param position : Int value of the position of the image
     * @return ImageView of which we gonna change the image of it.
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

    /**
     * function to clear the image of an arrow if it has an value
     */
    private fun clearImageArrow(it: View?) {
        if (it?.tag.toString() != "none") {
            (it as ImageView).setImageResource(R.drawable.ractangle_score)
            val value = valueFromTag(it.tag.toString())
            val index = viewModel.currentRoundScore.indexOf(value)
            viewModel.currentRoundScore[index] = -1
            it.tag = "none"
            addClickListenerToMarks()
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
