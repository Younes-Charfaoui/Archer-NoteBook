package me.mxcsyounes.archernotebook.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class AddAdjustmentDistanceDialog extends DialogFragment {

    private String[] distances = {"90 meters", "70 meters",
            "60 meters", "50 meters",
            "30 meters", "18 meters"};
    private AdjustmentDistanceListener mInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterface = (AdjustmentDistanceListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder distanceDialog = new AlertDialog.Builder(getContext());

        distanceDialog.setTitle("Choose a distance.");


        distanceDialog.setItems(distances, (dialogInterface, position) -> {
            mInterface.onAdjustmentDistanceComplete(position);
            dismiss();
        });

        distanceDialog.setNegativeButton("Cancel", null);

        return distanceDialog.create();
    }

    public interface AdjustmentDistanceListener {
        void onAdjustmentDistanceComplete(int distance);
    }
}
