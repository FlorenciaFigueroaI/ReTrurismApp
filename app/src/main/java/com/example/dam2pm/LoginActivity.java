package com.example.dam2pm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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


    private Button btnEnt;
    private Button btnReg;
    private EditText txtEmail;
    private EditText txtPwdLg;
    private TextView txtVwOlviPwd;
    FirebaseAuth mAuth; // variable para conexión de la base de datos en Firebase
    FirebaseAuth.AuthStateListener mAuthListener;

   // FirebaseUser usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.txtEmailUsuario);
        txtPwdLg= findViewById(R.id.txtPwdLogin);
        txtVwOlviPwd = findViewById(R.id.txtVwOlvidoPwd);

        btnEnt = findViewById(R.id.btnEntrar);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

            }
        };

        btnEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   ingresarUsuario();

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
                txtPwdLg.setVisibility(View.GONE);
                txtVwOlviPwd.setVisibility(View.GONE);

                // se carga el fragment
                // Link ayuda: https://developer.android.com/training/basics/fragments/fragment-ui?hl=es
                FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction(); // creación nueva transacción
                RegistroFragment frgRegistro = new RegistroFragment(); // instancia de transacción
                transaccion.add(R.id.contenedorLogin, frgRegistro); // se añade transacción
                transaccion.addToBackStack(null); //para volver hacia atrás
                transaccion.commit(); // confirmación del cambio

            }
        });

    }
/*
    @Override
    public void onStart() {
        super.onStart();
        // Comprueba si el usuario está registrado
        mAuth.addAuthStateListener(mAuthListener);
    }

 */

    public void ingresarUsuario(){
        String email = txtEmail.getText().toString();
        String password = txtPwdLg.getText().toString();
        // verificación de datos
        if(TextUtils.isEmpty(email)){
            txtEmail.setError("Email requerido.");
        } else if(TextUtils.isEmpty(password)) {
            txtPwdLg.setError("Contraseña requerida.");
        } else if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Login con éxito", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }else{
                        Toast.makeText(LoginActivity.this, "Registro erróneo" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }


}
