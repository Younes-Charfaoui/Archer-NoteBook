package me.mxcsyounes.archernotebook.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.mxcsyounes.archernotebook.R;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.scores_add_score_card).setOnClickListener(v -> {
            // TODO: 18-Aug-18 Launch adding score process
        });

        findViewById(R.id.scores_progress_card).setOnClickListener(v -> {
            // TODO: 18-Aug-18  launch preview of progress
        });

        findViewById(R.id.scores_add_score_card).setOnClickListener(v -> {
            // TODO: 18-Aug-18 launch activity of scores
        });
    }

}
