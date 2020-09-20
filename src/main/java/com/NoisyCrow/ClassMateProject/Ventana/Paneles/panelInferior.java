package com.NoisyCrow.ClassMateProject.Ventana.Paneles;

import java.awt.Color;
import java.io.IOException;

import javax.swing.*;

import com.NoisyCrow.ClassMateProject.DATA.inic;
import com.NoisyCrow.ClassMateProject.Objetos.superUsuario;
import com.NoisyCrow.ClassMateProject.Ventana.ventanaPrincipal;
import com.NoisyCrow.ClassMateProject.funciones.lectorArchvivos;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class panelInferior extends JPanel {

    /**
     * Aitor Piris Caballero
     */
    private static final long serialVersionUID = 1L;
    private JLabel layer, inicioSesion;
    private int Xpos;
    private Thread ejecucion;
    private JPanel contenedorPrincipal;
    private lectorArchvivos lA;
    private static boolean comprobar = true;
    private Thread cargarElementos;

    private void initialize() {
        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setBounds(0, 0, 1000, 30);
        contenedorPrincipal.setLayout(null);
        contenedorPrincipal.setBackground(Color.black);
        lA = new lectorArchvivos();
    }

    public panelInferior() throws JsonParseException, JsonMappingException, IOException {
        initialize();
        setName("panelInferior");
        setBounds(0 , 800 , 1000 , 30);
        setBackground(Color.pink);
        setLayout(null);
        setVisible(true);

        Xpos = 0;
        layer = new JLabel("Aitor Piris Caballero");
        layer.setBounds(Xpos , 0 , 200 , 30);
        layer.setBackground(Color.black);
        layer.setForeground(Color.white);
        layer.setHorizontalAlignment(JLabel.CENTER);layer.setVerticalAlignment(JLabel.CENTER);

        inicioSesion = new JLabel();
        inicioSesion.setBounds(600, 0, 400, 30);
        inicioSesion.setBackground(Color.black);
        inicioSesion.setForeground(Color.white);
        inicioSesion.setHorizontalAlignment(JLabel.CENTER);inicioSesion.setVerticalAlignment(JLabel.CENTER);


        contenedorPrincipal.add(layer);contenedorPrincipal.add(inicioSesion);
        add(contenedorPrincipal);
        cargarElementos = new Thread(){
            @Override
            public void run(){
                while(true){
                    while(panelInferior.comprobar){
                        try {
                            inic su =  lA.sesionIniciada();
                            if (su.getSuperUsuario().equals("\"true\"")) {
                                inicioSesion.setText("Bienvenido de nuevo: " + su.getNombre());
                                inicioSesion.setForeground(Color.green);
                            } else {
                                inicioSesion.setText("No hay super Usuario iniciado");
                                inicioSesion.setForeground(Color.red);
                            }
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                            System.err.println("No se ha podido parsear");
                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                            System.err.println("No se ha podido mapear");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("");
                }
            }
        };
        cargarElementos.start();
    }

    public static void set(boolean com){
        panelInferior.comprobar = com;
    }
    public static boolean getSet(){
        return panelInferior.comprobar;
    }
    
}
