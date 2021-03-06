package me.mxcsyounes.archernotebook.activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
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

        addScoreSheetStartButton.setOnClickListener { _ ->
            launchScoreSheetActivity()
        }
    }

    private fun launchScoreSheetActivity(){
        val intent = Intent(this, ScoreSheetActivity::class.java)
        intent.putExtra(KEY_DISTANCE, viewModel.distance)
        startActivityForResult(intent, REQUEST_SCORE_SHEET)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_SCORE_SHEET && resultCode == Activity.RESULT_OK) {
            val scoreRaw = data?.getStringExtra(KEY_SCORE)
            viewModel.scores.add(ScoreRounds(scoreRaw!!))
            toolbar.title = "Score Sheet"
            supportActionBar?.title = "Score Sheet"

            addScoreSheetSeriesTypeRadioGroup.visibility = View.GONE
            addScoreSheetSheetTypeRadioGroup.visibility = View.GONE
            addScoreSheetDistanceRadioGroup.visibility = View.GONE
            addScoreSheetTypeSeriesTv.visibility = View.GONE
            addScoreSheetScoreSeriesTv.visibility = View.VISIBLE
            addScoreSheetProgressGraphView.visibility = View.VISIBLE

            addScoreSheetTypeOfSheetTv.text = getString(R.string.progress)

            if (viewModel.state() == 1) {
                addScoreSheetDistanceTv.text = getString(R.string.first_serie)
                addScoreSheetScoreSeriesTv.text = viewModel.firstScore

                //display the line of the series
                val lineGraphSeries = LineGraphSeries<DataPoint>(viewModel.scoreSeries(1))
                addScoreSheetProgressGraphView.addSeries(lineGraphSeries)

                addScoreSheetStartButton.text = getString(R.string.continue_shooting_string)
                addScoreSheetStartButton.setOnClickListener {
                    launchScoreSheetActivity()
                }

            } else {

                if (viewModel.seriesType == 2) {
                    addScoreSheetDistanceTv.text = getString(R.string.second_serie)
                    addScoreSheetScoreSeriesTv.text = viewModel.secondScore

                    //display the line of the series
                    val lineGraphSeriesOne = LineGraphSeries<DataPoint>(viewModel.scoreSeries(1))
                    val lineGraphSeriesTwo = LineGraphSeries<DataPoint>(viewModel.scoreSeries(2))

                    addScoreSheetProgressGraphView.addSeries(lineGraphSeriesOne)
                    addScoreSheetProgressGraphView.addSeries(lineGraphSeriesTwo)
                } else {
                    addScoreSheetDistanceTv.text = getString(R.string.first_serie)
                    addScoreSheetScoreSeriesTv.text = viewModel.firstScore

                    //display the line of the series
                    val lineGraphSeries = LineGraphSeries<DataPoint>(viewModel.scoreSeries(1))
                    addScoreSheetProgressGraphView.addSeries(lineGraphSeries)
                }

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
        const val KEY_DISTANCE = "keyScore"
        const val TAG = "AddScoreSheetActivity"
        const val REQUEST_SCORE_SHEET = 1454
    }
}
