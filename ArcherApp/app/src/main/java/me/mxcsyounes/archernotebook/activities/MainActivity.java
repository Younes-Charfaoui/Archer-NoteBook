package me.mxcsyounes.archernotebook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import me.mxcsyounes.archernotebook.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, getResources().getString(R.string.app_id_ad_test));

        AdView mAdView = findViewById(R.id.ad_view);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        findViewById(R.id.adjust_card_view).setOnClickListener(this);
        findViewById(R.id.score_card_view).setOnClickListener(this);
        findViewById(R.id.bow_setting_card_view).setOnClickListener(this);
        findViewById(R.id.more_card_view).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_setting:
                // TODO: 16-Aug-18 adding action.
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.adjust_card_view:
                startActivity(new Intent(this, AdjustmentsActivity.class));
                break;
            case R.id.score_card_view:
                startActivity(new Intent(this, ScoresActivity.class));
                break;
            case R.id.bow_setting_card_view:
                startActivity(new Intent(this, BowSettingActivity.class));
                break;
            case R.id.more_card_view:
                Toast.makeText(this, R.string.more_toast, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
