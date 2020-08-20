package com.NoisyCrow.ClassMateProject.Ventana;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

import com.NoisyCrow.ClassMateProject.Ventana.Paneles.panelAgregarUsuario;
import com.NoisyCrow.ClassMateProject.funciones.funcionesBasicas;
import com.NoisyCrow.ClassMateProject.funciones.gestorBBDD;

public class ventanaPrincipal {

    private JFrame ventana;
    private funcionesBasicas fb;
    private JMenuBar mS;
    private Font fuenteMenusSuperior;
    private Font fuenteMenuSuperior2;
    private gestorBBDD GBS = new gestorBBDD();
    private panelSaludo pS;
    private panelAgregarUsuario PAU;
    private ArrayList<JPanel> paneles = new ArrayList<JPanel>();
    private Thread ejecucionPaneles;
    private JMenuItem archivo_salir;
    private JMenuItem archivo_usuario;
    private JMenuItem archivo_usuario_agregar;
    private JMenuItem archivo_usuario_estadisticas;
    private JMenuItem archivo_usuario_verUsuarios;

    private void initialize(){
        fuenteMenusSuperior = new Font("Times New Roman ",Font.PLAIN, 20);
        fuenteMenuSuperior2 = new Font("Times New Roman ",Font.PLAIN, 15);
        ejecucionPaneles = new Thread(){
            @Override
            public void run(){
                pS = new panelSaludo(); pS.setName("panelSaludo");
                PAU = new panelAgregarUsuario(GBS); PAU.setName("panelAgregarUsuario");
                paneles.add(PAU);
                ventana.add(pS);ventana.add(PAU);

                ActionListener exit = new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                };
                archivo_salir.addActionListener(exit);
                ActionListener agregarUsuarioL = new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        setPanel("panelAgregarUsuario");
                    }
                };
                archivo_usuario_agregar.addActionListener(agregarUsuarioL);
            }
        };
        
        fb = new funcionesBasicas();
        //
        mS = new JMenuBar();
        JMenu archivo = new JMenu("Archivo");
        archivo.setFont(fuenteMenusSuperior);
        archivo_usuario = new JMenu("Opciones de usuario");
        archivo_usuario.setFont(fuenteMenuSuperior2);
        archivo_usuario_agregar = new JMenuItem("Agregar usuario");
        archivo_usuario_agregar.setFont(fuenteMenuSuperior2);
        archivo_usuario_verUsuarios = new JMenuItem("Ver usuarios");
        archivo_usuario_verUsuarios.setFont(fuenteMenuSuperior2);
        archivo_usuario_estadisticas = new JMenuItem("Ver las estadísticas del usuario");
        archivo_usuario_estadisticas.setFont(fuenteMenuSuperior2);
        archivo_usuario.add(archivo_usuario_agregar);archivo_usuario.add(archivo_usuario_verUsuarios); archivo_usuario.add(archivo_usuario_estadisticas);

        archivo_salir = new JMenuItem("Salir");
        archivo_salir.setFont(fuenteMenuSuperior2);
        archivo.add(archivo_usuario);archivo.add(archivo_salir);
        mS.add(archivo);
        //
        ventana = new JFrame();
        ventana.setTitle(fb.JSON("TITULO"));
        ventana.getContentPane().setBackground(Color.white);
        ventana.setSize(1000 , 800);
        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventana.setJMenuBar(mS);
        ventana.setLayout(null);
        ejecucionPaneles.start();
        ////
       
        }
        public void setPanel(String nomPanel){
            for(JPanel p : paneles){
                if(p.getName().equals(nomPanel)){
                    p.setVisible(true);
                }else{
                    p.setVisible(false);
                }
            }
        }
        

    public ventanaPrincipal(){
        initialize();
    }
}