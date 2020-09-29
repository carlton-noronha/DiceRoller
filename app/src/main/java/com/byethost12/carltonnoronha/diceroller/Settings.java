package com.byethost12.carltonnoronha.diceroller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import com.byethost12.carltonnoronha.diceroller.constantsandkeys.ConstantsAndKeys;

public class Settings extends AppCompatActivity {

    SwitchCompat switchSaveDiceSate;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchSaveDiceSate = findViewById(R.id.switchSaveDiceSate);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle(getString(R.string.settings));
        }

        sharedPreferences = getSharedPreferences(ConstantsAndKeys.SHARED_PREF_KEY, MODE_PRIVATE);

        int switchValue = sharedPreferences.getInt(ConstantsAndKeys.SWITCH_STATE, 0);

        if(switchValue == 1){
            switchSaveDiceSate.setChecked(true);
        }

        mEditor = sharedPreferences.edit();

        switchSaveDiceSate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.i("diceroller.Settings", "State of switch: " + isChecked);

                if(isChecked) {
                    mEditor.putInt(ConstantsAndKeys.SWITCH_STATE, 1);
                }
                else {
                    mEditor.putInt(ConstantsAndKeys.SWITCH_STATE, 0);
                }

                mEditor.putInt(ConstantsAndKeys.DICE_FACE, 0);

                mEditor.apply();
            }
        });

    }
}