package com.example.dam2pm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class LoginActivity extends AppCompatActivity {
    Button btnReg;
    Button btnEnt;
    EditText txtEmail;
    EditText txtPd;
    TextView txtVwOlviPwd;
    CheckBox chkBoxMantSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.txtEmailUsuario);
        txtPd = findViewById(R.id.txtPwd);
        txtVwOlviPwd = findViewById(R.id.txtVwOlvidoPwd);
        chkBoxMantSesion = findViewById(R.id.chkBoxMantenerSesion);

        btnEnt = findViewById(R.id.btnEntrar);
        btnEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        btnReg = findViewById(R.id.btnRegistro);
        btnReg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {        // Carga el fragmento de registro al clikear en el botón de registro
                btnReg.setVisibility(View.GONE);    // Desaparecen los componentes de la vista del usuario
                btnEnt.setVisibility(View.GONE);
                txtEmail.setVisibility(View.GONE);
                txtPd.setVisibility(View.GONE);
                txtVwOlviPwd.setVisibility(View.GONE);
                chkBoxMantSesion.setVisibility(View.GONE);
                // Link ayuda: https://developer.android.com/training/basics/fragments/fragment-ui?hl=es
                FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction(); // creación nueva transacción
                RegistroFragment frgRegistro = new RegistroFragment(); // instancia de transacción
                transaccion.add(R.id.contenedor, frgRegistro); // se añade transacción
                transaccion.commit(); // confirmación del cambio

            }
        });

    }

}