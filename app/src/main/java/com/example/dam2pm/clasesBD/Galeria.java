package com.example.dam2pm.clasesBD;

public class Galeria {
    Integer galeriaId;
    String titulo;
    String descripcion;
    String ciudad;
    int anyo;
    String ruta;
    Usuario usuarioId;

    public Galeria() {
    }

    public Galeria(Integer galeriaId, String titulo, String descripcion, String ciudad, int anyo, String ruta, Usuario usuarioId) {
        this.galeriaId = galeriaId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.anyo = anyo;
        this.ruta = ruta;
        this.usuarioId = usuarioId;
    }
/*
    public Integer getGaleriaId() {
        return galeriaId;
    }

    public void setGaleriaId(Integer galeriaId) {
        this.galeriaId = galeriaId;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "Galeria{" + "galeriaId=" + galeriaId + ", titulo=" + titulo + ", descripcion=" + descripcion + ", ciudad=" + ciudad + ", anyo=" + anyo + ", ruta=" + ruta + ", usuarioId=" + usuarioId + '}';
    }



 */

}





