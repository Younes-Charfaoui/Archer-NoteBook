package me.mxcsyounes.archernotebook.adapters;

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.adjustment_list_item_layout.view.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.database.entities.Adjustment
import java.text.SimpleDateFormat
import java.util.*

class AdjustmentsAdapter(context: Context, listener: AdjustmentAdapterListener) : RecyclerView.Adapter<AdjustmentsAdapter.AdjustmentViewHolder>() {

    companion object {

        const val TAG = "Adapter";
    }


    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mAdjustmentsList: List<Adjustment>? = null
    private val mListener: AdjustmentAdapterListener = listener


    private fun getDistance(distance: Int?): String {
        return when (distance) {
            1 -> "90m"
            2 -> "70m"
            3 -> "60m"
            4 -> "50m"
            5 -> "30m"
            6 -> "18m"
            else -> ""
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdjustmentViewHolder {
        val view = mInflater
                .inflate(R.layout.adjustment_list_item_layout, parent, false)
        return AdjustmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdjustmentViewHolder, position: Int) {
        val adjustment: Adjustment? = mAdjustmentsList?.get(position)
        var finalString = ""
        if (adjustment?.horizontalAdjustment != null)
            finalString += "Horizontal: ${adjustment.horizontalAdjustment}"

        if (adjustment?.verticalAdjustment != null) {
            if (finalString.isNotEmpty()) finalString += ", "
            finalString += "Vertical: ${adjustment.verticalAdjustment}"
        }

        if (adjustment?.path != null) {
            if (finalString.isNotEmpty()) finalString += ", "
            finalString += "Photos: ${adjustment.path?.split(";")?.size} pics"
        }

        finalString += ".";
        holder.mAdjustParams.text = finalString;

        val format = SimpleDateFormat("E dd MMM", Locale.getDefault())
        holder.mDateTv.text = format.format(adjustment?.date);

        holder.mDistanceTv.text = getDistance(adjustment?.distance);

    }

    fun setAdjustmentsList(list: List<Adjustment>?) {
        this.mAdjustmentsList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (mAdjustmentsList != null)
            return mAdjustmentsList?.size!!
        return 0
    }

    interface AdjustmentAdapterListener {
        fun onAdjustmentClicked(adjustment: Adjustment?)
    }

    inner class AdjustmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mBackgroundCircle: View = view.adjustment_background_circle
        val mDistanceTv: TextView = view.adj_distance_text_view
        val mDateTv: TextView = view.adj_date_text_view
        val mAdjustParams: TextView = view.adj_type_text_view

        init {
            view.setOnClickListener {
                mListener.onAdjustmentClicked(mAdjustmentsList?.get(adapterPosition))
            }
        }
    }


}
