package com.example.dam2pm;

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

}