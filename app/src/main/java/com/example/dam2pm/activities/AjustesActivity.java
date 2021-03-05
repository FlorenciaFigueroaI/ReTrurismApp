package com.example.dam2pm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.dam2pm.R;
import com.example.dam2pm.fragments.AjustesFragment;

public class AjustesActivity extends AppCompatActivity {

    FrameLayout frmLytAjustes;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerAjustes, new AjustesFragment()).commit();
        cargarAjustes();
    }

    public void cargarAjustes(){
        boolean swtTema = sharedPreferences.getBoolean("tema", false);
        if (swtTema) {
            frmLytAjustes.setBackgroundColor(Color.parseColor(String.valueOf(R.color.colorPrimaryDark)));

        } else {
            frmLytAjustes.setBackgroundColor(Color.parseColor(String.valueOf(R.color.colorBackground)));
        }
    }




}