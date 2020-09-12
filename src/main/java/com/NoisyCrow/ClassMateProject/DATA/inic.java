package com.NoisyCrow.ClassMateProject.DATA;

public class inic {
    
    public inic(String superUsuario ,String password , String nombre , int dni){
        this.superUsuario = superUsuario;
        this.DNI = dni;
        this.password = password;
        this.nombre = nombre;
    }

    private String superUsuario;
    private int DNI;
    private String password;
    private String nombre;

    public inic(){}

    public void setDNI(int dni){
        this.DNI = dni;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public void setSuperUsuario(String su){
        this.superUsuario = su;
    }
    public String getSuperUsuario(){
        return this.superUsuario;
    }
    public String getPassword(){
        return this.password;
    }
    public int getDNI(){
        return this.DNI;
    }
    public void setNombre(String nom){
        this.nombre = nom;
    }
    public String getNombre(){
        return this.nombre;
    }
    
}
