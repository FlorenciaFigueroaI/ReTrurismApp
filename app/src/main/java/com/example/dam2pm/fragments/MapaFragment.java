package com.example.dam2pm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.dam2pm.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends Fragment implements OnMapReadyCallback{

    MapView mapVw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapsInitializer.initialize(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);


        mapVw = view.findViewById(R.id.mapView);
     //   mapVw.onCreate(savedInstanceState);
     //   mapVw.getMapAsync(this); // inicializa el mapa

        mapVw = mapVw.findViewById(R.id.mapView);
        if (mapVw != null) {
            // Initialise the MapView
            mapVw.onCreate(null);
            mapVw.onResume();
            // Set the map ready callback to receive the GoogleMap object
            mapVw.getMapAsync(this);
        }

        return view;
    }




    // Método mapa
    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng chaletCastro = new LatLng(43.38447771436967, -3.2152276869961915);
        googleMap.addMarker(new MarkerOptions().position(chaletCastro)
                .title("Chalet El Peñón, Castro Urdiales"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(chaletCastro));

        // zoom
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chaletCastro, 15));

    }


}