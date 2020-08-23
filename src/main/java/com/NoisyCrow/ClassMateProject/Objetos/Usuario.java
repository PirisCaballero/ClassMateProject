package com.NoisyCrow.ClassMateProject.Objetos;

public class Usuario {
    private String tipo;
    private int DNI;
    private String nombre;
    private String apellidos;
    private String correo;
    private String fechaNacimiento;

    public Usuario(){}
    public Usuario(String T , int dni , String N , String A , String C , String fN){
        this.tipo = T;
        this.DNI = dni;
        this.nombre = N;
        this.apellidos = A;
        this.correo = C;
        this.fechaNacimiento = fN;
    }
    public void setNombre(String Nombre){
        this.nombre = Nombre;
    }
    public void setApellidos(String Apellidos){
        this.apellidos = Apellidos;
    }
    public void setCorreo(String Correo){
        this.correo = Correo;
    }
    public void setFechaNacimiento(String fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    public void setDNI(int dni){
        this.DNI = dni;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getApellidos(){
        return this.apellidos;
    }
    public String getCorreo(){
        return this.correo;
    }
    public int getDNI(){
        return this.DNI;
    }
    public String getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    public String getTipo(){
        return this.tipo;
    } 

    public String[] toArray(){
        String [] usuario = {this.getTipo() , this.getDNI()+"" , this.getNombre() , this.getApellidos() , this.getCorreo() , this.getFechaNacimiento()};
        return usuario;
    }
}