package com.example.dam2pm;

import android.os.Bundle;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar = findViewById(R.id.tlBarBuscador);
        setSupportActionBar();

        // Fragmento Explore

        // Link ayuda: https://developer.android.com/training/basics/fragments/fragment-ui?hl=es
        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction(); // creación nueva transacción
        ExploreFragment frgExplore = new ExploreFragment(); // instancia de transacción
        transaccion.add(R.id.contMain, frgExplore); // se añade transacción
        transaccion.commit(); // confirmación del cambio

    }

    private void setSupportActionBar() {

    }

}