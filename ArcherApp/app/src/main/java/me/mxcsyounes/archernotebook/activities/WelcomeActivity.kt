package me.mxcsyounes.archernotebook.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_welcome.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.adapters.IntroPagerAdapter
import me.mxcsyounes.archernotebook.utilities.PreferencesManager

class WelcomeActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    //the needed attributes
    private var mPreferencesManager: PreferencesManager? = null
    private val mLayouts: Array<Int> = arrayOf(R.layout.welcome_slide_one
            , R.layout.welcome_slide_two, R.layout.welcome_slide_three
            , R.layout.welcome_slide_four)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPreferencesManager = PreferencesManager(this)

        if (mPreferencesManager?.isNotFirstTimeLaunched()!!) {
            launchMainScreen()
        }

        //checking if the sdk is grate than Lollipop
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        setContentView(R.layout.activity_welcome)

        //getting reference to the views


        //changing the color of the status bar to transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }

        val adapter = IntroPagerAdapter(this, mLayouts)
        welcome_view_pager.adapter = adapter
        welcome_view_pager.addOnPageChangeListener(this)

        //when the skip button is Pressed
        skip_button.setOnClickListener {
            launchMainScreen()
        }

        //when the next button is pressed
        next_button.setOnClickListener {
            val current = getItem()
            if (current < mLayouts.size) {
                welcome_view_pager.currentItem = current
            } else {
                launchMainScreen()
            }
        }
    }

    private fun launchMainScreen() {
        mPreferencesManager?.setFirstTimeLaunched()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun getItem(): Int = welcome_view_pager.currentItem + 1

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (position == mLayouts.size - 1) {
            next_button.text = getString(R.string.done)
            skip_button.visibility = View.GONE
        } else {
            next_button.text = getString(R.string.next)
            skip_button.visibility = View.VISIBLE
        }
    }

    override fun onPageSelected(position: Int) {}

    override fun onPageScrollStateChanged(state: Int) {}
}
