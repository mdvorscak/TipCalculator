package com.dvorscak.mike.tipcalculator;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.NumberFormat;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener{

    public static final String TAG = MainActivity.class.getSimpleName();
    private EditText mCost;
    private EditText mTip;
    private String serviceLevelSpinnerText;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        serviceLevelSpinnerText = parent.getItemAtPosition(pos).toString();
        setTip();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private double currencyStringToDouble(String currencyString) {
        String cleanString = currencyString.replaceAll("[$,.]", "");
        if(!cleanString.equals("")) {
            return Double.parseDouble(cleanString);
        }
        return 0.0;
    }

    private void setCurrencyField(EditText field, double amount, boolean selectable, String current) {
        String formatted = NumberFormat.getCurrencyInstance().format((amount / 100));

        if(amount != 0.0) {
            current = formatted;
            field.setText(formatted);
            if(selectable) {
                field.setSelection(formatted.length());
            }
        } else {
            //Show hint when we get back to zero
            current = "";
            field.setText("");
        }
    }

    private void setCurrencyField(EditText field, double amount, boolean selectable) {
        setCurrencyField(field, amount, selectable, "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "starting");

        mTip = (EditText) findViewById(R.id.tipEditText);

        //Cost field
        mCost = (EditText) findViewById(R.id.costEditText);
        mCost.setRawInputType(Configuration.KEYBOARD_12KEY);
        mCost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            private String current = "";
            @Override
            public void onTextChanged(CharSequence text, int i, int i2, int i3) {
                if(!text.toString().equals(current)){
                    mCost.removeTextChangedListener(this);
                    double parsed = currencyStringToDouble(text.toString());
                    setCurrencyField(mCost, parsed, true, current);

                    mCost.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //TODO:Run the calculation if you can
                setTip();
            }
        });

        //Set all the dropdown stuff
        Spinner levelOfService = (Spinner) findViewById(R.id.serviceLevelSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.service_level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        levelOfService.setAdapter(adapter);

        levelOfService.setOnItemSelectedListener(this);
    }

    private void setTip() {
        Log.d(TAG, "setting tip");
        double cost = currencyStringToDouble(mCost.getText().toString());
        double tipAmount = Tip.getTipPercentage(serviceLevelSpinnerText) * cost;
        setCurrencyField(mTip, tipAmount, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
