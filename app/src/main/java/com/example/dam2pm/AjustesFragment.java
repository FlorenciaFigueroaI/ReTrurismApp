package com.example.dam2pm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toolbar;

public class AjustesFragment extends Fragment {

 //   Toolbar toolbar;
    Spinner spIdiomas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

     //   toolbar = toolbar.findViewById(R.id.tlBarBuscador);
       // setSupportActionBar();

        // Spinner de idiomas
     //   spIdiomas.findViewById(R.id.spnrIdiomas);
        // Creamos del array con el array spnrIdiomas y el spinner layout por defecto
       //  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spnrIdiomas, android.R.layout.simple_spinner_item);
        // Espeficamos el layout para utlizarlo cuando la lista de opciones aparece
      //  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Ponemos el adaptador en el spinner
    //    spinner.setAdapter(adapter);


    }

    private void setContentView() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajustes, container, false);
    }

}