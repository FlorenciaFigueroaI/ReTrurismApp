package com.example.dam2pm;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;


public class RegistroFragment extends Fragment {

    int contador = 0; // contador
    FirebaseAuth mAuth;

    Button btnEnviar;
    Button btnCancelar;

    EditText txtEmailUsuario, txtPwd, txtNombre, txtApellido;
    ProgressBar progressBar;

    String email;
    String password;
    String nombre;
    String apellido;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        //parámetros nuevos para el view del fragmento
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(2000, LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        mAuth = FirebaseAuth.getInstance(); // instanciamos mAuth

        txtEmailUsuario = view.findViewById(R.id.txtEmailUsuario);
        txtPwd = view.findViewById(R.id.txtPwdLogin);
        txtNombre = view.findViewById(R.id.txtNombre);
        txtApellido = view.findViewById(R.id.txtApellido);
        progressBar = view.findViewById(R.id.prgrssBarRegistro);

        btnEnviar = view.findViewById(R.id.btnEnviar);
        btnCancelar = view.findViewById(R.id.btnCancelar);

        btnEnviar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // llamada al método de registro
                validar();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(getActivity(), AccesoActivity.class)); // volvemos a la página de Login
                Toast.makeText(getActivity(), "No te has registrado", Toast.LENGTH_SHORT).show();

            }
        });


        return view;

    }

    // Método para el comportamiento de la barra del progreso
    private void barraProgreso() {
        // Progressbar con el objeto Timer
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                contador++;
                progressBar.setProgress(contador);
                if(contador==100)
                    t.cancel();
            }
        };
        t.schedule(tt, 0,100);
    }

    // Comprueba que el edittext de email esté rellenado y con formato correcto

        private void validar() {
            nombre = txtNombre.getText().toString();
            email = txtEmailUsuario.getText().toString();
            password = txtPwd.getText().toString();

            if (TextUtils.isEmpty(nombre)) {
                txtNombre.setError("Campo requerido.");
                return;
            }

            if (TextUtils.isEmpty(email)) {
                txtEmailUsuario.setError("Campo requerido.");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                txtPwd.setError("Campo requerido.");
                return;
            }

            // Restricciones
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    txtEmailUsuario.setError("Escriba un email válido.");
                    return;
            }

             if (password.length() < 6) {
                    txtPwd.setError("La contraseña debe tener más de 6 caracteres.");
                    return;
             }
            registrarUsuario();
    }



    // Método cargar datos de registro de los usuarios
    private void registrarUsuario() {

        email = txtEmailUsuario.getText().toString();
        password = txtPwd.getText().toString();
        nombre = txtNombre.getText().toString();
        apellido = txtApellido.getText().toString();
        barraProgreso();
        mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                               Usuario usuario = new Usuario(email, password, nombre, apellido);

                               FirebaseDatabase.getInstance().getReference("Usuario")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            barraProgreso();
                                            // mensaje de registro correcto
                                            Toast.makeText(getActivity(), "Se ha registrado con éxito", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getActivity(), AccesoActivity.class));

                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getActivity(), "Ha habido un problema.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

    }

}


