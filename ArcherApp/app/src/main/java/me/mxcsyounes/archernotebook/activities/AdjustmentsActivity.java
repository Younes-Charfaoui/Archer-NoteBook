package me.mxcsyounes.archernotebook.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import me.mxcsyounes.archernotebook.DataProvider;
import me.mxcsyounes.archernotebook.R;
import me.mxcsyounes.archernotebook.adapters.AdjustmentsAdapter;
import me.mxcsyounes.archernotebook.fragments.AddAdjustmentDistanceDialog;
import me.mxcsyounes.archernotebook.fragments.AddAdjustmentPhotosDialog;
import me.mxcsyounes.archernotebook.fragments.AddAdjustmentTextsDialog;
import me.mxcsyounes.archernotebook.viewmodels.AdjustmentViewModel;

public class AdjustmentsActivity extends AppCompatActivity implements AddAdjustmentDistanceDialog.AdjustmentDistanceListener
        , AddAdjustmentTextsDialog.AdjustmentTextListener, AddAdjustmentPhotosDialog.AdjustmentPhotoListener {

    private AdjustmentViewModel mViewModel;
    private FloatingActionButton mAddAdjustFab;
    private ProgressBar mProgressBar;
    private static final String TAG = "AdjActivity";

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

        mProgressBar = findViewById(R.id.adjust_progress_bar);

        mAddAdjustFab = findViewById(R.id.add_adjust_fab);
        mAddAdjustFab.hide();

        mAddAdjustFab.setOnClickListener(v -> {
            AddAdjustmentDistanceDialog dialog = new AddAdjustmentDistanceDialog();
            dialog.show(getSupportFragmentManager(), "DistanceAdd");
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

        mViewModel = ViewModelProviders.of(this).get(AdjustmentViewModel.class);

        mViewModel.getAllNotes().observe(this, list -> {
            mProgressBar.setVisibility(View.GONE);
            mAddAdjustFab.show();
            recyclerView.setVisibility(View.VISIBLE);
            if (DataProvider.getAdjustmentList() != null && DataProvider.getAdjustmentList().size() > 0) {
                recyclerView.setVisibility(View.VISIBLE);
                adapter.setAdjustmentsList(DataProvider.getAdjustmentList());
            } else {
                findViewById(R.id.adjust_empty_view).setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onAdjustmentDistanceComplete(int distance) {
        Toast.makeText(this, "distance is " + distance, Toast.LENGTH_SHORT).show();

        AddAdjustmentTextsDialog dialog = new AddAdjustmentTextsDialog();
        dialog.show(getSupportFragmentManager(), "TextAdd");
    }

    @Override
    public void onAdjustmentTextComplete(String vertical, String horizontal, String description) {
        Toast.makeText(this, "text are " + vertical + horizontal + description, Toast.LENGTH_SHORT).show();
        AddAdjustmentPhotosDialog dialog = new AddAdjustmentPhotosDialog();
        dialog.show(getSupportFragmentManager(),"PhotoAdd");
    }

    @Override
    public void onAdjustmentPhotoComplete(String... paths) {

    }
}
