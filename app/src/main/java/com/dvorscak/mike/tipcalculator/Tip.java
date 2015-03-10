package com.dvorscak.mike.tipcalculator;

import android.content.Context;

import com.dvorscak.mike.preference.ApplicationSettings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mike on 1/24/15.
 */
public class Tip {

    ApplicationSettings mApplicationSettings;
    private static final Map<String, String> mTipMap;
    static {
        mTipMap = new HashMap<String, String>();
        mTipMap.put("low", ApplicationSettings.LOW_TIP);
        mTipMap.put("normal", ApplicationSettings.MEDIUM_TIP);
        mTipMap.put("high", ApplicationSettings.HIGH_TIP);
    }
    public Tip(Context context){
        mApplicationSettings = new ApplicationSettings(context);
    }

    private String getTipSettingName(String tipStr) {
        tipStr = tipStr.toLowerCase();
        return mTipMap.get(tipStr);
    }

    public double getTipPercentage(String tipStr){
        String tipSettingKey = getTipSettingName(tipStr);
        String tip = mApplicationSettings.getTipPreference(tipSettingKey);
        return Double.parseDouble(tip) / 100;
    }

}
