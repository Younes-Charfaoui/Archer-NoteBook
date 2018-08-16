package me.mxcsyounes.archernotebook.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import me.mxcsyounes.archernotebook.R;
import me.mxcsyounes.archernotebook.adapters.AdjustmentsAdapter;
import me.mxcsyounes.archernotebook.viewmodels.AdjustmentViewModel;

public class AdjustmentsActivity extends AppCompatActivity {

    private AdjustmentViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustments);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, getResources().getString(R.string.app_id_ad_test));

        AdView mAdView = findViewById(R.id.ad_view_adjustment);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        FloatingActionButton fab = findViewById(R.id.add_adjust_fab);
        fab.setOnClickListener(v -> {
            // TODO: 16-Aug-18 process to add new adjust.
        });
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.adjustment_recycler_view);
        AdjustmentsAdapter adapter = new AdjustmentsAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(false);
    }

}
