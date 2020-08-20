package com.NoisyCrow.ClassMateProject.funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.NoisyCrow.ClassMateProject.Objetos.Usuario;

public class gestorBBDD {
    private String ruta = "jdbc:sqlite:src/main/java/com/NoisyCrow/ClassMateProject/DATA/Usuario.db";
    private Connection conn;
    
    public void initialize(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(ruta);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public gestorBBDD(){
        initialize();
    }

    public ArrayList<String> getAsignaturas(){
        ArrayList<String> asignaturas = new ArrayList<String>();
        String sql = "Select * from asignaturas";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                asignaturas.add(rs.getString(3));
            }
            return asignaturas;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }
    public boolean agregarUsuario(Usuario u){
        if( u != null ){
            try {
                String sql = "Insert into usuario values (? , ? , ? , ? , ? , ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, u.getTipo());
                stmt.setInt(2, u.getDNI());
                stmt.setString(3, u.getNombre());
                stmt.setString(4, u.getApellidos());
                stmt.setString(5, u.getCorreo());
                stmt.setString(6, u.getFechaNacimiento());
                int result = stmt.executeUpdate();
            if(result == 1){
                conn.close();
                return true;
            }else{
                conn.close();
                return false;
            }
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }
    
}