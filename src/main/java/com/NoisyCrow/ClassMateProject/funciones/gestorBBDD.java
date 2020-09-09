package com.NoisyCrow.ClassMateProject.funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.NoisyCrow.ClassMateProject.Objetos.superUsuario;
import com.NoisyCrow.ClassMateProject.Objetos.Usuario;

public class gestorBBDD {
    private String ruta = "jdbc:sqlite:src/main/java/com/NoisyCrow/ClassMateProject/DATA/Usuario.db";
    private Connection conn;
    private lectorArchvivos lA = new lectorArchvivos();
    
    public void initialize(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(ruta);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAsignaturas() throws SQLException {
        initialize();
        ArrayList<String> asignaturas = new ArrayList<String>();
        String sql = "Select * from asignaturas";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                asignaturas.add(rs.getString(3));
            }
            conn.close();
            return asignaturas;
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            conn.close();
            return null;
        }
    }
    public boolean agregarUsuario(Usuario u) throws SQLException {
        initialize();
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
            } catch (SQLException e) {
                System.out.println(e);
                e.printStackTrace();
                conn.close();
                return false;
            }
        }else{
            conn.close();
            return false;
        }
    }
    public boolean agregarSuperUsuario(superUsuario sUser) throws SQLException{
        initialize();
        if( sUser != null ){
            try {
                String sql = "Insert into superusuario values (? , ? , ? , ? , ? , ? , ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, sUser.getTipo());
                stmt.setInt(2, sUser.getDNI());
                stmt.setString(3, sUser.getNombre());
                stmt.setString(4, sUser.getApellidos());
                stmt.setString(5, sUser.getCorreo());
                stmt.setString(6, sUser.getPassword());
                stmt.setString(7, sUser.getFechaNacimiento());
                int result = stmt.executeUpdate();
            if(result == 1){
                conn.close();
                return true;
            }else{
                conn.close();
                return false;
            }
            } catch (SQLException e) {
                System.out.println(e);
                e.printStackTrace();
                conn.close();
                return false;
            }
        }else{
            conn.close();
            return false;
        }
    }
    public boolean inicioSesion(int DNI , String password)throws SQLException{
        initialize();
        try {
            String sql = "Select count(*) from superusuario where DNI = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, DNI);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.getString(1).equals("1")){
                //TODO Cambio de estado en JSON
                superUsuario su = getSuperUsuario(DNI, password);
                lA.iniciarSesion(su);
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }finally{
            conn.close();
        }
    }

    public ArrayList<Usuario> getUsuarios() throws SQLException {
        initialize();
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        try {
            String sql = "select * from usuario";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                listaUsuarios.add(u);
            }
            conn.close();
            return listaUsuarios;
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            conn.close();
            return null;
        }
    }

    public boolean eliminarUsuario(int DNI) throws SQLException {
        initialize();
        if( DNI >= 00000000 && DNI <= 99999999){
            try {
                String sql = "Delete from usuario where DNI = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, DNI);
                int i = stmt.executeUpdate();
                System.out.println(i);
                return true;
            } catch (SQLException e) {
                System.out.println(e);
                e.printStackTrace();
                conn.close();
                return false;
            }
        }else{
            conn.close();
            return false;
        }
    }
    public superUsuario getSuperUsuario(int DNI , String password)throws SQLException{
        initialize();
        superUsuario sUser = new superUsuario();
        try {
            String sql = "Select * from superusuario where DNI = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, DNI);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            sUser.setTipo(rs.getString(1));
            sUser.setDNI(rs.getInt(2));
            sUser.setNombre(rs.getString(3));
            sUser.setApellidos(rs.getString(4));
            sUser.setCorreo(rs.getString(5));
            sUser.setPassword(rs.getString(6));
            sUser.setFechaNacimiento(rs.getString(7));
            return sUser;
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }finally{
            conn.close();
        }
    }
    
}