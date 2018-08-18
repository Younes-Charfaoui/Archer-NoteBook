package me.mxcsyounes.archernotebook.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

import me.mxcsyounes.archernotebook.R;

public class AddAdjustmentTextsDialog extends DialogFragment {


    private AdjustmentTextListener mInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterface = (AdjustmentTextListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder textDialog = new AlertDialog.Builder(getContext());

        textDialog.setTitle("Text Adjusts.");

        textDialog.setMessage("Please fill the information in the text fields");

        View view = getActivity().getLayoutInflater().inflate(R.layout.adjust_texts_dialog, null);

        textDialog.setView(view);

        textDialog.setPositiveButton("Next", (dialogInterface, i) -> {
            mInterface.onAdjustmentTextComplete("", "", "");
        });

        textDialog.setNegativeButton("Cancel", null);

        return textDialog.create();
    }

    public interface AdjustmentTextListener {
        void onAdjustmentTextComplete(String vertical, String horizontal, String description);
    }
}
