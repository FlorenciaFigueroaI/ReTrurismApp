package com.example.retrurism.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrurism.R;
import com.example.retrurism.modelos.Fotografia;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.GaleriaViewHolder>{

    Context mContext;
    ArrayList<Fotografia> listaFotos;

    public GaleriaAdapter(ArrayList<Fotografia> listaFotos) {
        this.listaFotos=listaFotos;
    }

    @NotNull
    @Override
    public GaleriaViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_galeria, parent,false);
         return new GaleriaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaViewHolder holder, int position) {


        /*
        Glide.with(mContext)
                .load(fotografia.getRuta())
                .into(holder.imgVwFoto);
                */

        holder.txtTitulo.setText(listaFotos.get(position).getTitulo());
        holder.txtCiudad.setText(listaFotos.get(position).getCiudad());
        holder.txtAnyo.setText(String.valueOf(listaFotos.get(position).getAnyo()));
        holder.imgVwFoto.setImageResource(listaFotos.get(position).getRuta());


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
        ConstraintLayout mContainer;

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
