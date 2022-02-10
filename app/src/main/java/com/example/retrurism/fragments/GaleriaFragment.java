package com.example.retrurism.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.retrurism.R;
import com.example.retrurism.adaptadores.GaleriaAdapter;
import com.example.retrurism.modelos.Fotografia;
import com.example.retrurism.singleton.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GaleriaFragment extends Fragment {

    RecyclerView recyclerFotos;
    ArrayList<Fotografia> listaFotos;

    GaleriaAdapter adapter;

    private static final String URL = "http://192.168.8.107/retrurism/fetchImages.php";
    String imageURL = "http://192.168.8.107/retrurism/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_galeria, container, false);

        recyclerFotos =  view.findViewById(R.id.recyclerVwGaleria);
        recyclerFotos.setHasFixedSize(true);
        recyclerFotos.setLayoutManager(new LinearLayoutManager(getContext()));
        listaFotos=new ArrayList<>();

        getImages();

        return view;
    }
    
    private void getImages(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL,
                response -> {
                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i<array.length(); i++) {

                            JSONObject fotosObj = array.getJSONObject(i);

                            String titulo = fotosObj.getString("titulo");
                            String ciudad = fotosObj.getString("ciudad");
                            int anyo = fotosObj.getInt("anyo");
                            String image = imageURL + fotosObj.getString("image");
                            Fotografia fotografia = new Fotografia(titulo, ciudad, anyo, image);
                            listaFotos.add(fotografia);

                        }


                        adapter = new GaleriaAdapter(getContext(), listaFotos);
                        recyclerFotos.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                },
                error -> Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show());

         MySingleton.getInstance(getActivity()).addToRequestQue(stringRequest);


        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}

