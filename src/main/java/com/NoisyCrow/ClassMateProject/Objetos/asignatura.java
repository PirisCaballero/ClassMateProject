package com.NoisyCrow.ClassMateProject.Objetos;

public class asignatura{

    private String nombre;
    private String codCarrera;
    
    public asignatura(){}
    public asignatura(String nom , String codCar){
        this.nombre = nom;
        this.codCarrera = codCar;
    }

    public void setNombre(String nom){
        this.nombre = nom;
    }
    public void setCodCarrera(String codCar){
        this.codCarrera = codCar;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getCodCarrera(){
        return this.codCarrera;
    }
    
}
