package me.mxcsyounes.archernotebook.activities;

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_scores.*
import kotlinx.android.synthetic.main.content_scores.*
import me.mxcsyounes.archernotebook.R

class ScoresActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        setSupportActionBar(scores_toolbar)

        MobileAds.initialize(this, resources.getString(R.string.app_id_ad_test))

        scores_ad_view.loadAd(AdRequest.Builder().build())

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        scoresAddScoreCard.setOnClickListener {
            startActivity(Intent(this, AddScoreSheetActivity::class.java))
        }

        scoresProgressCard.setOnClickListener {
            // TODO: 18-Aug-18 Launch progress scores activity
        }

        scoresScoresCard.setOnClickListener {
            startActivity(Intent(this, PreviousScoresActivity::class.java))
        }


    }

}
