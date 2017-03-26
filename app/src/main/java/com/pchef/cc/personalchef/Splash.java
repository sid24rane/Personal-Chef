package com.pchef.cc.personalchef;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

public class Splash extends AppCompatActivity {
    ImageView background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        background = (ImageView) findViewById(R.id.s_img);



        Glide.with(this)
                .load(R.drawable.splash)
                .into(background);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                finish();
                startActivity(new Intent(Splash.this, Home.class));
            }
        }, 3000);
    }


}
