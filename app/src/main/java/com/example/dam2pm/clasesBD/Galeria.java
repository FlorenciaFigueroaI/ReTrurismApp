package com.example.dam2pm.clasesBD;

public class Galeria {

    String titulo;
    String ciudad;
    int anyo;
    int imagenId;


    public Galeria() {
    }

    public Galeria(String titulo, String ciudad, int anyo, int imagenId) {

        this.titulo = titulo;
        this.ciudad = ciudad;
        this.anyo = anyo;
        this.imagenId = imagenId;

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

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

/*
    @Override
    public String toString() {
        return "Galeria{" + "galeriaId=" + galeriaId + ", titulo=" + titulo + ", descripcion=" + descripcion + ", ciudad=" + ciudad + ", anyo=" + anyo + "}";
    }



 */


}





