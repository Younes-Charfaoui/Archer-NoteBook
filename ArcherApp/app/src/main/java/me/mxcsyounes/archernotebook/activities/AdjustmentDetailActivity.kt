package me.mxcsyounes.archernotebook.activities

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_adjustment_detail.*
import kotlinx.android.synthetic.main.content_adjustment_detail.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.database.entities.Adjustment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AdjustmentDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adjustment_detail)

        setSupportActionBar(adjustment_detail_toolbar)

        MobileAds.initialize(this, resources.getString(R.string.app_id_ad_test))

        ad_view_detail_adjustment.loadAd(AdRequest.Builder().build())

        ad_view_detail_adjustment.adListener = object : AdListener() {
            override fun onAdLoaded() {
                ad_view_detail_adjustment.visibility = View.VISIBLE
            }
        }


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adjustment: Adjustment? = intent.getParcelableExtra(AdjustmentsActivity.KEY_ADJUSTMENT)
        if (adjustment != null) {
            detail_adjust_distance.text = getDistanceLong(adjustment.distance)

            val date = SimpleDateFormat("E dd MMM", Locale.getDefault()).format(adjustment.date)
            detail_adjust_date.text = date

            if (adjustment.description == null)
                detail_adjust_description.text = getString(R.string.no_description)
            else
                detail_adjust_description.text = adjustment.description

            if (adjustment.verticalAdjustment == null)
                detail_adjust_vertical_adjs.text = getString(R.string.nan)
            else
                detail_adjust_vertical_adjs.text = adjustment.verticalAdjustment


            if (adjustment.horizontalAdjustment == null)
                detail_adjust_horizontal_adjs.text = getString(R.string.nan)
            else
                detail_adjust_horizontal_adjs.text = adjustment.horizontalAdjustment

            if (adjustment.path == null)
                detail_adjust_no_photo.visibility = View.VISIBLE
            else {
                val paths = adjustment.path!!.split(";")
                for (path in paths) {

                    val imageView = ImageView(this)

                    val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                    params.setMargins(0, 8, 0, 8)
                    params.gravity = Gravity.CENTER_HORIZONTAL
                    imageView.layoutParams = params

                    val bitmap = BitmapFactory.decodeFile(path)
                    val ratio = bitmap.height / bitmap.width


                    Picasso.get().load(File(path)).resize((600 * ratio), (500 * ratio)).into(imageView)
                    detail_linear_layout.addView(imageView)
                }
            }

        } else {
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_adjustment_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.detail_adjustment_delete -> {
                AlertDialog.Builder(this)
                        .setTitle("Delete this?")
                        .setMessage("The data cannot be retrieved any more.")
                        .setPositiveButton("Delete", { _, _ ->
                            val intent = Intent()
                            val adjustment: Adjustment = intent.getParcelableExtra(AdjustmentsActivity.KEY_ADJUSTMENT)
                            intent.putExtra(AdjustmentsActivity.KEY_DATA, adjustment)
                            setResult(Activity.RESULT_OK, intent)
                            finish()
                        })
                        .setNegativeButton("Cancel", null)
                        .show()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


    companion object {

        fun getDistanceLong(distance: Int): String = when (distance) {
            1 -> "90 meters"
            2 -> "70 meters"
            3 -> "60 meters"
            4 -> "50 meters"
            5 -> "30 meters"
            6 -> "18 meters"
            else -> ""
        }
    }
}
