package com.example.macpas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        intent = new Intent (this, MainActivity.class);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        }, 3000);


    }
}