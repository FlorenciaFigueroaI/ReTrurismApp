
package com.example.retrurism.modelos;

import org.jetbrains.annotations.NotNull;

public class Fotografia {

 //   int fotoID;
    String titulo;
    String ciudad;
 //   String descripcion;
    int anyo;
    int ruta;


    public Fotografia(int ruta, String titulo, String ciudad, int anyo) {

   //     this.fotoID = fotoID;
        this.ruta = ruta;
        this.titulo = titulo;
        this.ciudad = ciudad;
      //  this.descripcion = descripcion;
        this.anyo = anyo;



    }
    public int getRuta() {
        return ruta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }



 /*   public void setImage(String image) {
        this.image = image;
    }

  */

    @Override
    public String toString() {
        return "Fotografia{" +
                "titulo='" + titulo + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", anyo=" + anyo +
                ", ruta='" + ruta + '\'' +
                '}';
    }
}







