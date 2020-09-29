package com.byethost12.carltonnoronha.diceroller.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.byethost12.carltonnoronha.diceroller.MainActivity;
import com.byethost12.carltonnoronha.diceroller.R;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        runSplashScreen();

    }

    private void runSplashScreen() {

        final int TIMEOUT = 800;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, TIMEOUT);

    }
}