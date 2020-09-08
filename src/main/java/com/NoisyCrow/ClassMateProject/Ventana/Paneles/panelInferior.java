package com.NoisyCrow.ClassMateProject.Ventana.Paneles;
import java.awt.Color;

import javax.swing.*;

public class panelInferior extends JPanel{

    /**
     *Aitor Piris Caballero
     */
    private static final long serialVersionUID = 1L;
    private JLabel layer;
    private int Xpos;
    private Thread ejecucion;
    private JPanel contenedorPrincipal;

    private void initialize(){
        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setBounds(0 , 0 , 1000 , 30);
        contenedorPrincipal.setLayout(null);
        contenedorPrincipal.setBackground(Color.black);
    }

    public panelInferior (){
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

        contenedorPrincipal.add(layer);
        add(contenedorPrincipal);
    }
    
}
