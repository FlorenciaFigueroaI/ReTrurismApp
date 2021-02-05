package com.example.dam2pm.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dam2pm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.app.Activity.RESULT_OK;


public class ColaboracionFragment extends Fragment {

 //   private static final int RESULT_OK = ;
    ImageView imagen;
    FloatingActionButton fltActBtn;
    TextView txtVwEjemplo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colaboracion, container, false);

        txtVwEjemplo = view.findViewById(R.id.txtEjemplo);
        imagen = view.findViewById(R.id.imgVwEjemplo);
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
            imagen.setImageURI(path);
            fltActBtn.setVisibility(View.GONE);
            txtVwEjemplo.setVisibility(View.GONE);

        }
    }
}