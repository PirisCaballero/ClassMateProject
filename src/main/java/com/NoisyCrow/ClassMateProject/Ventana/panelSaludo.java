package com.NoisyCrow.ClassMateProject.Ventana;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class panelSaludo extends JPanel{

    /**
     *Aitor Piris
     */
    private static final long serialVersionUID = 1L;
    private Font fuenteTitulo = new Font("Times New Roman", Font.ITALIC , 45);

    public panelSaludo (){
        setBounds(0 , 0 ,1000 , 175);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.red));
        JLabel nombre = new JLabel("Class Mate Project" , JLabel.CENTER);
        nombre.setFont(fuenteTitulo);
        nombre.setBounds(0 , 0 , 1000 , 175);
        nombre.setBorder(BorderFactory.createLineBorder(Color.green));
        add(nombre);
    }
    
}