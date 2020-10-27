package com.example.dam2pm;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar
        toolbar = findViewById(R.id.tlBarBuscador);
        setSupportActionBar();
        Objects.requireNonNull(getActionBar()).setTitle("Ayer & Hoy");

    }

    private void setSupportActionBar() {

    }

}