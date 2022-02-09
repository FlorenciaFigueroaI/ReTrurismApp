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
import com.example.retrurism.animaciones.GifLoadingActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class RecuperacionPwdFragment extends Fragment {

    MediaPlayer mp;
    String emailRecuperacion;
    EditText txtEmailRecup;
    Button btnCancelarEmailRecup;
    Button btnEnviarEmail;
    FirebaseAuth mAuth;

    String email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recuperacion_pwd, container, false);

        //parámetros nuevos para el view del fragmento
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(2000, LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        mAuth = FirebaseAuth.getInstance();

        txtEmailRecup = view.findViewById(R.id.txtEmailRecup);
        btnEnviarEmail = view.findViewById(R.id.btnEnviarEmail);
        btnCancelarEmailRecup = view.findViewById(R.id.btnCancelarRecupEmail);

        btnEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar();

            }
        });

        btnCancelarEmailRecup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                efectoSonido();
                startActivity(new Intent(getActivity(), AccesoActivity.class)); // volvemos a la página de acceso
                Toast.makeText(getActivity(), "Tarea cancelada", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    // Comprueba que el edittext de email esté rellenado y con correcto formato
    private void validar() {
        email = txtEmailRecup.getText().toString();

        if(TextUtils.isEmpty(email)){
            txtEmailRecup.setError("Email requerido.");
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtEmailRecup.setError("Email inválido");
            return;
        }
        cargarGif();
        enviarEmail(email);
    }

    // Método que envía el email a firebase y éste enviará un email con instrucciones al usuario para recuperar la contraseña
    private void enviarEmail(String email) {
        emailRecuperacion = email;
        mAuth.sendPasswordResetEmail(emailRecuperacion) // firebase envía el email
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            efectoSonido();
                            Toast.makeText(getActivity(), "Email enviado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), AccesoActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getActivity(), "Email inválido", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void efectoSonido() {
        mp = MediaPlayer.create(getActivity(), R.raw.sonido_botones);
        mp.start();
    }

    // método para cargar el GIF
    public void cargarGif(){
        startActivity(new Intent(getActivity(), GifLoadingActivity.class));

    }





}