package com.example.retrurism.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.retrurism.R;
import com.example.retrurism.animaciones.GifLoadingActivity;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PerfilActivity extends AppCompatActivity {
    MediaPlayer mp;
    Button btnGuardar;
    Button btnSalir;
    EditText txtNombre;
    EditText txtApellido;
    EditText txtApodo;
    ImageView imgVwAvatar;

    RequestQueue requestQueue;

    private static final String URL = "http://192.168.8.109/retrurism/save.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        requestQueue = Volley.newRequestQueue(this);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnSalir = findViewById(R.id.btnSalir);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtApodo = findViewById(R.id.txtApodo);
        imgVwAvatar = findViewById(R.id.imgVwAvatar);

        btnGuardar.setOnClickListener(v -> {
            int id = v.getId();

            if (id == R.id.btnGuardar){
                String nombre = txtNombre.getText().toString().trim();
                String apellido = txtApellido.getText().toString().trim();
                String apodo = txtApodo.getText().toString().trim();

                crearUsuario(nombre, apellido, apodo);
                efectoSonido();
                cargarGif();
            }
        });

        btnSalir.setOnClickListener(v -> {
            efectoSonido();
            startActivity(new Intent(PerfilActivity.this, MainActivity.class));
        });

    }

    private void crearUsuario(final String nombre, final String apellido, final String apodo) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                response -> {
                    Toast.makeText(PerfilActivity.this, "Datos guardados", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PerfilActivity.this, MainActivity.class));

                },
                error -> Toast.makeText(PerfilActivity.this, "Error. No se ha creado el usuario", Toast.LENGTH_SHORT).show()
        ){
            @NotNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nombre", nombre);
                params.put("apellido", apellido);
                params.put("apodo", apodo);
             //   params.put("ruta_avatar", ruta_avatar);
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

    // método para cargar el GIF
    public void cargarGif(){
        startActivity(new Intent(PerfilActivity.this, GifLoadingActivity.class));

    }

    // método para producir el sonido
    public void efectoSonido() {
        mp = MediaPlayer.create(PerfilActivity.this, R.raw.sonido_botones);
        mp.start();

    }

}