package com.NoisyCrow.ClassMateProject.Ventana.Paneles;

import java.awt.Color;
import java.awt.font.*;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.NoisyCrow.ClassMateProject.Objetos.botonAtras;

public class panelAgregarCurso extends JPanelE{

    /**
     *Aitor Piris Caballero
     */
    private static final long serialVersionUID = 1L;
    private botonAtras bAtras;
    private JPanel contenedor;
    private JLabel cCursoL , cCarreraL , nombreL , anioL , tipoL;
    private JTextField cCursoT , cCarreraT , nombreT , anioT;
    private Choice tipos;
    private Thread crearElementos;
    private Font fuenteTitulo = new Font("Times New Roman", Font.PLAIN, 30);
    private Font fuenteLabels = new Font("Times New Roman", Font.PLAIN, 17);
    private Font fuenteInstrucciones = new Font("Times New Roman", Font.PLAIN, 12);

    public panelAgregarCurso() {
        setName("panelAgregarCurso");
        contenedor = new JPanel();
        contenedor.setBounds(0 , 0 , 1000 , 625);
        contenedor.setBackground(Color.white);
        contenedor.setLayout(null);
        crearElementos = new Thread(){
            @Override
            public void run(){
                bAtras = new botonAtras();
                cCursoL = new JLabel("Codigo del curso");cCursoL.setFont(fuenteLabels);
                cCursoL.setBounds(100 , 100 , 150 , 50);
                cCarreraL = new JLabel("Codigo de la carrera");cCarreraL.setFont(fuenteLabels);
                cCarreraL.setBounds(100 , 150, 150 , 50);
                nombreL = new JLabel("Nombre del curso");nombreL.setFont(fuenteLabels);
                nombreL.setBounds(100 , 200 , 150 , 50);
                anioL = new JLabel("Año del curso"); anioL.setFont(fuenteLabels);
                anioL.setBounds(100 , 250 , 150 , 50);
                tipoL = new JLabel("Tipo de curso");tipoL.setFont(fuenteLabels);
                tipoL.setBounds(100 , 300 , 150 , 50);
                tipos = new Choice();tipos.setFont(fuenteLabels);
                tipos.setBounds(300, 310, 150, 30);


                contenedor.add(bAtras);contenedor.add(cCursoL);
                contenedor.add(cCarreraL);contenedor.add(nombreL);
                contenedor.add(anioL);contenedor.add(tipos);contenedor.add(tipoL);
                add(contenedor);
            }
        };
        crearElementos.start();
        
    }
    
    
}
