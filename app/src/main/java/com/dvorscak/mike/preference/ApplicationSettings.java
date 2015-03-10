package com.dvorscak.mike.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mike on 1/25/15.
 */

public class ApplicationSettings{

    public static final String LOW_TIP = "LOW_TIP";
    public static final String MEDIUM_TIP = "MEDIUM_TIP";
    public static final String HIGH_TIP = "HIGH_TIP";

    private static final String DEFAULT_TIP = "15";

    SharedPreferences mSharedPreferences;
    public ApplicationSettings(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getTipPreference(String preferenceKey){
        return mSharedPreferences.getString(preferenceKey, DEFAULT_TIP);
    }

}
