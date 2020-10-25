package com.example.dam2pm;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ConfiguracionActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        // Toolbar
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar();
        Objects.requireNonNull(getActionBar()).setTitle("Ayer & Hoy");

        // Spinner de idiomas
        Spinner spinner = (Spinner) findViewById(R.id.spnrIdiomas);
// Creamos del array con el array spnrIdiomas y el spinner layout por defecto
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spnrIdiomas, android.R.layout.simple_spinner_item);
// Espeficamos el layout para utlizarlo cuando la lista de opciones aparece
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Ponemos el adaptador en el spinner
        spinner.setAdapter(adapter);

    }

    private void setSupportActionBar() {

    }
}