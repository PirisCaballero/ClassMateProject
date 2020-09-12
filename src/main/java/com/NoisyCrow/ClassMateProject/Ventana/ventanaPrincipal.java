package com.NoisyCrow.ClassMateProject.Ventana;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;

import com.NoisyCrow.ClassMateProject.Ventana.Paneles.panelAgregarUsuario;
import com.NoisyCrow.ClassMateProject.Ventana.Paneles.panelEliminarUsuario;
import com.NoisyCrow.ClassMateProject.Ventana.Paneles.panelInferior;
import com.NoisyCrow.ClassMateProject.Ventana.Paneles.panelPrincipal;
import com.NoisyCrow.ClassMateProject.Ventana.Paneles.panelRegistroSuperUsuario;
import com.NoisyCrow.ClassMateProject.Ventana.Paneles.panelVerUsuarios;
import com.NoisyCrow.ClassMateProject.funciones.funcionesBasicas;
import com.NoisyCrow.ClassMateProject.funciones.gestorBBDD;
import com.NoisyCrow.ClassMateProject.funciones.lectorArchvivos;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ventanaPrincipal {

    public static JFrame ventana;
    private funcionesBasicas fb;
    private lectorArchvivos lA;
    private JMenuBar mS;
    private Font fuenteMenusSuperior;
    private Font fuenteMenuSuperior2;
    private gestorBBDD GBS = new gestorBBDD();
    private panelSaludo pS;
    private panelInferior pI;
    private static panelPrincipal pP;
    private static panelAgregarUsuario PAU;
    private static panelVerUsuarios PVU;
    private static panelEliminarUsuario PEU;
    private static panelRegistroSuperUsuario pRSU;
    private static ArrayList<JPanel> paneles = new ArrayList<JPanel>();
    private Thread ejecucionPaneles;
    private JMenuItem archivo_salir;
    private JMenuItem archivo_usuario;
    private JMenuItem archivo_usuario_agregar;
    private JMenuItem archivo_usuario_eliminar;
    private JMenuItem archivo_usuario_verUsuarios;
    private JMenuItem archivo_paginaPrincipal;
    public static JMenu superUsuario;
    private JMenuItem cerrarSesion;
    private Thread ejecucionVentana, ventanaThread , repinta;
    private File inic = new File("src/main/java/com/NoisyCrow/ClassMateProject/DATA/inicioSesion.json");

    private void initialize() throws InterruptedException {
        fuenteMenusSuperior = new Font("Times New Roman ", Font.PLAIN, 20);
        fuenteMenuSuperior2 = new Font("Times New Roman ", Font.PLAIN, 15);
        lA = new lectorArchvivos();
        ventana = new JFrame();
        fb = new funcionesBasicas();
        ventanaThread = new Thread() {
            @Override
            public void run() {
                ventana.setTitle(fb.JSON("TITULO"));
                ventana.getContentPane().setBackground(Color.white);
                ventana.setSize(1000, 900);
                ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
                ventana.setJMenuBar(mS);
                ventana.setLayout(null);
                ventana.setResizable(false);
            }
        };
        ventanaThread.start();

        ejecucionPaneles = new Thread() {
            @Override
            public void run() {
                pS = new panelSaludo();
                pS.setName("panelSaludo");
                try {
                    pI = new panelInferior();
                } catch (JsonParseException e2) {
                    e2.printStackTrace();
                } catch (JsonMappingException e2) {
                    e2.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                PAU = new panelAgregarUsuario(GBS);
                PAU.setName("panelAgregarUsuario");
                PVU = new panelVerUsuarios(GBS);
                PVU.setName("panelVerUsuarios");
                pP = new panelPrincipal();
                pP.setName("panelPrincipal");
                pRSU = new panelRegistroSuperUsuario(GBS);
                pRSU.setName("panelRegistroSuperUsuario");
                paneles.add(PAU);
                paneles.add(PVU);
                paneles.add(pP);
                paneles.add(pRSU);

                ventana.add(pS);
                ventana.add(pI);

                ventana.add(PAU);
                ventana.add(PVU);
                ventana.add(pP);
                ventana.add(pRSU);

                ActionListener exit = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                };
                archivo_salir.addActionListener(exit);
                ActionListener agregarUsuarioL = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setPanel("panelAgregarUsuario");
                    }
                };
                archivo_usuario_agregar.addActionListener(agregarUsuarioL);
                ActionListener paginaPrincipal = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setPanel("panelPrincipal");
                    }
                };
                archivo_paginaPrincipal.addActionListener(paginaPrincipal);
                ActionListener verUsuariosL = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setPanel("panelVerUsuarios");
                    }
                };
                archivo_usuario_verUsuarios.addActionListener(verUsuariosL);
                ActionListener eliminarUsuarioL = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setPanel("panelPrincipal");
                        try {
                            PEU = new panelEliminarUsuario(GBS);
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                };
                //
                ActionListener cerrarSesionL = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setPanel("panelPrincipal");
                        superUsuario.setText("Super Usuario");
                        panelPrincipal.dniT.setText("");
                        panelPrincipal.passwordT.setText("");
                        try {
                            lA.cerrarSesion();
                            setPanel("panelPrincipal");
                        } catch (JsonGenerationException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (JsonMappingException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                };
                cerrarSesion.addActionListener(cerrarSesionL);

            }
        };
        repinta = new Thread(){
            @Override
            public void run(){
                while(true){
                    ventana.repaint();
                }
            }
        };
        ejecucionVentana = new Thread() {
            @Override
            public void run() {
                //
                mS = new JMenuBar();
                JMenu archivo = new JMenu("Archivo");
                archivo.setFont(fuenteMenusSuperior);
                archivo_usuario = new JMenu("Opciones de usuario");
                archivo_paginaPrincipal = new JMenuItem("Página Principal");
                archivo_paginaPrincipal.setFont(fuenteMenuSuperior2);
                
                try{
                    HashMap str = (HashMap)lA.getArchivo(inic);
                    if(str.get("superUsuario").equals("\"false\"")){
                        superUsuario = new JMenu("SuperUsuario");
                        System.out.println("+");
                    }else{
                        superUsuario = new JMenu( (String)str.get("nombre"));
                        System.out.println("-");
                    }
                }catch (IOException w ){
                    System.out.println(w);
                    w.printStackTrace();
                }

                superUsuario.setFont(fuenteMenuSuperior2);
                archivo_usuario.setFont(fuenteMenuSuperior2);
                cerrarSesion = new JMenuItem("Cerrar Sesión");
                cerrarSesion.setFont(fuenteMenuSuperior2);
                archivo_usuario_agregar = new JMenuItem("Agregar usuario");
                archivo_usuario_agregar.setFont(fuenteMenuSuperior2);
                archivo_usuario_verUsuarios = new JMenuItem("Ver usuarios");
                archivo_usuario_verUsuarios.setFont(fuenteMenuSuperior2);
                archivo_usuario_eliminar = new JMenuItem("Eliminar usuario");
                archivo_usuario_eliminar.setFont(fuenteMenuSuperior2);
                archivo_usuario.add(archivo_usuario_agregar);
                archivo_usuario.add(archivo_usuario_verUsuarios);
                archivo_usuario.add(archivo_usuario_eliminar);

                JMenu miCarrera = new JMenu("Mi Carrera");
                miCarrera.setFont(fuenteMenusSuperior);
                JMenu misAsignaturas = new JMenu("Mis asignaturas");
                misAsignaturas.setFont(fuenteMenuSuperior2);
                JMenuItem agregarAsignatura = new JMenuItem("Agregar asignatura");
                agregarAsignatura.setFont(fuenteMenuSuperior2);
                JMenuItem verAsignaturas = new JMenuItem("Ver mis asignaturas");
                verAsignaturas.setFont(fuenteMenuSuperior2);
                JMenuItem eliminarAsignatura = new JMenuItem("Eliminar asignatura");
                eliminarAsignatura.setFont(fuenteMenuSuperior2);
                misAsignaturas.add(agregarAsignatura);
                misAsignaturas.add(verAsignaturas);
                misAsignaturas.add(eliminarAsignatura);

                archivo_salir = new JMenuItem("Salir");
                archivo_salir.setFont(fuenteMenuSuperior2);
                archivo.add(archivo_paginaPrincipal);
                archivo.add(superUsuario);
                archivo.add(archivo_usuario);
                archivo.add(cerrarSesion);
                archivo.add(archivo_salir);
                miCarrera.add(misAsignaturas);
                mS.add(archivo);
                mS.add(miCarrera);
                ejecucionPaneles.start();

                }
        };
        
        //
        ejecucionVentana.start();
        repinta.start();
        ////
       //ventana.repaint();
        }
        public static void setPanel(String nomPanel){
            for(JPanel p : paneles){
                if(p.getName().equals(nomPanel)){
                    p.setVisible(true);
                }else{
                    p.setVisible(false);
                }
            }
        }
        

    public ventanaPrincipal() throws InterruptedException {
        initialize();
    }
}