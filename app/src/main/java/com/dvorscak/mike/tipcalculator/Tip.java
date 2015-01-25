package com.dvorscak.mike.tipcalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mike on 1/24/15.
 */
public class Tip {
    public static final int LOW_TIP = 0;
    public static final int NORMAL_TIP = 1;
    public static final int HIGH_TIP = 2;

    private static final Map<String, Integer> mTipMap;
    static {
        mTipMap = new HashMap<String, Integer>();
        mTipMap.put("low", LOW_TIP);
        mTipMap.put("normal", NORMAL_TIP);
        mTipMap.put("high", HIGH_TIP);
    }

    private static double[] mTipPercentages = {
        0.1,
        0.15,
        0.2
    };

    private static int getTipIndex(String tipStr) {
        int index;
        tipStr = tipStr.toLowerCase();
        if(mTipMap.containsKey(tipStr)) {
            index = mTipMap.get(tipStr.toLowerCase());
        } else {
            // tipStr couldn't be found, default to normal
            index = NORMAL_TIP;
        }
        return index;
    }

    public static double getTipPercentage(int index){
        return mTipPercentages[index];
    }

    public static double getTipPercentage(String tipStr){
        int tipIndex = getTipIndex(tipStr);
        return getTipPercentage(tipIndex);
    }

    public static void setLowTip(double tip) {
        mTipPercentages[LOW_TIP] = tip;
    }

    public static void setNormalTip(double tip) {
        mTipPercentages[NORMAL_TIP] = tip;
    }

    public static void setHighTip(double tip) {
        mTipPercentages[HIGH_TIP] = tip;
    }

}
