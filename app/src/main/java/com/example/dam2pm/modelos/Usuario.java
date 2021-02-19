package com.example.dam2pm.modelos;

public class Usuario {

    public String email, password, nombre, apellido;

    // Constructores
    public Usuario(){

    }


    public Usuario (String email, String password, String nombre, String apellido){
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
        return "Usuario{" + ", email=" + email + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }





}