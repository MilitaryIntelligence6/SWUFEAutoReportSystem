package cn.misection.autoreport.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import cn.misection.autoreport.R;

/**
 * @author Administrator
 */
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_TIME = 500;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        splash();
    }

    private void init() {
        mHandler = new Handler(Looper.myLooper());
    }

    private void splash() {
        mHandler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }, SPLASH_DISPLAY_TIME);
    }
}