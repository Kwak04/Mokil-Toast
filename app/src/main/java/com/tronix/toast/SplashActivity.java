package com.tronix.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));

        Handler hd = new Handler();
        hd.postDelayed(new SplashHandler(), 1000);
    }

    @Override
    public void onBackPressed() { }

    private class SplashHandler implements Runnable {
        @Override
        public void run() {
            final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0, R.anim.fade_out);
            SplashActivity.this.finish();
        }
    }
}
