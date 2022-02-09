package com.example.retrurism.animaciones;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrurism.R;

public class GifLoadingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_loading);

        ImageView imgVwGIF = findViewById(R.id.imgVwGIF);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inicializamos animación
        Animation gifLoading = AnimationUtils.loadAnimation(this, R.anim.loading_anim);

        // Iniciamos animación

        imgVwGIF.setAnimation(gifLoading);

        // Inicializamos el objeto animador
        ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(
                imgVwGIF,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        );

        oa.setRepeatCount(ValueAnimator.INFINITE);
        oa.setRepeatCount(4);
        oa.setDuration(4000);

        oa.start();


    }





}
