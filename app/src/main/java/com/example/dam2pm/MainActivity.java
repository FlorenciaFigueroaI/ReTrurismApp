package com.example.dam2pm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toolbar;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private int ACTIVITY_PRINCIPAL = 1;
    private Button btnEnt;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar();
        Objects.requireNonNull(getActionBar()).setTitle("Ayer & Hoy");

    }

    private void setSupportActionBar() {

    }

}