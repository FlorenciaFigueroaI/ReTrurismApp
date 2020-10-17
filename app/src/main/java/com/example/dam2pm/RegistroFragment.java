package com.example.dam2pm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {


    public RegistroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Rename and change types of parameters
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Web usada de ayuda: http://www.hermosaprogramacion.com/2014/09/android-aplicaciones-fragmento/ Autor: James Revelo
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        // parámetros nuevos para el view del fragmento
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(2000, LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

/*
        EditText txtNombre = (EditText) view.findViewById(R.id.txtNombre);
        EditText txtApellido = (EditText) view.findViewById(R.id.txtApellido);
        EditText txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        EditText txtPwd = (EditText) view.findViewById(R.id.txtPwd);
        EditText txtPwdConfirm = (EditText) view.findViewById(R.id.txtPwdConfirm);
        CheckBox chkBoxCondiciones = (CheckBox) view.findViewById(R.id.chkBoxCondiciones);
        CheckBox chkBoxBoletin = (CheckBox) view.findViewById(R.id.chkBoxBoletin);
 */
        Button btnEnviar = (Button) view.findViewById(R.id.btnEnviar);
        Button btnCancelar = (Button) view.findViewById(R.id.btnCancelar);


        btnEnviar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getActivity(), "Formulario enviado", Toast.LENGTH_SHORT).show();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getActivity(), "No te has registrado", Toast.LENGTH_SHORT).show();

            }
        });

        return view;

    }


}