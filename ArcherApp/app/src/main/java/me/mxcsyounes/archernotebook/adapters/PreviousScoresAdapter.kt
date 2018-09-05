package me.mxcsyounes.archernotebook.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
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

class PreviousScoresAdapter(val context: Context, private val listener: PreviousScoreListener) : RecyclerView.Adapter<PreviousScoresAdapter.PreviousScoreViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private var scoresList: List<ScoreSheet>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousScoreViewHolder {
        val view = layoutInflater.inflate(R.layout.score_list_item, parent, false)
        return PreviousScoreViewHolder(view)
    }

    override fun getItemCount() = scoresList?.size ?: 0

    override fun onBindViewHolder(holder: PreviousScoreViewHolder, position: Int) {
        with(scoresList?.get(position)) {

            val colors = getColor(this)

            holder.scoreTv.text = this?.sumOfAll().toString()

            val format = SimpleDateFormat("E dd MMM", Locale.getDefault())
            holder.scoreDateTv.text = format.format(this?.date)

            holder.scoreDateTv.setTextColor(ContextCompat.getColor(context, colors.textColor))
            holder.scoreTv.setTextColor(ContextCompat.getColor(context, colors.textColor))
            holder.scoresCard.setCardBackgroundColor(ContextCompat.getColor(context, colors.mainColor))
            holder.scoresDark.setBackgroundColor(ContextCompat.getColor(context, colors.darkColor))
        }
    }

    fun swapList(list: List<ScoreSheet>?) {
        this.scoresList = list
        notifyDataSetChanged()
    }

    inner class PreviousScoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val scoreTv: TextView = view.scoreSheetItemScore
        val scoreDateTv: TextView = view.scoreSheetItemDate
        val scoresCard: CardView = view as CardView
        val scoresDark: View = view.view

        init {
            view.setOnClickListener {
                listener.onClickScoreItem(scoresList?.get(adapterPosition)!!)
            }
        }
    }

    interface PreviousScoreListener {
        fun onClickScoreItem(scoreSheet: ScoreSheet)
    }

    private fun getColor(scoreSheet: ScoreSheet?): ColorScore {
        val average: Double = scoreSheet?.sumOfAll()?.toDouble()!! / scoreSheet.numberOfArrows.toDouble()

        return when (average.toInt()) {
            in 0 until 3 -> {
                ColorScore(R.color.black, R.color.whiteTwoDark, R.color.whiteTwo)
            }
            in 3 until 5 -> {
                ColorScore(R.color.whiteTwo, R.color.blackDark, R.color.black)
            }
            in 5 until 7 -> {
                ColorScore(R.color.whiteTwo, R.color.blueDark, R.color.blue)
            }
            in 7 until 9 -> {
                ColorScore(R.color.whiteTwo, R.color.redDark, R.color.red)
            }
            in 9 until 11 -> {
                ColorScore(R.color.black, R.color.yellowDark, R.color.yellow)
            }
            else -> {
                ColorScore(R.color.whiteTwo, R.color.colorPrimaryDark, R.color.colorPrimary)
            }
        }
    }
}

