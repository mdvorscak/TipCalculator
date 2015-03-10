package com.dvorscak.mike.preference;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.dvorscak.mike.tipcalculator.R;

/**
 * Created by mike on 3/9/15.
 */
public class TipSettingsFragment extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
