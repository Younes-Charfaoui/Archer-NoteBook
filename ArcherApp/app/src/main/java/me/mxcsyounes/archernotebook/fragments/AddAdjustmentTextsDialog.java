package me.mxcsyounes.archernotebook.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

import me.mxcsyounes.archernotebook.R;

public class AddAdjustmentTextsDialog extends DialogFragment {


    private AdjustmentTextListener mInterface;

    private TextInputEditText horizontalEt, verticalEt, descriptionEt;


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

        View view = Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.adjust_texts_dialog, null);

        horizontalEt = view.findViewById(R.id.horizontal_adjust_input_edit_text);
        verticalEt = view.findViewById(R.id.vertical_adjust_input_edit_text);
        descriptionEt = view.findViewById(R.id.description_adjust_input_edit_text);

        textDialog.setView(view);

        textDialog.setPositiveButton("Next", null);


        textDialog.setNegativeButton("Cancel", null);
        AlertDialog finalOne = textDialog.create();
        finalOne.setOnShowListener(dialogInterface -> {
            finalOne.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                if (validate()) {
                    mInterface.onAdjustmentTextComplete(verticalEt.getText().toString(),
                            horizontalEt.getText().toString(),
                            descriptionEt.getText().toString());
                    dismiss();
                }
            });
        });
        return finalOne;
    }

    public interface AdjustmentTextListener {
        void onAdjustmentTextComplete(String vertical, String horizontal, String description);
    }

    private boolean validate() {
        String hText = horizontalEt.getText().toString().trim();
        String vText = verticalEt.getText().toString().trim();
        if (hText.length() == 0 && vText.length() == 0) {
            Toast.makeText(getContext(), "One of the adjustment should have a value.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
