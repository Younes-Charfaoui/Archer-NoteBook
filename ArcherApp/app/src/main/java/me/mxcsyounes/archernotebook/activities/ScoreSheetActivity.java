package me.mxcsyounes.archernotebook.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import me.mxcsyounes.archernotebook.R;

public class ScoreSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_sheet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //just testing if value are transmitted correctly
        Toast.makeText(this, "Distance " + getIntent()
                        .getIntExtra(AddScoreSheetActivity.KEY_DISTANCE, -1),
                Toast.LENGTH_SHORT).show();
    }

}
