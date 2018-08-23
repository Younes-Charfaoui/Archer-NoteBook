package me.mxcsyounes.archernotebook.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_score_sheet.*

import me.mxcsyounes.archernotebook.R

class ScoreSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_sheet)
        setSupportActionBar(score_sheet_toolbar)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //just testing if value are transmitted correctly
        Toast.makeText(this, "Distance " + intent
                .getIntExtra(AddScoreSheetActivity.KEY_DISTANCE, -1),
                Toast.LENGTH_SHORT).show()
    }

}
