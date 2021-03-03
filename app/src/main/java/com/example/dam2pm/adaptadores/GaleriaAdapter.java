package com.example.dam2pm.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dam2pm.R;
import com.example.dam2pm.modelos.Fotografia;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.GaleriaViewHolder>{

    Context mContext;
    ArrayList<Fotografia> listaFotos;

    public GaleriaAdapter(Context context, ArrayList<Fotografia> listaFoto) {
        this.mContext = context;
        this.listaFotos=listaFoto;
    }


    public static class GaleriaViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo,txtCiudad, txtAnyo;
        ImageView imgVwFoto;
        LinearLayout mContainer;

        public GaleriaViewHolder(View itemView) {
            super(itemView);

            txtTitulo= itemView.findViewById(R.id.txtTitulo);
            txtCiudad=  itemView.findViewById(R.id.txtCiudad);
            txtAnyo=  itemView.findViewById(R.id.txtAnyo);
            imgVwFoto= itemView.findViewById(R.id.idImagen);
            mContainer = itemView.findViewById(R.id.contenedorFotografias);
        }
    }

    @NotNull
    @Override
    public GaleriaViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_list_galeria, parent,false);
        return new GaleriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaAdapter.GaleriaViewHolder holder, int position) {

        final Fotografia fotografia = listaFotos.get(position);

        holder.txtTitulo.setText(listaFotos.get(position).getTitulo());
        holder.txtCiudad.setText(listaFotos.get(position).getCiudad());
        holder.txtAnyo.setText(String.valueOf(listaFotos.get(position).getAnyo()));
        Glide.with(mContext).load(fotografia.getImage()).into(holder.imgVwFoto);

    }


    @Override
    public int getItemCount() {
        return listaFotos.size();
    }

}
