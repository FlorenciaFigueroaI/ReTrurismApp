package com.example.retrurism.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.retrurism.R;
import com.example.retrurism.fragments.ColaboracionFragment;
import com.example.retrurism.fragments.GaleriaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    boolean vistaEnPrincipal;
    BottomNavigationView btmNavVw;
    Button btnMasOpciones;

    Button btnPlayPause;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menú de opciones inferior
        btmNavVw = findViewById(R.id.btmNavgtView);
        addFragment(new GaleriaFragment());
        btmNavVw.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.nvgGaleria:
                        addFragment(new GaleriaFragment());
                        vistaEnPrincipal = true;
                        break;

                    case R.id.nvgColaboracion:
                        addFragment(new ColaboracionFragment());
                        vistaEnPrincipal = false;
                        break;
                }
                return true;
            }

        });

        btnMasOpciones = findViewById(R.id.btnMasopciones);
        registerForContextMenu(btnMasOpciones);

        // Reproductor de música
        btnPlayPause = findViewById(R.id.btnPlay);
        mp = MediaPlayer.create(this,R.raw.aretha_franklin_save_me);
        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.pause();
                    btnPlayPause.setBackgroundResource(R.drawable.ic_baseline_play_circle_outline_24);
                    Toast.makeText(MainActivity.this, "Canción en pausa", Toast.LENGTH_SHORT).show();
                }else{
                    mp.start();
                    Toast.makeText(MainActivity.this, "Estás escuchando: Aretha Franklin - Save me", Toast.LENGTH_SHORT).show();
                    btnPlayPause.setBackgroundResource(R.drawable.ic_baseline_pause_circle_outline_24);
                }
            }
        });

    }


    // menú contextual

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderIcon(R.drawable.ic_baseline_settings_applications_24);

        menu.add(0, v.getId(), 0, "Perfil");
        menu.add(0, v.getId(), 0, "Cerrar sesión");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getTitle() == "Perfil") {
            startActivity(new Intent(MainActivity.this, PerfilActivity.class));

        } else if (item.getTitle() == "Cerrar sesión") {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, AccesoActivity.class)); // volvemos a la página de Login
        }

        return true;
    }

    // Método transacciones para añadir y reemplazar los fragmentos
    private void addFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }


    // Método botón hacia atrás
    @Override
    public void onBackPressed() {
        if (!vistaEnPrincipal) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.btmNavgtView);
            bottomNavigationView.setSelectedItemId(R.id.nvgGaleria);
        } else {
            moveTaskToBack(true);  // sale de la app
        }
    }



}
