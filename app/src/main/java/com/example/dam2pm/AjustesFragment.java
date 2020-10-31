package com.example.dam2pm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class AjustesFragment extends Fragment {

 //   Spinner spinnerIdiomas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        // Spinner de idiomas
        spinnerIdiomas = spinnerIdiomas.findViewById(R.id.spnrIdiomas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spnrIdiomas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdiomas.setAdapter(adapter);
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ajustes, container, false);
    }

}