package com.example.dam2pm.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam2pm.R;
import com.example.dam2pm.animaciones.GifLoadingActivity;
import com.example.dam2pm.bd.DataBaseHelper;
import com.example.dam2pm.modelos.Fotografia;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colaboracion, container, false);

        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        txtCiudad = view.findViewById(R.id.txtCiudad);
        txtAnyo = view.findViewById(R.id.txtAnyo);
        txtVwEjemplo = view.findViewById(R.id.txtEjemplo);
        imgVwFotografia = view.findViewById(R.id.imgVwEjemplo);
        btnEnviarFoto = view.findViewById(R.id.btnEnviarFoto);
        /*
        btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fotografia fotografia;

                    try {
                        fotografia = new Fotografia(-1, txtTitulo.getText().toString(), txtCiudad.getText().toString(), txtDescripcion.getText().toString(), Integer.parseInt(txtAnyo.getText().toString()), imgVwFotografia.getDrawable().toString());

                    } catch (Exception e) {
                        startActivity(new Intent(getActivity(), GifLoadingActivity.class));
                        Toast.makeText(getActivity(), "No se ha podido agregar la fotografía",  Toast.LENGTH_SHORT).show();
                        fotografia = new Fotografia(-1, "error", "error", "error",0, "error");

                    }
                    startActivity(new Intent(getActivity(), GifLoadingActivity.class));
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                    boolean exito = dataBaseHelper.insertarFoto(fotografia);
                    Toast.makeText(getActivity(), "Se ha agregado correctamente" + exito, Toast.LENGTH_SHORT).show();



            }
        });

         */

        fltActBtn = view.findViewById(R.id.fltActBtn);
        fltActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();

            }
        });


        return view;

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
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== RESULT_OK){
            Uri path=data.getData();
            imgVwFotografia.setImageURI(path);
            fltActBtn.setVisibility(View.GONE);
            txtVwEjemplo.setVisibility(View.GONE);

        }
    }
}