package com.example.dam2pm.modelos;

public class Autor {
    int autorID;
    String apodo;
    String nombre;
    String apellido;

    public Autor() {}

    public Autor(int autorID, String apodo, String nombre, String apellido) {
        this.autorID = autorID;
        this.apodo = apodo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getAutorID() {
        return autorID;
    }

    public void setAutorID(int autorID) {
        this.autorID = autorID;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "autorID=" + autorID +
                ", apodo='" + apodo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}



