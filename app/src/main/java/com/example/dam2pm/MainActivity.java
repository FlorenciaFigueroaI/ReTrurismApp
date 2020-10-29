package com.example.dam2pm;

import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.LinearLayout;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
  //  LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar = findViewById(R.id.tlBarBuscador);
        setSupportActionBar();
    }

    private void setSupportActionBar() {
    }

/*
    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nvCuenta:
                //  cuenta();

                return true;
            case R.id.nvgFotos:
                //  listadoFotos();
                return true;

            case R.id.nvgMapa:
                //    mapa();
                return true;

            case R.id.nvgAjustes:
                View itemAjustes = findViewById(R.id.nvgAjustes);
                itemAjustes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearLayout=findViewById(R.id.contMain);
                        linearLayout.setVisibility(View.GONE);
                        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
                        AjustesFragment frgAjustes = new AjustesFragment();
                        transaccion.add(R.id.contAjustes, frgAjustes);
                        transaccion.commit();
                    }
                });

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


*/

}

