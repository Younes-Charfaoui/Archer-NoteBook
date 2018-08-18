package me.mxcsyounes.archernotebook.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import me.mxcsyounes.archernotebook.R;

public class AddAdjustmentPhotosDialog extends DialogFragment {


    private AdjustmentPhotoListener mInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterface = (AdjustmentPhotoListener) context;
    }

    int counter = 0;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder textDialog = new AlertDialog.Builder(getContext());

        textDialog.setTitle("Photo Adjusts.");

        textDialog.setMessage("Please take photos for your bow.");

        View view = getActivity().getLayoutInflater().inflate(R.layout.adjust_photos_dialog, null);

        Button addPhotoBtn = view.findViewById(R.id.adjust_button_add_photo);
        TextView photoInfoTv = view.findViewById(R.id.adjust_photo_state_text_view);

        addPhotoBtn.setOnClickListener(v -> {
            takePhoto();
            counter++;
            photoInfoTv.setText(counter + " photo are taken.");
        });

        textDialog.setView(view);

        textDialog.setPositiveButton("Finish", (dialogInterface, i) -> {
            mInterface.onAdjustmentPhotoComplete("");
        });


        return textDialog.create();
    }

    public interface AdjustmentPhotoListener {
        void onAdjustmentPhotoComplete(String... paths);
    }


    static final int REQUES_IMAGE_CAPTURE = 1;

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (getActivity() != null)
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    Toast.makeText(getContext(), "Something Wrong happend.", Toast.LENGTH_SHORT).show();
                }

                if (photoFile != null) {
                    Uri photoUri = FileProvider.getUriForFile(Objects.requireNonNull(getContext()),"" , photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, REQUES_IMAGE_CAPTURE);
                }

            } else
                Toast.makeText(getContext(), "No app to take photo.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "Problem in the app, try later.", Toast.LENGTH_SHORT).show();
    }

    String mCurrentPhotoFile;

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.FRANCE).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getFilesDir();
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        mCurrentPhotoFile = image.getAbsolutePath();
        return image;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
