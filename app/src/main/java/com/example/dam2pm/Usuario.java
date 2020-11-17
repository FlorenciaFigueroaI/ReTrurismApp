package com.example.dam2pm;

public class Usuario {

    int codUsuario;
    String email;
    String claveAcceso;
    String nombre;
    String apellido;

    // Constructor
    public Usuario (int codUsuario, String email, String claveAcceso, String nombre, String apellido){
        this.codUsuario = codUsuario;
        this.email = email;
        this.claveAcceso = claveAcceso;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
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


}
