package com.example.dam2pm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dam2pm.R;
import com.example.dam2pm.adaptadores.GaleriaAdapter;
import com.example.dam2pm.modelos.Fotografia;
import com.example.dam2pm.singleton.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GaleriaFragment extends Fragment {

    RecyclerView recyclerFotos;
    RecyclerView.LayoutManager manager;
    RecyclerView.Adapter mAdapter;
    ArrayList<Fotografia> listaFotos;

    private final String URL = "http://192.168.8.107/retrurism/upload.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_galeria, container, false);

        recyclerFotos =  view.findViewById(R.id.recyclerVwGaleria);
        recyclerFotos.setLayoutManager(manager);

        listaFotos=new ArrayList<>();
        getImages();

        return view;
    }
    
    private void getImages(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);

                                String titulo = object.getString("Título");
                                String descripcion = object.getString("Descripción");
                                String ciudad = object.getString("Ciudad");
                                int anyo = object.getInt("Año");
                                String image = object.getString("image");

                                Fotografia fotografia = new Fotografia(titulo, descripcion, ciudad, anyo, image);
                                listaFotos.add(fotografia);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mAdapter = new GaleriaAdapter(getActivity(), listaFotos);
                        recyclerFotos.setAdapter(mAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                });

         MySingleton.getInstance(getActivity()).addToRequestQue(stringRequest);
        
        
    }


}

