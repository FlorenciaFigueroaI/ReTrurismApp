package com.example.dam2pm.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.dam2pm.R;
import com.example.dam2pm.animaciones.GifLoadingActivity;
import com.example.dam2pm.fragments.RecuperacionPwdFragment;
import com.example.dam2pm.fragments.RegistroFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AccesoActivity extends AppCompatActivity {


    private Button btnEnt;
    private Button btnReg;
    private EditText txtEmailAcc;
    private EditText txtPwdAcc;
    private TextView txtVwOlviPwd;
    FragmentTransaction transaccion;

    MediaPlayer mp;
    FirebaseAuth mAuth;

    String email;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        mAuth = FirebaseAuth.getInstance();


        txtEmailAcc = findViewById(R.id.txtEmailAcceso);
        txtPwdAcc= findViewById(R.id.txtPwdAcceso);
        txtVwOlviPwd = findViewById(R.id.txtVwOlvidoPwd);
        btnEnt = findViewById(R.id.btnEntrar);

        btnEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar();

            }
        });

        // Botón registro que me lleva al fragment_registro
        btnReg = findViewById(R.id.btnRegistro);
        btnReg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {        // Carga el fragmento de registro al clikear en el botón de registro
                btnReg.setVisibility(View.GONE);    // Desaparecen los componentes de la vista del usuario
                btnEnt.setVisibility(View.GONE);
                txtEmailAcc.setVisibility(View.GONE);
                txtPwdAcc.setVisibility(View.GONE);
                txtVwOlviPwd.setVisibility(View.GONE);

                efectoSonido();

                // se carga el fragment
                // Link ayuda: https://developer.android.com/training/basics/fragments/fragment-ui?hl=es
                transaccion = getSupportFragmentManager().beginTransaction(); // creación nueva transacción
                RegistroFragment frgRegistro = new RegistroFragment(); // instancia de transacción
                transaccion.add(R.id.contenedorAcceso, frgRegistro); // se añade transacción
                transaccion.addToBackStack(null); //para volver hacia atrás
                transaccion.commit(); // confirmación del cambio

            }
        });

        // Recuperacion de la contraseña
        txtVwOlviPwd = findViewById(R.id.txtVwOlvidoPwd);
        txtVwOlviPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnReg.setVisibility(View.GONE);    // Desaparecen los componentes de la vista del usuario
                btnEnt.setVisibility(View.GONE);
                txtEmailAcc.setVisibility(View.GONE);
                txtPwdAcc.setVisibility(View.GONE);
                txtVwOlviPwd.setVisibility(View.GONE);

                efectoSonido();

                // se carga el fragment
                transaccion = getSupportFragmentManager().beginTransaction(); // creación nueva transacción
                RecuperacionPwdFragment frgRecupPwd = new RecuperacionPwdFragment(); // instancia de transacción
                transaccion.add(R.id.contenedorAcceso,  frgRecupPwd); // se añade transacción
                transaccion.addToBackStack(null); //para volver hacia atrás
                transaccion.commit(); // confirmación del cambio

            }
        });

    }

    // Comprueba que el edittext de email esté rellenado y con formato correcto
    private void validar() {
        email = txtEmailAcc.getText().toString();
        password = txtPwdAcc.getText().toString();

        if (TextUtils.isEmpty(email)) {
            txtEmailAcc.setError("Campo requerido.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            txtPwdAcc.setError("Campo requerido.");
            return;
        }
        ingresarUsuario();
    }

    private void ingresarUsuario() {
        email = txtEmailAcc.getText().toString();
        password = txtPwdAcc.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    cargarGif();
                    startActivity(new Intent(AccesoActivity.this, MainActivity.class));
                 //   mostrarDialogo();

                }else{
                    Toast.makeText(AccesoActivity.this, "No se puede acceder. Verifique los datos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
/*
    public void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AccesoActivity.this);
        builder.setTitle("Antes de empezar...");
        builder.setMessage("¿Quieres crear tu usuario?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(AccesoActivity.this, PerfilActivity.class));

                    }
                })
                .setNegativeButton("Lo haré más tarde", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        cargarGif();
                        Toast.makeText(AccesoActivity.this, "¡Bienvenido/a!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AccesoActivity.this, MainActivity.class));
                        dialog.dismiss();
                    }

                }).show();
    }

 */

    // método para producir el sonido
    public void efectoSonido() {
        mp = MediaPlayer.create(AccesoActivity.this, R.raw.sonido_botones);
        mp.start();


    }

    // método para cargar el GIF
    public void cargarGif(){
        efectoSonido();
        startActivity(new Intent(AccesoActivity.this, GifLoadingActivity.class));

    }




}
