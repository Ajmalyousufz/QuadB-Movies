package com.ajmalyousufza.quadbmovies.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajmalyousufza.quadbmovies.R;

public class SplashScreen extends AppCompatActivity {

    ImageView splashImage;
    TextView quadblogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        getSupportActionBar().hide();
        splashImage = findViewById(R.id.splshimg);
        quadblogo = findViewById(R.id.quadblogo);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(2000);
        quadblogo.startAnimation(animation);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}