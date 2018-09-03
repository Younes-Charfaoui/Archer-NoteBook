package me.mxcsyounes.archernotebook.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_score_sheet.*
import kotlinx.android.synthetic.main.content_add_score_sheet.*
import me.mxcsyounes.archernotebook.R

class AddScoreSheetActivity : AppCompatActivity() {

    private var distance: Int = 2
    private var sheetType: Int = 2
    private var seriesType: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_score_sheet)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        add_score_sheet_distance_radio_group.setOnCheckedChangeListener { _, whichRadio ->
            distance = AddAdjustmentActivity.getDistance(whichRadio)
        }

        add_score_sheet_sheet_type_radio_group.setOnCheckedChangeListener { _, whichRadio ->
            sheetType = if (whichRadio == R.id.add_score_sheet_tournament_radio) 1 else 2
        }

        add_score_sheet_series_type_radio_group.setOnCheckedChangeListener { _, whichRadio ->
            seriesType = if (whichRadio == R.id.add_score_sheet_one_series_radio) 1 else 2

        }

        add_score_sheet_start_button.setOnClickListener({ _ ->
            val intent = Intent(this, ScoreSheetActivity::class.java)
            intent.putExtra(KEY_DISTANCE, distance)
            intent.putExtra(KEY_SERIES_TYPE, seriesType)
            intent.putExtra(KEY_SHEET_TYPE, sheetType)
            startActivity(intent)
        })

    }

    companion object {
        const val KEY_DISTANCE = "keyDistance"
        const val KEY_SHEET_TYPE = "keySheetType"
        const val KEY_SERIES_TYPE = "keySeriesType"
    }
}
