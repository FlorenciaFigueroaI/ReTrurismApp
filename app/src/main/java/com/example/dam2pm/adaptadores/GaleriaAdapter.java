package com.example.dam2pm.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    @NotNull
    @Override
    public GaleriaViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_list_galeria, parent,false);
         return new GaleriaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaViewHolder holder, int position) {

        final Fotografia fotografia = listaFotos.get(position);

        Glide.with(mContext)
                .load(fotografia.getImage())
                .into(holder.imgVwFoto);
        holder.txtTitulo.setText(fotografia.getTitulo());
        holder.txtCiudad.setText(fotografia.getCiudad());
        holder.txtAnyo.setText(String.valueOf(fotografia.getAnyo()));

    }


    @Override
    public int getItemCount() {
       // return listaFotos.size();
        if (listaFotos != null)
            return listaFotos.size();
        else{
            return 0;
            }
    }


    static class GaleriaViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo,txtCiudad, txtAnyo;
        ImageView imgVwFoto;
        RelativeLayout mContainer;

        public GaleriaViewHolder(View itemView) {
            super(itemView);

            txtTitulo= itemView.findViewById(R.id.txtTitulo);
            txtCiudad=  itemView.findViewById(R.id.txtCiudad);
            txtAnyo=  itemView.findViewById(R.id.txtAnyo);
            imgVwFoto= itemView.findViewById(R.id.imgVwFoto);
            mContainer = itemView.findViewById(R.id.contenedorFotografias);
        }
    }
}
