package com.example.dam2pm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    boolean vistaEnExplore;
    Button btnCerrarSesion;

    // Menú
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menú de opciones
        BottomNavigationView btmNavVw = findViewById(R.id.btmNavgtView);
        addFragment(new ExploreFragment());
        btmNavVw.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                switch (itemId) {

                    case R.id.nvExplorar:
                        addFragment(new ExploreFragment());
                        vistaEnExplore = true;
                        break;

                    case R.id.nvCuenta:
                        addFragment(new CuentaFragment());
                        vistaEnExplore = false;
                        break;

                    case R.id.nvgGaleria:
                        addFragment(new GaleriaFragment());
                        vistaEnExplore = false;
                        break;

                    case R.id.nvgMapa:
                        addFragment(new MapaFragment());
                        vistaEnExplore = false;
                        break;

                    case R.id.nvgAjustes:
                        addFragment(new AjustesFragment());
                        vistaEnExplore = false;
                        break;
                }
                return true;
            }

        });

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        // Botón cerrar sesión
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, AccesoActivity.class)); // volvemos a la página de Login

            }
        });


    }

    // Método que añade los fragmentos
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
        if (!vistaEnExplore) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.btmNavgtView);
            bottomNavigationView.setSelectedItemId(R.id.nvExplorar);
        } else {
            moveTaskToBack(true);  // sale de la app
        }
    }


}
