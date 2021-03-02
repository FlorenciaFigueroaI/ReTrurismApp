package com.example.dam2pm.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class ColaboracionFragment extends Fragment {

    ImageView imgVwFotografia;
    FloatingActionButton fltActBtn;
    Button btnEnviarFoto;
    TextView txtVwEjemplo;
    EditText txtTitulo;
    EditText txtDescripcion;
    EditText txtCiudad;
    EditText txtAnyo;

    RequestQueue requestQueue;

    private static final String URL = "http://192.168.1.38/retrurism/save.php";

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

                int id = v.getId();

                if (id == R.id.btnEnviarFoto){
                    String titulo = txtTitulo.getText().toString().trim();
                    String descripcion = txtDescripcion.getText().toString().trim();
                    String ciudad = txtCiudad.getText().toString().trim();
                    int anyo = Integer.parseInt(txtAnyo.getText().toString().trim());

                  //  String ruta = imgVwFotografia.getDrawable(R.id.imgVwEjemplo).toString();

                    crearFotografia(titulo, descripcion, ciudad, anyo);

                }

            }
        });

        fltActBtn = view.findViewById(R.id.fltActBtn);
        fltActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();

            }
        });

        return view;

    }


    private void crearFotografia(final String titulo, final String descripcion, final String ciudad, final int anyo) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "Correcto", Toast.LENGTH_SHORT).show();
                      //  cargarGifSonido();
                        startActivity(new Intent(getActivity(), MainActivity.class));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("titulo", titulo);
                params.put("descripcion", descripcion);
                params.put("ciudad", ciudad);
                params.put("anyo", String.valueOf(anyo));

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    // método para cargar la imagen
    @SuppressLint("IntentReset")
    private void cargarImagen() {
        @SuppressLint("IntentReset") Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent, "Selecciona la aplicación"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode== RESULT_OK){
            Uri path=data.getData();
            imgVwFotografia.setImageURI(path);
            fltActBtn.setVisibility(View.GONE);
            txtVwEjemplo.setVisibility(View.GONE);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}