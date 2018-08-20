package me.mxcsyounes.archernotebook.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import me.mxcsyounes.archernotebook.R;
import me.mxcsyounes.archernotebook.adapters.AdjustmentsAdapter;
import me.mxcsyounes.archernotebook.database.entities.Adjustment;

public class AdjustmentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustment_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, getResources().getString(R.string.app_id_ad_test));

        AdView mAdView = findViewById(R.id.ad_view_detail_adjustment);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }
        });

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Adjustment adjustment = getIntent().getParcelableExtra(AdjustmentsActivity.KEY_ADJUSTMENT);
        if (adjustment != null) {
            ((TextView) findViewById(R.id.detail_adjust_distance)).
                    setText(getDistanceLong(adjustment.distance));

            String date = new SimpleDateFormat("E dd MMM", Locale.getDefault()).format(adjustment.date);
            ((TextView) findViewById(R.id.detail_adjust_date)).
                    setText(date);

            if (adjustment.description == null)
                ((TextView) findViewById(R.id.detail_adjust_description)).
                        setText(R.string.no_description);
            else
                ((TextView) findViewById(R.id.detail_adjust_description)).
                        setText(adjustment.description);

            if (adjustment.v_adj == null)
                ((TextView) findViewById(R.id.detail_adjust_vertical_adjs)).
                        setText(R.string.nan);
            else
                ((TextView) findViewById(R.id.detail_adjust_vertical_adjs)).
                        setText(adjustment.v_adj);


            if (adjustment.h_adj == null)
                ((TextView) findViewById(R.id.detail_adjust_horizontal_adjs)).
                        setText(R.string.nan);
            else
                ((TextView) findViewById(R.id.detail_adjust_horizontal_adjs)).
                        setText(adjustment.h_adj);

            if (adjustment.path == null)
                findViewById(R.id.detail_adjust_no_photo).setVisibility(View.VISIBLE);
            else {
                LinearLayout imagesLayout = findViewById(R.id.detail_linear_layout);
                String[] paths = adjustment.path.split(";");
                for (String path : paths) {
                    ImageView imageView = new ImageView(this);

                    LinearLayout.LayoutParams params =
                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, 8, 0, 8);
                    params.gravity = Gravity.CENTER_HORIZONTAL;
                    imageView.setLayoutParams(params);

                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    float ratio = bitmap.getHeight() / bitmap.getWidth();


                    Picasso.get().load(new File(path)).resize((int) (600 * ratio), (int) (500 * ratio)).into(imageView);
                    imagesLayout.addView(imageView);
                }
            }

        } else {
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_adjustment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detail_adjustment_delete:
                new AlertDialog.Builder(this)
                        .setTitle("Delete this?")
                        .setMessage("The data cannot be retrieved any more.")
                        .setPositiveButton("Delete", (dialogInterface, i) -> {
                            Intent intent = new Intent();
                            Adjustment adjustment = getIntent().getParcelableExtra(AdjustmentsActivity.KEY_ADJUSTMENT);
                            intent.putExtra(AdjustmentsActivity.KEY_DATA, adjustment);
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String getDistanceLong(int distance) {
        switch (distance) {
            case 1:
                return "90 meters";
            case 2:
                return "70 meters";
            case 3:
                return "60 meters";
            case 4:
                return "50 meters";
            case 5:
                return "30 meters";
            case 6:
                return "18 meters";
            default:
                return "";
        }

    }
}
