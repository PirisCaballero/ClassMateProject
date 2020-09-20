package com.NoisyCrow.ClassMateProject.Objetos;

public class curso {

    private int codCurso;   
    private String codCarrera;
    private String nombre;
    private String anio;
    private String tipo;

    public curso(){}
    public curso(int cCurso , String cCarrera , String nom , String an , String tip ){
        this.codCurso = cCurso;
        this.codCarrera = cCarrera;
        this.nombre = nom;
        this.anio = an;
        this.tipo = tip;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(String codCarrera) {
        this.codCarrera = codCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
