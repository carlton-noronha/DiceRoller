package com.byethost12.carltonnoronha.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.byethost12.carltonnoronha.diceroller.constantsandkeys.ConstantsAndKeys;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int switchState = 0;
    int numOnDice = 0;

    ImageView ivDiceFace;
    TextView tvDiceValue;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivDiceFace = findViewById(R.id.ivDiceFace);
        tvDiceValue = findViewById(R.id.tvDiceValue);

        sharedPreferences = getSharedPreferences(ConstantsAndKeys.SHARED_PREF_KEY, MODE_PRIVATE);

        switchState = checkSwitchState();

        if(switchState == 1) {

            numOnDice = sharedPreferences.getInt(ConstantsAndKeys.DICE_FACE, 0);

            if(numOnDice == 0){
                tvDiceValue.setText("?");
            } else {
                decideDiceValues(numOnDice);
            }

        }  else {
            tvDiceValue.setText("?");
        }


        Button btnRollDice = findViewById(R.id.btnRollDice);
        ImageButton btnSettings = findViewById(R.id.btnSettings);


        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOnDice = rollDice();

                Log.i("diceroller.MainActivity", "The number on dice: " + numOnDice);

                decideDiceValues(numOnDice);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });

    }

    private int checkSwitchState() {
        return sharedPreferences.getInt(ConstantsAndKeys.SWITCH_STATE, 0);
    }

    private void decideDiceValues(int numberOnDice) {
        switch (numberOnDice) {
            case 1:
                setDiceValues(R.drawable.dice_face1, R.string.dice_value_1, numberOnDice);
                break;
            case 2:
                setDiceValues(R.drawable.dice_face2, R.string.dice_value_2, numberOnDice);
                break;
            case 3:
                setDiceValues(R.drawable.dice_face3, R.string.dice_value_3, numberOnDice);
                break;
            case 4:
                setDiceValues(R.drawable.dice_face4, R.string.dice_value_4, numberOnDice);
                break;
            case 5:
                setDiceValues(R.drawable.dice_face5, R.string.dice_value_5, numberOnDice);
                break;
            case 6:
                setDiceValues(R.drawable.dice_face6, R.string.dice_value_6, numberOnDice);
                break;
            default:
                Toast.makeText(MainActivity.this,
                        "Something went wrong! Roll Dice again", Toast.LENGTH_SHORT)
                        .show();
        }
    }

    private void setDiceValues(int imageID, int textID, int noOnDice) {
        tvDiceValue.setText(textID);
        ivDiceFace.setImageResource(imageID);
    }

    private int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(switchState == 1){
            mEditor = sharedPreferences.edit();
            mEditor.putInt(ConstantsAndKeys.DICE_FACE, numOnDice);
            mEditor.apply();
        }
    }
}