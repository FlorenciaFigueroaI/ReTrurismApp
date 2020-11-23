
package com.example.dam2pm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

public class AjustesFragment extends Fragment {

    SwitchCompat swtchNotificaciones;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);

        swtchNotificaciones = view.findViewById(R.id.swtchNotificaciones);

        swtchNotificaciones.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getActivity(), "Descubre nuestras últimas novedades y si somos muy pesados, podrás desactivar las notificaciones cuando quieras", Toast.LENGTH_LONG).show();

                return true;
            }
        });


        return view;
    }



}