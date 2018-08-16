package me.mxcsyounes.archernotebook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import me.mxcsyounes.archernotebook.R;
import me.mxcsyounes.archernotebook.database.entities.Adjustment;

public class AdjustmentsAdapter extends RecyclerView.Adapter<AdjustmentsAdapter.AdjustmentViewHolder> {

    private LayoutInflater mInflater;
    private List<Adjustment> mAdjustmentsList;

    public AdjustmentsAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public AdjustmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater
                .inflate(R.layout.adjustment_list_item_layout, parent, false);
        return new AdjustmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdjustmentViewHolder holder, int position) {
        Adjustment adjustment = mAdjustmentsList.get(position);
        String adjustmentParams = "Horizontal: " + adjustment.h_adj + ", Vertical: " + adjustment.v_adj;
        holder.mAdjustParams.setText(adjustmentParams);

        SimpleDateFormat format = new SimpleDateFormat("E dd MMM", Locale.getDefault());
        holder.mDateTv.setText(format.format(adjustment.date));

        holder.mDistanceTv.setText(getDistance(adjustment.distance));

    }

    public void setAdjustmentsList(List<Adjustment> list) {
        mAdjustmentsList = list;
        notifyDataSetChanged();
    }

    private String getDistance(int distance) {
        switch (distance) {
            case 1:
                return "90m";
            case 2:
                return "70m";
            case 3:
                return "50m";
            case 4:
                return "30m";
            case 5:
                return "18m";
            default:
                return "";
        }

    }

    @Override
    public int getItemCount() {
        return mAdjustmentsList == null ? 0 : mAdjustmentsList.size();
    }

    class AdjustmentViewHolder extends RecyclerView.ViewHolder {

        View mBackgroundCircle;
        TextView mDistanceTv, mDateTv, mAdjustParams;

        AdjustmentViewHolder(View itemView) {
            super(itemView);
            mBackgroundCircle = itemView.findViewById(R.id.adjustment_background_circle);
            mDistanceTv = itemView.findViewById(R.id.adj_distance_text_view);
            mDateTv = itemView.findViewById(R.id.adj_date_text_view);
            mAdjustParams = itemView.findViewById(R.id.adj_type_text_view);
        }
    }
}
