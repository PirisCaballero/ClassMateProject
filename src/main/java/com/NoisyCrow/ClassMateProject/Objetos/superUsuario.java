package com.NoisyCrow.ClassMateProject.Objetos;


public class superUsuario extends Usuario{

    private String password;

    public superUsuario(){}
    public superUsuario(String pass){
        this.password = pass;
    }

    public void setPassword(String pass){
        this.password = pass;
    }
   
    public String getPassword(){
        return this.password;
    }
    
}
