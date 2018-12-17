package com.itikkits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * * Created by Ahmed Adel Al-Desouqy on 15-Dec-18.
 */
public class SplashActivity extends Activity {

    private static final long SPLASH_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          runOnUiThread(new Runnable() {
                                              @Override
                                              public void run() {
                                                  navigateToHomeActivity();
                                                  finish();
                                              }
                                          });
                                      }
                                  },
                SPLASH_DELAY);
    }

    private void navigateToHomeActivity() {
        Intent navigationIntent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(navigationIntent);
    }
}
