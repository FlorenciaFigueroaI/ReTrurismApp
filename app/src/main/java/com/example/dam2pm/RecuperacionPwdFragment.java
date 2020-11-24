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
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class RecuperacionPwdFragment extends Fragment {

    int contador =0;
    String emailRecuperacion;
    EditText txtEmailRecup;
    Button btnCancelarEmailRecup;
    Button btnEnviarEmail;
    ProgressBar progressBar;
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
        progressBar = view.findViewById(R.id.prgrssBarEnvioEmail);

        btnEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar();
            }
        });

        btnCancelarEmailRecup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AccesoActivity.class)); // volvemos a la página de acceso
                Toast.makeText(getActivity(), "Tarea cancelada", Toast.LENGTH_SHORT).show();
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

        enviarEmail(email);
    }

    // Método que envía el email a firebase y éste enviará un email con instrucciones al usuario para recuperar la contraseña
    private void enviarEmail(String email) {
        emailRecuperacion = email;
        barraProgreso();
        mAuth.sendPasswordResetEmail(emailRecuperacion) // firebase envía el email
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Email enviado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), AccesoActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getActivity(), "Email inválido", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}