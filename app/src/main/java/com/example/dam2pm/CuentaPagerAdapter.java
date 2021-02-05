package com.example.dam2pm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CuentaPagerAdapter extends FragmentStateAdapter {

    public CuentaPagerAdapter(@NonNull CuentaFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new DatosPersonalesFragment();
            case 1:
                return new GestionGaleriaFragment();
            case 2:
                return new AjustesFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
