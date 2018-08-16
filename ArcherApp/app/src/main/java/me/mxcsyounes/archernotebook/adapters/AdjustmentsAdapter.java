package me.mxcsyounes.archernotebook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.mxcsyounes.archernotebook.R;

public class AdjustmentsAdapter extends RecyclerView.Adapter<AdjustmentsAdapter.AdjustmentViewHolder> {

    private Context mContext;
    private List mAdjustmentsList;

    public AdjustmentsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public AdjustmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.adjustment_list_item_layout, parent, false);
        return new AdjustmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdjustmentViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return mAdjustmentsList == null ? 0 : mAdjustmentsList.size();
    }

    class AdjustmentViewHolder extends RecyclerView.ViewHolder {

        View mBackgroundCircle;
        TextView mDistanceTv, mDateTv, mAdjustParams;

        public AdjustmentViewHolder(View itemView) {
            super(itemView);
            mBackgroundCircle = itemView.findViewById(R.id.adjustment_background_circle);
            mDistanceTv = itemView.findViewById(R.id.adj_distance_text_view);
            mDateTv = itemView.findViewById(R.id.adj_date_text_view);
            mAdjustParams = itemView.findViewById(R.id.adj_type_text_view);
        }
    }
}
