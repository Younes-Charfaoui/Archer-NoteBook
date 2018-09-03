package me.mxcsyounes.archernotebook.activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_add_score_sheet.*
import kotlinx.android.synthetic.main.content_add_score_sheet.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.model.ScoreRounds
import me.mxcsyounes.archernotebook.viewmodels.AddScoreSheetViewModel

class AddScoreSheetActivity : AppCompatActivity() {


    private lateinit var viewModel: AddScoreSheetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_score_sheet)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this).get(AddScoreSheetViewModel::class.java)


        addScoreSheetDistanceRadioGroup.setOnCheckedChangeListener { _, whichRadio ->
            viewModel.distance = AddAdjustmentActivity.getDistance(whichRadio)
        }

        addScoreSheetSheetTypeRadioGroup.setOnCheckedChangeListener { _, whichRadio ->
            viewModel.sheetType = if (whichRadio == R.id.add_score_sheet_tournament_radio) 1 else 2
        }

        addScoreSheetSeriesTypeRadioGroup.setOnCheckedChangeListener { _, whichRadio ->
            viewModel.seriesType = if (whichRadio == R.id.add_score_sheet_one_series_radio) 1 else 2
        }

        addScoreSheetStartButton.setOnClickListener({ _ ->
            val intent = Intent(this, ScoreSheetActivity::class.java)
            startActivityForResult(intent, REQUEST_SCORE_SHEET)
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_SCORE_SHEET && resultCode == Activity.RESULT_OK) {
            val scoreRaw = data?.getStringExtra(KEY_SCORE)
            viewModel.scores.add(ScoreRounds(scoreRaw!!))
            Log.i(TAG, scoreRaw)
            addScoreSheetLL.visibility = View.GONE


            if (viewModel.state() == 1) {

                toolbar.title = "Score Sheet"
                supportActionBar?.title = "Score Sheet"
                addScoreSheetStartButton.text = getString(R.string.continue_shooting_string)
                addScoreSheetStartButton.setOnClickListener {
                    val intent = Intent(this, ScoreSheetActivity::class.java)
                    startActivityForResult(intent, REQUEST_SCORE_SHEET)
                }

            } else {
                toolbar.title = "Score Sheet"
                supportActionBar?.title = "Score Sheet"
                addScoreSheetStartButton.text = getString(R.string.save_score_string)
                addScoreSheetStartButton.setOnClickListener {
                    viewModel.saveScore()
                    finish()
                }
            }
        }
    }

    companion object {
        const val KEY_SCORE = "keyScore"
        const val TAG = "AddScoreSheetActivity"
        const val REQUEST_SCORE_SHEET = 1454
    }
}
