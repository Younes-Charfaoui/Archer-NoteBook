package me.mxcsyounes.archernotebook.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;

import me.mxcsyounes.archernotebook.R;

public class AddScoreSheetActivity extends AppCompatActivity {

    private int mDistance, mSheetType, mSeriesType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score_sheet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDistance = R.id.add_adjust_70_meter_radio;
        mSeriesType = R.id.add_score_sheet_one_series_radio;
        mSheetType = R.id.add_score_sheet_training_radio;

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((RadioGroup) findViewById(R.id.add_score_sheet_distance_radio_group)).setOnCheckedChangeListener((radioGroup, whichRadio) -> {
            mDistance = whichRadio;
        });

        ((RadioGroup) findViewById(R.id.add_score_sheet_sheet_type_radio_group)).setOnCheckedChangeListener((radioGroup, whichRadio) -> {
            mSheetType = whichRadio;
        });

        ((RadioGroup) findViewById(R.id.add_score_sheet_series_type_radio_group)).setOnCheckedChangeListener((radioGroup, whichRadio) -> {
            mSeriesType = whichRadio;
        });


    }

}
