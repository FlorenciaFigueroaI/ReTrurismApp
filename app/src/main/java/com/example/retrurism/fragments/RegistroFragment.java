package com.example.retrurism.fragments;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.retrurism.R;
import com.example.retrurism.activities.AccesoActivity;
import com.example.retrurism.modelos.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegistroFragment extends Fragment {

    FirebaseAuth mAuth;
    MediaPlayer mp;
    Button btnEnviar;
    Button btnCancelar;

    EditText txtEmailUsuario, txtPwd, txtPwdConfirmn;

    String email, password, passwordConf;


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
        txtPwdConfirmn = view.findViewById(R.id.txtPwdLoginConfirmacion);

        btnEnviar = view.findViewById(R.id.btnEnviar);
        btnCancelar = view.findViewById(R.id.btnCancelar);

        btnEnviar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                 validar();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                efectoSonido();
                startActivity(new Intent(getActivity(), AccesoActivity.class)); // volvemos a la página de Login
                Toast.makeText(getActivity(), "No te has registrado", Toast.LENGTH_SHORT).show();

            }
        });
        return view;

    }

    // Comprueba que el edittext de email esté rellenado y con formato correcto

    private void validar() {
        email = txtEmailUsuario.getText().toString();
        password = txtPwd.getText().toString();
        passwordConf = txtPwdConfirmn.getText().toString();

        // Campos requeridos

        if (TextUtils.isEmpty(email)) {
            txtEmailUsuario.setError("Campo requerido.");

        }

        // requisitos password
        if (TextUtils.isEmpty(password)) {
            txtPwd.setError("Campo requerido.");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {   // Formato email
            txtEmailUsuario.setError("Escriba un email válido.");

        }else if (password.length() < 6 || password.length() > 12) {      // tamaño password
            txtPwd.setError("La contraseña debe tener entre 6 y 12 caracteres.");

        } else if(!password.equals(passwordConf)){    // Confirmación Password
            txtPwdConfirmn.setError("Las contraseñas no coinciden.");

        }
        registrarUsuario();
    }


    // Método cargar datos de registro de los usuarios
    private void registrarUsuario() {

        email = txtEmailUsuario.getText().toString();
        password = txtPwd.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Usuario usuario = new Usuario(email, password);
                            FirebaseDatabase.getInstance().getReference("Usuario")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (!task.isSuccessful()) {
                                        efectoSonido();
                                        // mensaje de registro correcto
                                        Toast.makeText(getActivity(), "Se ha registrado con éxito", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getActivity(), AccesoActivity.class));
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(getActivity(), "El email introducido ya existe, intente acceder con dicho email o vuelva a intentarlo con otro.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    // método para producir el sonido
    public void efectoSonido() {
        mp = MediaPlayer.create(getActivity(), R.raw.sonido_botones);
        mp.start();

    }
}


