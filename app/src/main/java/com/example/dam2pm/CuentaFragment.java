package com.example.dam2pm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CuentaFragment extends Fragment {

    ViewPager2 VwPgrCuenta;
    TabLayout tbLytCuenta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);

        VwPgrCuenta = view.findViewById(R.id.VwPgrCuenta);
        VwPgrCuenta.setAdapter(new CuentaPagerAdapter(this));

        tbLytCuenta = view.findViewById(R.id.tabLytCuenta);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tbLytCuenta, VwPgrCuenta, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position) {
                    case 0: {
                        tab.setText("Datos personales");
                        tab.setIcon(R.drawable.ic_baseline_account_box_24);
                        break;
                    }
                    case 1: {
                        tab.setText("Mis fotos");
                        tab.setIcon(R.drawable.ic_baseline_photo_album_24);
                        break;
                    }
                    case 2: {
                        tab.setText("Ajustes");
                        tab.setIcon(R.drawable.ic_baseline_settings_applications_24);
                        break;
                    }
                }
            }
        }); tabLayoutMediator.attach();

        return view;
    }
}
