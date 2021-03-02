package com.example.dam2pm.fragments;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dam2pm.R;
import com.example.dam2pm.activities.MainActivity;
import com.example.dam2pm.singleton.MySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class ColaboracionFragment extends Fragment {

    private static final int IMG_REQUEST = 1;
    ImageView imgVwFotografia;
    FloatingActionButton fltActBtn;
    Button btnEnviarFoto;
    TextView txtVwEjemplo;
    EditText txtTitulo;
    EditText txtDescripcion;
    EditText txtCiudad;
    EditText txtAnyo;

    Bitmap bitmap;


    RequestQueue requestQueue;

    private final String uploadURL = "http://192.168.1.38/retrurism/upload.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colaboracion, container, false);

       requestQueue = Volley.newRequestQueue(getContext());

        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        txtCiudad = view.findViewById(R.id.txtCiudad);
        txtAnyo = view.findViewById(R.id.txtAnyo);
        txtVwEjemplo = view.findViewById(R.id.txtEjemplo);
        imgVwFotografia = view.findViewById(R.id.imgVwEjemplo);
        btnEnviarFoto = view.findViewById(R.id.btnEnviarFoto);

        btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   int id = v.getId();

               // if (id == R.id.btnEnviarFoto){
                 //   String titulo = txtTitulo.getText().toString().trim();
                //    String descripcion = txtDescripcion.getText().toString().trim();
                 //   String ciudad = txtCiudad.getText().toString().trim();
                 //   int anyo = Integer.parseInt(txtAnyo.getText().toString().trim());

                  //  String ruta = imgVwFotografia.getDrawable(R.id.imgVwEjemplo).toString();

               //     crearFotografia(titulo, descripcion, ciudad, anyo);
                    subirFoto();

                }
        });

        fltActBtn = view.findViewById(R.id.fltActBtn);
        fltActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarImagen();

            }
        });

        return view;

    }


    private void subirFoto() {
//final String titulo, final String descripcion, final String ciudad, final int anyo
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                uploadURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String resp = jsonObject.getString("response");

                            Toast.makeText(getContext(), "Correcto", Toast.LENGTH_SHORT).show();
                            imgVwFotografia.setImageResource(0);
                            fltActBtn.setVisibility(View.GONE);
                            txtVwEjemplo.setVisibility(View.GONE);


                            //   cargarGifSonido();
                            //  startActivity(new Intent(getActivity(), MainActivity.class));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("titulo", imageToString(bitmap));
                params.put("descripcion", txtDescripcion.getText().toString().trim());
                params.put("ciudad", txtCiudad.getText().toString().trim());
                params.put("anyo", String.valueOf(Integer.parseInt(txtAnyo.getText().toString().trim())));


                return params;
            }
        };


        requestQueue.add(stringRequest);
    }



    // método para cargar la imagen
    private void seleccionarImagen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMG_REQUEST && resultCode== RESULT_OK && data!=null){

           Uri path = data.getData();
           try {
                ImageDecoder.Source source;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    source = ImageDecoder.createSource(getContext().getContentResolver(), path);
                    bitmap = ImageDecoder.decodeBitmap(source);
                    imgVwFotografia.setImageBitmap(bitmap);
                    imgVwFotografia.setVisibility(View.VISIBLE);
                    fltActBtn.setVisibility(View.GONE);
                    txtVwEjemplo.setVisibility(View.GONE);
                   

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }



    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream); // formato y calidad de la foto
        byte[] imgByte =  byteArrayOutputStream.toByteArray(); // convertimos en bytes

        return Base64.encodeToString(imgByte, Base64.DEFAULT);


    }

}