package me.mxcsyounes.archernotebook.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.mxcsyounes.archernotebook.R;
import me.mxcsyounes.archernotebook.database.entities.Adjustment;

public class AddAdjustmentActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private int currentId;
    private TextInputEditText horizontalEt, verticalEt, descriptionEt;
    private String mCurrentPhotoFiles = "";
    private File currentImage;
    private int counter = 0;
    private TextView photoInfoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adjustment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, getResources().getString(R.string.app_id_ad_test));

        AdView mAdView = findViewById(R.id.add_adjustment_ad_view);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });

        currentId = R.id.add_adjust_70_meter_radio;


        findViewById(R.id.adjust_button_add_photo).setOnClickListener(view -> {
            takePhoto();
        });

        photoInfoTv = findViewById(R.id.adjust_photo_state_text_view);

        horizontalEt = findViewById(R.id.horizontal_adjust_input_edit_text);
        verticalEt = findViewById(R.id.vertical_adjust_input_edit_text);
        descriptionEt = findViewById(R.id.description_adjust_input_edit_text);


        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RadioGroup radioGroup = findViewById(R.id.add_adjutsment_distances_radio_group);

        radioGroup.setOnCheckedChangeListener((whichRadioGroup, idRadio) -> {
            currentId = idRadio;
        });


    }

    private boolean validate() {
        String hText = horizontalEt.getText().toString().trim();
        String vText = verticalEt.getText().toString().trim();
        String description = descriptionEt.getText().toString().trim();
        if (hText.length() == 0 && vText.length() == 0 && description.length() == 0) {
            Toast.makeText(this, "One of the adjustment should have a value.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(this, R.string.somthing_wrong, Toast.LENGTH_SHORT).show();
            }

            if (photoFile != null) {
                Uri imageUri = FileProvider.getUriForFile(this, "me.mxcsyounes.archernotebook.fileprovider", photoFile);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }

        } else
            Toast.makeText(this, "No app to take photo.", Toast.LENGTH_SHORT).show();

    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getFilesDir();
        currentImage = File.createTempFile(imageFileName, ".jpg", storageDir);
        return currentImage;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE)
            if (resultCode == Activity.RESULT_OK) {
                counter++;
                String currentPhotoText = counter + " " + getString(R.string.photo_string);
                photoInfoTv.setText(currentPhotoText);
                mCurrentPhotoFiles += currentImage.getAbsolutePath() + ";";
            } else {
                if (currentImage.exists())
                    deleteFile(currentImage.getName());
            }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_adjustment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_adjustment_done:
                if (validate()) {
                    Adjustment adjustment = new Adjustment();
                    adjustment.distance = getDistance(currentId);

                    String vertical = horizontalEt.getText().toString().trim();
                    String horizontal = verticalEt.getText().toString().trim();
                    String description = descriptionEt.getText().toString().trim();

                    if (vertical.trim().length() == 0)
                        adjustment.v_adj = null;
                    else
                        adjustment.v_adj = vertical;
                    if (horizontal.trim().length() == 0)
                        adjustment.h_adj = null;
                    else
                        adjustment.h_adj = horizontal;
                    if (description.trim().length() == 0)
                        adjustment.description = null;
                    else
                        adjustment.description = description;

                    if (mCurrentPhotoFiles.trim().length() == 0)
                        adjustment.path = null;
                    else
                        adjustment.path = mCurrentPhotoFiles;
                    adjustment.date = new Date();

                    Intent intent = new Intent();
                    intent.putExtra(AdjustmentsActivity.KEY_DATA, adjustment);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                // todo display successful message and Interstitial add.
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private int getDistance(int id) {
        switch (id) {
            case R.id.add_adjust_90_meter_radio:
                return 1;
            case R.id.add_adjust_70_meter_radio:
                return 2;
            case R.id.add_adjust_60_meter_radio:
                return 3;
            case R.id.add_adjust_50_meter_radio:
                return 4;
            case R.id.add_adjust_30_meter_radio:
                return 5;
            case R.id.add_adjust_18_meter_radio:
                return 6;
            default:
                throw new IllegalStateException();
        }

    }
}
