package com.NoisyCrow.ClassMateProject.DATA;

public class inic {
    
    public inic(String SU , String dni , String pass , String nom){
        this.superUsuario = SU;
        this.DNI = dni;
        this.password = pass;
        this.nombre = nom;
    }

    private String superUsuario;
    private String DNI;
    private String password;
    private String nombre;

    public inic(){}

    public void setDNI(String dni){
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
    public String getDNI(){
        return this.DNI;
    }
    public void setNombre(String nom){
        this.nombre = nom;
    }
    public String getNombre(){
        return this.nombre;
    }
    
}
