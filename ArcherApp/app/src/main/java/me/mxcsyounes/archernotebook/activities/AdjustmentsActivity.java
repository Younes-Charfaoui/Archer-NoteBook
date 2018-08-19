package me.mxcsyounes.archernotebook.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Date;

import me.mxcsyounes.archernotebook.R;
import me.mxcsyounes.archernotebook.adapters.AdjustmentsAdapter;
import me.mxcsyounes.archernotebook.database.entities.Adjustment;
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
    private Adjustment mAdjustment;


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

        mViewModel.getAllAdjustments().observe(this, list -> {
            mProgressBar.setVisibility(View.GONE);
            mAddAdjustFab.show();
            recyclerView.setVisibility(View.VISIBLE);
            if (list != null && list.size() > 0) {
                findViewById(R.id.adjust_empty_view).setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.setAdjustmentsList(list);
            } else {
                adapter.setAdjustmentsList(null);
                findViewById(R.id.adjust_empty_view).setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onAdjustmentDistanceComplete(int distance) {
        mAdjustment = new Adjustment();
        mAdjustment.distance = distance + 1;
        AddAdjustmentTextsDialog dialog = new AddAdjustmentTextsDialog();
        dialog.show(getSupportFragmentManager(), "TextAdd");
    }

    @Override
    public void onAdjustmentTextComplete(String vertical, String horizontal, String description) {
        if (vertical.trim().length() == 0)
            mAdjustment.v_adj = null;
        else
            mAdjustment.v_adj = vertical;
        if (horizontal.trim().length() == 0)
            mAdjustment.h_adj = null;
        else
            mAdjustment.h_adj = horizontal;
        if (description.trim().length() == 0)
            mAdjustment.description = null;
        else
            mAdjustment.description = description;

        AddAdjustmentPhotosDialog dialog = new AddAdjustmentPhotosDialog();
        dialog.show(getSupportFragmentManager(), "PhotoAdd");
    }

    @Override
    public void onAdjustmentPhotoComplete(String paths) {
        if (paths.trim().length() == 0)
            mAdjustment.path = null;
        else
            mAdjustment.path = paths;
        mAdjustment.date = new Date();
        mViewModel.insertAdjustment(mAdjustment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adjustment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.adjustment_delete_all_item:
                new AlertDialog.Builder(this)
                        .setTitle("Delete All?")
                        .setMessage("The data cannot be retrieved any more.")
                        .setPositiveButton("Delete", (dialogInterface, i) -> {
                            mViewModel.deleteAllAdjustments();
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
