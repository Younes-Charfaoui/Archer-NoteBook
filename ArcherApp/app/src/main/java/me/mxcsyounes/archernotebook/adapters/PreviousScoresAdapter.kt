package me.mxcsyounes.archernotebook.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.score_list_item.view.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.database.entities.ScoreSheet
import me.mxcsyounes.archernotebook.model.ColorScore
import java.text.SimpleDateFormat
import java.util.*

class PreviousScoresAdapter(context: Context, private val listener: PreviousScoreListener) : RecyclerView.Adapter<PreviousScoresAdapter.PreviousScoreViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private var scoresList: List<ScoreSheet>? = null

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
        notifyDataSetChanged()
    }

    inner class PreviousScoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val scoreTv: TextView = view.scoreSheetItemScore
        val scoreDateTv: TextView = view.scoreSheetItemDate

        init {
            view.setOnClickListener {
                listener.onClickScoreItem(scoresList?.get(adapterPosition)!!)
            }
        }
    }

    interface PreviousScoreListener {
        fun onClickScoreItem(scoreSheet: ScoreSheet)
    }

    fun getColor(scoreSheet: ScoreSheet): ColorScore {
        val average : Double = scoreSheet.sumOfAll().toDouble() / scoreSheet.numberOfArrows.toDouble()

        return when (average) {
            in 0..2 -> {
                ColorScore(1, 1, 1)
            }
            in 2..4 -> {
                ColorScore(1, 1, 1)
            }
            in 4..6 -> {
                ColorScore(1, 1, 1)
            }
            in 6..8 -> {
                ColorScore(1, 1, 1)
            }
            in 8..10 -> {
                ColorScore(1, 1, 1)
            }
            else -> {
                ColorScore(1, 1, 1)
            }
        }
    }
}

