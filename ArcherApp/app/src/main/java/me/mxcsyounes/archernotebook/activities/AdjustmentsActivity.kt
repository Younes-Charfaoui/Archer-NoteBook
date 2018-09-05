package me.mxcsyounes.archernotebook.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_adjustments.*
import kotlinx.android.synthetic.main.content_adjustments.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.adapters.AdjustmentsAdapter
import me.mxcsyounes.archernotebook.database.entities.Adjustment
import me.mxcsyounes.archernotebook.viewmodels.AdjustmentViewModel

class AdjustmentsActivity : AppCompatActivity(), AdjustmentsAdapter.AdjustmentAdapterListener {


    companion object {
        const val KEY_DATA = "data"
        const val KEY_ADJUSTMENT = "key_Data"
        const val REQUEST_CODE_ADD_ADJUSTMENT = 1244
        const val REQUEST_CODE_DELETE = 1117
        @Suppress("unused")
        const val TAG = "AdjActivity"
    }

    private var mViewModel: AdjustmentViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adjustments)
        setSupportActionBar(adjustments_toolbar)

        MobileAds.initialize(this, resources.getString(R.string.app_id_ad_test))

        ad_view_adjustment.loadAd(AdRequest.Builder().build())

        ad_view_adjustment.adListener = object : AdListener() {
            override fun onAdLoaded() {
                ad_view_adjustment.visibility = View.VISIBLE
            }
        }

        add_adjust_fab.hide()


        add_adjust_fab.setOnClickListener{
            startActivityForResult(Intent(this, AddAdjustmentActivity::class.java), REQUEST_CODE_ADD_ADJUSTMENT)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = AdjustmentsAdapter(this, this)

        adjustment_recycler_view.adapter = adapter
        adjustment_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adjustment_recycler_view.setHasFixedSize(false)

        mViewModel = ViewModelProviders.of(this).get(AdjustmentViewModel::class.java)

        mViewModel?.mAllAdjustment?.observe(this, Observer<MutableList<Adjustment>> { list ->
            adjust_progress_bar.visibility = View.GONE
            add_adjust_fab.show()
            adjustment_recycler_view.visibility = View.VISIBLE
            if (list != null && list.size > 0) {
                adjust_empty_view.visibility = View.GONE
                adjustment_recycler_view.visibility = View.VISIBLE
                adapter.setAdjustmentsList(list)
            } else {
                adapter.setAdjustmentsList(null)
                adjust_empty_view.visibility = View.VISIBLE
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.adjustment_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.adjustment_delete_all_item -> {
            AlertDialog.Builder(this)
                    .setTitle("Delete All?")
                    .setMessage("The data cannot be retrieved any more.")
                    .setPositiveButton("Delete") { _, _ ->
                        mViewModel?.deleteAllAdjustments()
                    }.setNegativeButton("Cancel", null)
                    .show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_ADD_ADJUSTMENT && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                mViewModel?.insertAdjustment(data.getParcelableExtra(KEY_DATA))
            }
        }

        if (requestCode == REQUEST_CODE_DELETE && resultCode == Activity.RESULT_OK)
            if (data != null) {
                mViewModel?.deleteAdjustment(data.getParcelableExtra(KEY_DATA))
            }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onAdjustmentClicked(adjustment: Adjustment?) {
        val intent = Intent(this, AdjustmentDetailActivity::class.java)
        intent.putExtra(KEY_ADJUSTMENT, adjustment)
        startActivityForResult(intent, REQUEST_CODE_DELETE)
    }
}
