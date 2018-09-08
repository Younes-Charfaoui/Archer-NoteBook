package me.mxcsyounes.archernotebook.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_add_adjustment.*
import kotlinx.android.synthetic.main.content_add_adjustment.*
import me.mxcsyounes.archernotebook.R
import me.mxcsyounes.archernotebook.database.entities.Adjustment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddAdjustmentActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1

        fun getDistance(id: Int): Int = when (id) {
            R.id.addAdjust90MeterRadio -> 1
            R.id.addAdjust70MeterRadio -> 2
            R.id.addAdjust60MeterRadio -> 3
            R.id.addAdjust50MeterRadio -> 4
            R.id.addAdjust30MeterRadio -> 5
            R.id.addAdjust18MeterRadio -> 6
            else -> throw IllegalStateException()
        }
    }


    private var currentId = R.id.addAdjust70MeterRadio

    private var mCurrentPhotoFiles = ""
    private var currentImage: File? = null
    private var counter = 0
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_adjustment)

        setSupportActionBar(add_adjustment_toolbar)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd?.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd?.loadAd(AdRequest.Builder().build())

        currentId = R.id.addAdjust70MeterRadio

        adjustButtonAddPhoto.setOnClickListener {
            takePhoto()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addAdjutsmentDistancesRadioGroup.setOnCheckedChangeListener{ _, id ->
            currentId = id
        }
    }

    private fun validate(): Boolean {
        val hText = horizontalAdjustInputEditText.text.toString().trim()
        val vText = vertical_adjust_input_edit_text.text.toString().trim()
        val description = descriptionAdjustInputEditText.text.toString().trim()

        if (hText.isEmpty() && vText.isEmpty() && description.isEmpty()) {
            Toast.makeText(this, "One of the adjustment should have a value.",
                    Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                Toast.makeText(this, R.string.somthing_wrong, Toast.LENGTH_SHORT).show()
            }

            if (photoFile != null) {
                val imageUri = FileProvider.getUriForFile(this,
                        "me.mxcsyounes.archernotebook.fileprovider",
                        photoFile)

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }

        } else
            Toast.makeText(this, "No app to take photo.", Toast.LENGTH_SHORT).show()

    }


    private fun createImageFile(): File? {
        val timeStamp = SimpleDateFormat("yyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = filesDir
        currentImage = File.createTempFile(imageFileName, ".jpg", storageDir)
        return currentImage
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE)
            if (resultCode == Activity.RESULT_OK) {
                counter++
                val currentPhotoText = "$counter  ${getString(R.string.photo_string)}"
                adjustPhotoStateTextView.text = currentPhotoText
                mCurrentPhotoFiles += currentImage?.absolutePath + ";"
            } else {
                if (currentImage?.exists()!!)
                    deleteFile(currentImage?.name)
            }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_adjustment_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_adjustment_done ->
                if (validate()) {
                    val adjustment = Adjustment()
                    adjustment.distance = getDistance(currentId)

                    val vertical = horizontalAdjustInputEditText.text.toString().trim()
                    val horizontal = vertical_adjust_input_edit_text.text.toString().trim()
                    val description = descriptionAdjustInputEditText.text.toString().trim()

                    if (vertical.trim().isEmpty())
                        adjustment.verticalAdjustment = null
                    else
                        adjustment.verticalAdjustment = vertical

                    if (horizontal.trim().isEmpty())
                        adjustment.horizontalAdjustment = null
                    else
                        adjustment.horizontalAdjustment = horizontal

                    if (description.trim().isEmpty())
                        adjustment.description = null
                    else
                        adjustment.description = description

                    if (mCurrentPhotoFiles.trim().isEmpty())
                        adjustment.path = null
                    else
                        adjustment.path = mCurrentPhotoFiles

                    adjustment.date = Date()

                    val intent = Intent()
                    intent.putExtra(AdjustmentsActivity.KEY_DATA, adjustment)
                    setResult(Activity.RESULT_OK, intent)
                    if (mInterstitialAd?.isLoaded!!) mInterstitialAd?.show()
                    finish()
                    return true
                }
        }
        return super.onOptionsItemSelected(item)
    }


}
