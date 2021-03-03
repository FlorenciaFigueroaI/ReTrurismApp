
package com.example.dam2pm.modelos;

public class Fotografia {

   // int fotografiaID;
    String titulo;
    String ciudad;
    String descripcion;
    int anyo;
    String image;

    public Fotografia() {}

    public Fotografia(String titulo, String descripcion, String ciudad, int anyo, String image) {

      //  this.fotografiaID = fotografiaID;
        this.titulo = titulo;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.anyo = anyo;
        this.image = image;


    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Fotografia{" +
                "titulo='" + titulo + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", anyo=" + anyo +
                ", image='" + image + '\'' +
                '}';
    }
}







