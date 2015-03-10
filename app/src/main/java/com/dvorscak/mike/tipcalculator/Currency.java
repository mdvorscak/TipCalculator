package com.dvorscak.mike.tipcalculator;

import android.widget.EditText;

import java.text.NumberFormat;

/**
 * Created by mike on 3/9/15.
 */
public class Currency {
    public static double toDouble(String currencyString) {
        String cleanString = currencyString.replaceAll("[$,.]", "");
        if(!cleanString.equals("")) {
            return Double.parseDouble(cleanString);
        }
        return 0.0;
    }

    public static void setCurrencyField(EditText field, double amount, boolean selectable) {
        String formatted = NumberFormat.getCurrencyInstance().format((amount / 100));

        if(amount != 0.0) {
            field.setText(formatted);
            if(selectable) {
                field.setSelection(formatted.length());
            }
        } else {
            //Show hint when we get back to zero
            field.setText("");
        }
    }

    public static void setCurrencyField(EditText field, String amount, boolean selectable) {
        setCurrencyField(field, toDouble(amount), selectable);
    }

}
