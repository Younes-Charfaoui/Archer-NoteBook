package me.mxcsyounes.archernotebook.utilities


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * @definition: this class is responsible for creating and managing the
 * shared preferences files in the android phone.
 */

class PreferencesManager @SuppressLint("CommitPrefEdits")
constructor(mContext: Context) {

    private val mPreferences: SharedPreferences
    private val mEditor: SharedPreferences.Editor

    fun isNotFirstTimeLaunched()= !mPreferences.getBoolean(FIRST_TIME, true)


    init {
        mPreferences = mContext.getSharedPreferences(PREFERENCE_CONFIGURATION_NAME, PRIVATE_MODE)
        mEditor = mPreferences.edit()
    }

    fun setFirstTimeLaunched() {
        mEditor.putBoolean(FIRST_TIME, false)
        mEditor.commit()
    }

    fun removePreference() {
        mEditor.clear().commit()
    }

    companion object {
        private const val TAG = "manager"
        private const val PRIVATE_MODE = 0
        private const val PREFERENCE_CONFIGURATION_NAME = "configuration"
        private const val FIRST_TIME = "isFirstTime"
    }
}
