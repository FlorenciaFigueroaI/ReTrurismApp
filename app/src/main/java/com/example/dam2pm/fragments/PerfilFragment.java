package com.example.dam2pm.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dam2pm.R;
import com.google.android.material.tabs.TabLayout;


public class PerfilFragment extends Fragment {
/*
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter adapter;

 */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

/*
        tabLayout = view.findViewById(R.id.tbLyt);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Perfil));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Mis_fotos));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.ajustes));

        viewPager = view.findViewById(R.id.vwPgr);




        viewPager.setAdapter(adapter);

 */

        return view;
    }
}