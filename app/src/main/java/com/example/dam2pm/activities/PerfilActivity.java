package com.example.dam2pm.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dam2pm.R;
import com.example.dam2pm.animaciones.GifLoadingActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

    private static final String URL = "http://192.168.1.38/retrurism/save.php";


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

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();

                if (id == R.id.btnGuardar){
                    String nombre = txtNombre.getText().toString().trim();
                    String apellido = txtApellido.getText().toString().trim();
                    String apodo = txtApodo.getText().toString().trim();
                 //   String avatar = imgVwAvatar.getDrawable(R.id.imgVwAvatar).toString();

                    crearUsuario(nombre, apellido, apodo);

                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarGifSonido();

            }
        });

    }

    private void crearUsuario(final String nombre, final String apellido, final String apodo) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PerfilActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PerfilActivity.this, MainActivity.class));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nombre", nombre);
                params.put("apellido", apellido);
                params.put("apodo", apodo);
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }


    // método para producir el sonido
    public void cargarGifSonido() {
        startActivity(new Intent(PerfilActivity.this, GifLoadingActivity.class));
        mp = MediaPlayer.create(PerfilActivity.this, R.raw.sonido_botones);
        mp.start();

        startActivity(new Intent(PerfilActivity.this, MainActivity.class));
    }
}