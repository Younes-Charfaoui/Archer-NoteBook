package me.mxcsyounes.archernotebook.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import me.mxcsyounes.archernotebook.R;
import me.mxcsyounes.archernotebook.utilities.PreferencesManager;

public class LauncherActivity extends AppCompatActivity {

    private static final long SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Class classes = WelcomeActivity.class;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        if (new PreferencesManager(this).isNotFirstTimeLaunched()) {
            //classes = MainActivity.class;
        }


        Class finalClasses = classes;
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, finalClasses));
            finish();
        }, SPLASH_TIME);
    }
}
