package me.mxcsyounes.archernotebook.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.score_list_item.view.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.database.entities.ScoreSheet
import java.text.SimpleDateFormat
import java.util.*

class PreviousScoresAdapter(context: Context, listnener: PreviousScoreListener) : RecyclerView.Adapter<PreviousScoresAdapter.PreviousScoreViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)
    var scoresList: List<ScoreSheet>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousScoreViewHolder {
        val view = layoutInflater.inflate(R.layout.score_list_item, parent, false)
        return PreviousScoreViewHolder(view)
    }

    override fun getItemCount() = scoresList?.size ?: 0

    override fun onBindViewHolder(holder: PreviousScoreViewHolder, position: Int) {
        with(scoresList?.get(position)) {
            holder.scoreTv.text = this?.sumOfAll().toString()
            val format = SimpleDateFormat("E dd MMM", Locale.getDefault())
            holder.scoreDateTv.text = format.format(this?.date)
        }
    }

    fun swapList(list: List<ScoreSheet>?) {
        this.scoresList = list
    }

    inner class PreviousScoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val scoreTv = view.scoreSheetItemScore
        val scoreDateTv = view.scoreSheetItemDate
    }

    interface PreviousScoreListener {
        fun onClickScoreItem(scoreSheet: ScoreSheet)
    }
}