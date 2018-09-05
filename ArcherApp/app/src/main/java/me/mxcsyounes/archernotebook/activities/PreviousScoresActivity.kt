package me.mxcsyounes.archernotebook.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
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

        viewModel = ViewModelProviders.of(this).get(PreviousScoresViewModel::class.java)

        val adapter = PreviousScoresAdapter(this, this)

        previousScoresRecyclerView.adapter = adapter
        previousScoresRecyclerView.layoutManager = GridLayoutManager(this, 2)
        previousScoresRecyclerView.setHasFixedSize(true)

        viewModel.allScores.observe(this, Observer {
            adapter.swapList(it)
        })
    }


    override fun onClickScoreItem(scoreSheet: ScoreSheet) {
        Toast.makeText(this, "id is ${scoreSheet.score}", Toast.LENGTH_LONG).show()
    }
}
