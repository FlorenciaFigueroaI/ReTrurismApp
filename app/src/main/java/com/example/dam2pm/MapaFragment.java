package com.example.dam2pm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;


public class MapaFragment extends Fragment {

    View view;
    MapController mpContrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapView mapVw = view.findViewById(R.id.mapaVw);
        GeoPoint castro = new GeoPoint( -3.2167, 43.3833);
        mapVw.setBuiltInZoomControls(true);
        mpContrl = (MapController) mapVw.getController();
        mpContrl.setCenter(castro);
        mpContrl.setZoom(6);
        mapVw.setMultiTouchControls(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mapa, container, false);

    }
}