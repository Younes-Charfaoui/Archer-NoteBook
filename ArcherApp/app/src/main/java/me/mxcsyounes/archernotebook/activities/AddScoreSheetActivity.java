package me.mxcsyounes.archernotebook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;

import me.mxcsyounes.archernotebook.R;

public class AddScoreSheetActivity extends AppCompatActivity {

    public static final String KEY_DISTANCE = "keyDistance",
            KEY_SHEET_TYPE = "keySheetType",
            KEY_SERIES_TYPE = "keySeriesType";

    private int mDistance, mSheetType, mSeriesType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score_sheet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDistance = 2;
        mSeriesType = 2;
        mSheetType = 2;

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((RadioGroup) findViewById(R.id.add_score_sheet_distance_radio_group)).setOnCheckedChangeListener((radioGroup, whichRadio) -> {
            mDistance = AddAdjustmentActivity.getDistance(whichRadio);
        });

        ((RadioGroup) findViewById(R.id.add_score_sheet_sheet_type_radio_group)).setOnCheckedChangeListener((radioGroup, whichRadio) -> {
            if (whichRadio == R.id.add_score_sheet_tournament_radio)
                mSheetType = 1;
            else
                mSheetType = 2;
        });

        ((RadioGroup) findViewById(R.id.add_score_sheet_series_type_radio_group)).setOnCheckedChangeListener((radioGroup, whichRadio) -> {
            if (whichRadio == R.id.add_score_sheet_one_series_radio)
                mSeriesType = 1;
            else
                mSeriesType = 2;
        });


        findViewById(R.id.add_score_sheet_start_button).setOnClickListener(view -> {
            Intent intent = new Intent(this, ScoreSheetActivity.class);
            intent.putExtra(KEY_DISTANCE, mDistance);
            intent.putExtra(KEY_SERIES_TYPE, mSeriesType);
            intent.putExtra(KEY_SHEET_TYPE, mSheetType);
            startActivity(intent);
        });


    }

}
