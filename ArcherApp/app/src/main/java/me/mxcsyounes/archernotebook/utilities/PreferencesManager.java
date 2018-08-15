package me.mxcsyounes.archernotebook.utilities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @definition: this class is responsible for creating and managing the
 * shared preferences files in the android phone.
 */

public class PreferencesManager {


    private static final String TAG = "manager";
    private static final int PRIVATE_MODE = 0;
    private static final String PREFERENCE_CONFIGURATION_NAME = "configuration";
    private static final String FIRST_TIME = "isFirstTime";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @SuppressLint("CommitPrefEdits")
    public PreferencesManager(Context mContext) {
        mPreferences = mContext.getSharedPreferences(PREFERENCE_CONFIGURATION_NAME, PRIVATE_MODE);
        mEditor = mPreferences.edit();
    }

    public boolean isNotFirstTimeLaunched() {
        return !mPreferences.getBoolean(FIRST_TIME, true);
    }

    public void setFirstTimeLaunched() {
        mEditor.putBoolean(FIRST_TIME, false);
        mEditor.commit();
    }

    void removePreference() {
        mEditor.clear().commit();
    }
}
