package com.example.dam2pm;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//import java.sql.DatabaseMetaData;
//import java.util.HashMap;
//import java.util.Map;


public class RegistroFragment extends Fragment {

    FirebaseAuth mAuth; // variable para conexión de la base de datos en Firebase
    FirebaseAuth.AuthStateListener mAuthListener; // escucha para verificar si son correctos los datos

    EditText txtEmailUsuario, txtPwd;
   // String nombre = "";
  //  String apellido = "";
  //  String email = "";
  //  String password = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
// Web usada de ayuda: http://www.hermosaprogramacion.com/2014/09/android-aplicaciones-fragmento/ Autor: James Revelo

        // parámetros nuevos para el view del fragmento
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(2000, LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        mAuth = FirebaseAuth.getInstance(); // instanciamos mAuth

        txtEmailUsuario = view.findViewById(R.id.txtEmailUsuario);
        txtPwd = view.findViewById(R.id.txtPwdLogin);

        Button btnEnviar = view.findViewById(R.id.btnEnviar);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    // si la verificacion de los datos es correcta nos llevará a la página de login
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        };

        // Verificamos los datos
        btnEnviar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                registrarUsuario(); // cargar los datos de login
            }
        });

        Button btnCancelar = view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(getActivity(), LoginActivity.class)); // volvemos a la página de Login
                Toast.makeText(getActivity(), "No te has registrado", Toast.LENGTH_SHORT).show();

            }
        });

        return view;

    }


    // Método cargar datos de registro de los usuarios
    private void registrarUsuario() {

        String email = txtEmailUsuario.getText().toString(); // obtener email y password
        String password = txtPwd.getText().toString();

        // Si los edittext están vacíos se envían los mensajes de aviso
        if(TextUtils.isEmpty(email)){
            txtEmailUsuario.setError("Debe ingresar un email.");
            return;
        }
        if(TextUtils.isEmpty(password)){
            txtPwd.setError("Debe ingresar una contraseña.");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // mensaje de registro correcto
                            Toast.makeText(getActivity(), "Se ha registrado con éxito", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), LoginActivity.class));

                        } else {
                            // mensaje de error
                            Toast.makeText(getActivity(), "El usuario o la contraseña son incorrectos. Inténtelo de nuevo",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

}
