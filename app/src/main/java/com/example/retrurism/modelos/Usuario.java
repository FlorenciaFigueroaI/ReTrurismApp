package com.example.retrurism.modelos;

import org.jetbrains.annotations.NotNull;

public class Usuario {

    public String email, password;

    public Usuario (String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
/*
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 */

    @NotNull
    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}