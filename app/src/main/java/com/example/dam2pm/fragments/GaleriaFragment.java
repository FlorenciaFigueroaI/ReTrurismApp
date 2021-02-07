package com.example.dam2pm.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dam2pm.R;
import com.example.dam2pm.clases.GaleriaAdapter;
import com.example.dam2pm.clasesBD.Galeria;

import java.util.ArrayList;

public class GaleriaFragment extends Fragment {

    RecyclerView recyclerFotos;
    ArrayList<Galeria> listaFotos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_galeria, container, false);


        listaFotos=new ArrayList<>();

        recyclerFotos =  view.findViewById(R.id.recyclerVwGaleria);
        recyclerFotos.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        GaleriaAdapter adapter=new GaleriaAdapter(listaFotos);
        recyclerFotos.setAdapter(adapter);

        return view;
    }

    private void llenarLista() {
        listaFotos.add(new Galeria("Chalet El Peñón", "Castro Urdiales", 1940, R.drawable.img_antigua_penyol));

    }
}

