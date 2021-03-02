package com.example.dam2pm.activities;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.dam2pm.R;
import com.example.dam2pm.animaciones.GifLoadingActivity;
import com.example.dam2pm.modelos.Autor;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class PerfilActivity extends AppCompatActivity {
    MediaPlayer mp;
    Button btnGuardar;
    Button btnSalir;
    EditText txtNombre;
    EditText txtApellido;
    EditText txtApodo;
    ImageView imgVwAvatar;

    final int PICK_IMAGE_REQUEST = 234;
    private Uri rutaFichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

     //   requestQueue = Volley.newRequestQueue(this);

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

                  //  crearUsuario(nombre, apellido, apodo);

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
/*
    private void crearUsuario(final String nombre, final String apellido, final String apodo) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PerfilActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                     //   cargarGifSonido();
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


 */

// Opciones de fichero
   public void mostrarFicheroOpciones(){

        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Elige una imagen para subir"), PICK_IMAGE_REQUEST);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            rutaFichero = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), rutaFichero);
                imgVwAvatar.setImageBitmap(bitmap);

            }catch (IOException e){
                e.printStackTrace();

            }

        }
    }


    // método para producir el sonido
    public void cargarGifSonido() {
        startActivity(new Intent(PerfilActivity.this, GifLoadingActivity.class));
        mp = MediaPlayer.create(PerfilActivity.this, R.raw.sonido_botones);
        mp.start();

       startActivity(new Intent(PerfilActivity.this, MainActivity.class));
    }
/*
    private void mostrarDialogoOpciones(){
        final CharSequence[] opciones = {"Sacar foto","Elegir de la galería", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(PerfilActivity.this);
        builder.setTitle("Elige una opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (opciones[i].equals("Sacar foto")){
                    // llama al método para activar la cámara
                }else{
                    if (opciones[i].equals("Elegir de la galería")){
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione", 10);

                    }else{
                        dialog.dismiss();
                    }
                }
            }
        });
    }
    
 */
}