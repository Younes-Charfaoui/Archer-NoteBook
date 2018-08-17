package me.mxcsyounes.archernotebook.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Toast;

import me.mxcsyounes.archernotebook.R;

public class AddAdjustmentDialog extends DialogFragment {

    private String[] distances = {"90 meters", "70 meters",
            "60 meters", "50 meters",
            "30 meters", "18 meters"};
    private AdjustmentDistance mInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterface = (AdjustmentDistance) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder distanceDialog = new AlertDialog.Builder(getContext());

        View view = getActivity().getLayoutInflater().inflate(R.layout.adjust_distance_dialog, null);
        distanceDialog.setView(view);

        distanceDialog.setItems(distances, (dialogInterface, position) -> {
            mInterface.onAdjustmentDistanceComplete(position);
            Toast.makeText(getContext(), distances[position], Toast.LENGTH_SHORT).show();
            dismiss();
        });

        distanceDialog.setNegativeButton("Cancel", null);

        return distanceDialog.create();
    }

    public interface AdjustmentDistance {
        void onAdjustmentDistanceComplete(int distance);
    }
}
