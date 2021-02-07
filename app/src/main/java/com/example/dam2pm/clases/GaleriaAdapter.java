package com.example.dam2pm.clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dam2pm.R;
import com.example.dam2pm.clasesBD.Galeria;

import java.util.ArrayList;

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.GaleriaViewHolder>{

    ArrayList<Galeria> listaFotos;

    public GaleriaAdapter(ArrayList<Galeria> listaFoto) {
        this.listaFotos=listaFoto;
    }

    @Override
    public GaleriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_galeria,null,false);
        return new GaleriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaAdapter.GaleriaViewHolder holder, int position) {
        holder.txtTitulo.setText(listaFotos.get(position).getTitulo());
        holder.txtCiudad.setText(listaFotos.get(position).getCiudad());
    //    holder.txtAnyo.setText(listaFotos.get(position).getAnyo());
        holder.txtAnyo.setText(String.valueOf(listaFotos.get(position)));
      //  holder.txtAnyo.setText(""+ listaFotos.get(position));
        holder.imgVwFoto.setImageResource(listaFotos.get(position).getImagenId());
    }


    @Override
    public int getItemCount() {
        return listaFotos.size();
    }

    public static class GaleriaViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo,txtCiudad, txtAnyo;
        ImageView imgVwFoto;

        public GaleriaViewHolder(View itemView) {
            super(itemView);
            txtTitulo= itemView.findViewById(R.id.txtTitulo);
            txtCiudad=  itemView.findViewById(R.id.txtCiudad);
            txtAnyo=  itemView.findViewById(R.id.txtAnyo);
            imgVwFoto= itemView.findViewById(R.id.idImagen);
        }
    }
}