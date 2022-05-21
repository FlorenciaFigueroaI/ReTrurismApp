package com.example.retrurism.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.retrurism.R;
import com.example.retrurism.adaptadores.GaleriaAdapter;
import com.example.retrurism.modelos.Fotografia;


import java.util.ArrayList;

public class GaleriaFragment extends Fragment {

    RecyclerView recyclerFotos;
    ArrayList<Fotografia> listaFotos;

    GaleriaAdapter adapter;

//    Fotografia fotografia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_galeria, container, false);

        listaFotos=new ArrayList<>();
        recyclerFotos =  view.findViewById(R.id.recyclerVwGaleria);
        recyclerFotos.setHasFixedSize(true);
        recyclerFotos.setLayoutManager(new LinearLayoutManager(getContext()));

        agregarElementos();

        GaleriaAdapter adapter = new GaleriaAdapter(listaFotos);
        recyclerFotos.setAdapter(adapter);

        return view;
    }

    private void agregarElementos(){
// cambiar por carga de BD

        listaFotos.add(new Fotografia(R.drawable.img_antigua_penyol, "prueba","Castro Urdiales", 1940));


        adapter = new GaleriaAdapter(listaFotos);
        recyclerFotos.setAdapter(adapter);

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}

