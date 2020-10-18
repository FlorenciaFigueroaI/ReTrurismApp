package com.example.dam2pm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class LoginActivity extends AppCompatActivity {
    Button btn;
    Button btn2;
    EditText txt;
    EditText txt2;
    TextView txtVw;
    CheckBox chkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn=(Button) findViewById(R.id.btnRegistro);
        btn2=(Button) findViewById(R.id.btnEntrar);
        txt=(EditText) findViewById(R.id.txtEmailUsuario);
        txt2=(EditText) findViewById(R.id.txtPwd);
        txtVw=(TextView) findViewById(R.id.txtVwOlvidoPwd);
        chkBox=(CheckBox) findViewById(R.id.chkBoxMantenerSesion);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {        // Carga el fragmento de registro al clikear en el botón de registro
                btn.setVisibility(View.GONE);    // Desaparecen los componentes de la vista del usuario
                btn2.setVisibility(View.GONE);
                txt.setVisibility(View.GONE);
                txt2.setVisibility(View.GONE);
                txtVw.setVisibility(View.GONE);
                chkBox.setVisibility(View.GONE);
                FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction(); // creación nueva transacción
                RegistroFragment frgRegistro = new RegistroFragment(); // instancia de transacción
                transaccion.add(R.id.contenedor, frgRegistro); // se añade transacción
                transaccion.commit(); // confirmación del cambio

            }
        });

    }



}