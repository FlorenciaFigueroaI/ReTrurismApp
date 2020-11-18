package com.example.dam2pm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "antut";
    private FirebaseAuth mAuth; // variable para conexión de la base de datos en Firebase
    private FirebaseAuth.AuthStateListener mAuthListener; // escucha para verificar si son correctos los datos

    private Button btnEnt;
    private Button btnReg;
    private EditText txtEmail;
    private EditText txtPd;
    private TextView txtVwOlviPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance(); // instanciamos mAuth

        txtEmail = findViewById(R.id.txtEmailUsuario);
        txtPd = findViewById(R.id.txtPwd);
        txtVwOlviPwd = findViewById(R.id.txtVwOlvidoPwd);

        btnEnt = findViewById(R.id.btnEntrar);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    // si la verificacion de los datos es correcta nos llevará a la actividad principal
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                } else {
                    // si no es correcta nos mostrará el siguiente mensaje
                  // Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
                }
            }
        };
        // verifica datos
        btnEnt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loguearUsuario(); // cargar los datos de login

            }
        });


        // Botón registro que me lleva al fragment_registro
        btnReg = findViewById(R.id.btnRegistro);
        btnReg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {        // Carga el fragmento de registro al clikear en el botón de registro
                btnReg.setVisibility(View.GONE);    // Desaparecen los componentes de la vista del usuario
                btnEnt.setVisibility(View.GONE);
                txtEmail.setVisibility(View.GONE);
                txtPd.setVisibility(View.GONE);
                txtVwOlviPwd.setVisibility(View.GONE);
                // Link ayuda: https://developer.android.com/training/basics/fragments/fragment-ui?hl=es
                FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction(); // creación nueva transacción
                RegistroFragment frgRegistro = new RegistroFragment(); // instancia de transacción
                transaccion.add(R.id.contenedorLogin, frgRegistro); // se añade transacción
                transaccion.commit(); // confirmación del cambio

            }
        });

    }

    // Método que verifica si el usuario ya está logueado, si lo está no volverá a salir la página de login al volver a entrar
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    // Método cargar datos de logueo de los usuarios
    private void loguearUsuario() {

        String email = txtEmail.getText().toString(); // obtener email y password
        String password = txtPd.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success" + task.isSuccessful());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "El usuario o la contraseña son incorrectos. Inténtelo de nuevo",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}


