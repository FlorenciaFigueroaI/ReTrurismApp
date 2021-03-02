package com.example.dam2pm.animaciones;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dam2pm.R;
import com.example.dam2pm.activities.AccesoActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setTheme(R.style.SplashScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startActivity(new Intent(SplashActivity.this, AccesoActivity.class));


        /*
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        int DURATION_SPLASH = 3000; // duración de 3 segundos
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

Intent intent = new Intent(SplashActivity.this, AccesoActivity.class);
        startActivity(intent);
        finish();
            }
        }, DURATION_SPLASH);
*/
    }


}