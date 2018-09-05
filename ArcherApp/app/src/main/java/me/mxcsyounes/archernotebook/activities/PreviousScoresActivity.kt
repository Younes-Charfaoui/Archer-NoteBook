package me.mxcsyounes.archernotebook.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_previous_scores.*
import kotlinx.android.synthetic.main.content_previous_scores.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.adapters.PreviousScoresAdapter
import me.mxcsyounes.archernotebook.database.entities.ScoreSheet
import me.mxcsyounes.archernotebook.viewmodels.PreviousScoresViewModel

class PreviousScoresActivity : AppCompatActivity(), PreviousScoresAdapter.PreviousScoreListener {

    lateinit var viewModel: PreviousScoresViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_scores)
        setSupportActionBar(previousScoreToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        MobileAds.initialize(this, resources.getString(R.string.app_id_ad_test))

        adViewPreviousScores.loadAd(AdRequest.Builder().build())

        adViewPreviousScores.adListener = object : AdListener() {
            override fun onAdLoaded() {
                adViewPreviousScores.visibility = View.VISIBLE
            }
        }

        viewModel = ViewModelProviders.of(this).get(PreviousScoresViewModel::class.java)

        val adapter = PreviousScoresAdapter(this, this)

        previousScoresRecyclerView.adapter = adapter
        val manager = GridLayoutManager(this, 2)
        previousScoresRecyclerView.layoutManager = manager
        previousScoresRecyclerView.setHasFixedSize(true)

        viewModel.allScores.observe(this, Observer {
            previousScoresProgressBar.visibility = View.GONE
            if (it != null && it.size > 0) {
                previousScoresEmptyLayout.visibility = View.GONE
                previousScoresRecyclerView.visibility = View.VISIBLE
                adapter.swapList(it)
            } else {
                adapter.swapList(null)
                previousScoresEmptyLayout.visibility = View.VISIBLE
            }
        })
    }


    override fun onClickScoreItem(scoreSheet: ScoreSheet) {
        Toast.makeText(this, "id is ${scoreSheet.score}", Toast.LENGTH_LONG).show()
    }
}
