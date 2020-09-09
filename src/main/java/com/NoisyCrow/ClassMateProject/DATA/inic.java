package com.NoisyCrow.ClassMateProject.DATA;

public class inic {
    
    public inic(String SU , int dni , String pass){
        this.superUsuario = SU;
        this.DNI = dni;
        this.password = pass;
    }

    private String superUsuario;
    private int DNI;
    private String password;

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
    
}
