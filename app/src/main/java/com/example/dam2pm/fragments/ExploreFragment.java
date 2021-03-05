package com.example.dam2pm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dam2pm.R;
import com.example.dam2pm.adaptadores.ExploreAdapter;
import com.example.dam2pm.modelos.Fotografia;
import com.example.dam2pm.singleton.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ExploreFragment extends Fragment {

    RecyclerView recyclerFotos;
    ArrayList<Fotografia> listaFotos;

    ExploreAdapter adapter;

    private static final String URL = "http://192.168.8.106/retrurism/fetchImages.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        recyclerFotos =  view.findViewById(R.id.recyclerVwExplore);
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
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i<5; i++) {

                                JSONObject fotosObj = array.getJSONObject(i);

                                String titulo = fotosObj.getString("titulo");
                                String ciudad = fotosObj.getString("ciudad");
                                int anyo = fotosObj.getInt("anyo");
                                String image = "http://192.168.8.106/retrurism/" + fotosObj.getString("image");
                                Fotografia fotografia = new Fotografia(titulo, ciudad, anyo, image);
                                listaFotos.add(fotografia);

                            }


                            adapter = new ExploreAdapter(getContext(), listaFotos);
                            recyclerFotos.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}