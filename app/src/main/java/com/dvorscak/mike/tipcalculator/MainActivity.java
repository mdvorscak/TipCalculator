package com.dvorscak.mike.tipcalculator;

import android.app.Activity;
import android.content.Intent;
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

import com.dvorscak.mike.preference.SettingsActivity;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private EditText mCostField;
    private EditText mTipField;
    private String mServiceLevelSpinnerText;
    private Tip mTip;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        mServiceLevelSpinnerText = parent.getItemAtPosition(pos).toString();
        setTip();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTip = new Tip(this);

        Log.d(TAG, getString(R.string.test));

        mTipField = (EditText) findViewById(R.id.tipEditText);

        //Cost field
        mCostField = (EditText) findViewById(R.id.costEditText);
        mCostField.setRawInputType(Configuration.KEYBOARD_12KEY);


        //Set all the dropdown stuff
        Spinner levelOfService = (Spinner) findViewById(R.id.serviceLevelSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.service_level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        levelOfService.setAdapter(adapter);

        //set the default level
        int normalServicePos = adapter.getPosition("Normal");
        levelOfService.setSelection(normalServicePos);

        levelOfService.setOnItemSelectedListener(this);

        mCostField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i2, int i3) {
                String textStr = text.toString();
                if (!textStr.equals("")) {
                    mCostField.removeTextChangedListener(this);
                    Currency.setCurrencyField(mCostField, textStr, true);
                    mCostField.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setTip();
            }
        });
    }

    private void setTip() {
        Log.d(TAG, "setting tip");
        double cost = Currency.toDouble(mCostField.getText().toString());
        double tipAmount = mTip.getTipPercentage(mServiceLevelSpinnerText) * cost;
        Currency.setCurrencyField(mTipField, tipAmount, false);
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
        if (id == R.id.settings_action) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
