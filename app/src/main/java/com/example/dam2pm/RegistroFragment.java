package com.example.dam2pm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;


public class RegistroFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Web usada de ayuda: http://www.hermosaprogramacion.com/2014/09/android-aplicaciones-fragmento/ Autor: James Revelo
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        // parámetros nuevos para el view del fragmento
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(2000, LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        Button btnEnviar = (Button) view.findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getActivity(), "Formulario enviado", Toast.LENGTH_SHORT).show();

            }
        });

        Button btnCancelar = (Button) view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getActivity(), "No te has registrado", Toast.LENGTH_SHORT).show();

            }
        });

        return view;

    }


}