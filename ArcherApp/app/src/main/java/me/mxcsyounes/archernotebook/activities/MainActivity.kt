package me.mxcsyounes.archernotebook.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.mxcsyounes.archernotebook.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)

        MobileAds.initialize(this, resources.getString(R.string.app_id_ad_test))

        main_ad_view.loadAd(AdRequest.Builder().build())

        adjust_card_view.setOnClickListener(this)
        score_card_view.setOnClickListener(this)
        bow_setting_card_view.setOnClickListener(this)
        more_card_view.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =

            when (item.itemId) {
                R.id.menu_item_setting -> true
            // TODO: 16-Aug-18 adding action.
                else -> super.onOptionsItemSelected(item)
            }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onClick(view: View) = when (view.id) {
                R.id.adjust_card_view ->
                    startActivity(Intent(this, AdjustmentsActivity::class.java))

                R.id.score_card_view ->
                    startActivity(Intent(this, ScoresActivity::class.java))

                R.id.bow_setting_card_view ->
                    startActivity(Intent(this, BowSettingActivity::class.java))

                R.id.more_card_view ->
                    Toast.makeText(this, R.string.more_toast, Toast.LENGTH_SHORT).show()
                else -> {
                }
            }

}
