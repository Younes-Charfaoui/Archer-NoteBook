package me.mxcsyounes.archernotebook.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bow_setting.*
import me.mxcsyounes.archernotebook.R

class BowSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bow_setting)

        setSupportActionBar(bow_setting_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
