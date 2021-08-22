package com.example.moviesapp.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.moviesapp.R;
import com.example.moviesapp.ui.main.MainActivity;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private SplashPresenter splashPresenter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
        splashPresenter = new SplashPresenter(this);

        if (splashPresenter.isNetworkAvailable())
            openMainPage();
        else
            splashPresenter.showNetworkInformationDialog();
    }

    private void openMainPage() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }, SPLASH_DISPLAY_LENGTH);
    }
}



