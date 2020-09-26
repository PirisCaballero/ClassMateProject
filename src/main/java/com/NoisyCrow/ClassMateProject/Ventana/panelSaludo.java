package com.NoisyCrow.ClassMateProject.Ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class panelSaludo extends JPanel{

    /**
     *Aitor Piris
     */
    private static final long serialVersionUID = 1L;
    private Font fuenteTitulo = new Font("Times New Roman", Font.ITALIC , 45);
    private Font fuenteSubTitulo = new Font("Times New Roman", Font.ITALIC , 20);

    public panelSaludo (){
        setBounds(0 , 0 ,1000 , 175);
        setLayout(null);
        //setBorder(BorderFactory.createLineBorder(Color.red));
        JLabel nombre = new JLabel("ClassMateProject" , JLabel.CENTER);
        nombre.setVerticalAlignment(JLabel.BOTTOM);
        JLabel subNombre = new JLabel("NoisyCrow company" , JLabel.CENTER);
        subNombre.setVerticalAlignment(JLabel.TOP);
        nombre.setFont(fuenteTitulo);
        nombre.setBounds(0 , 0 , 750 , 87);
        //nombre.setBorder(BorderFactory.createLineBorder(Color.green));
        subNombre.setFont(fuenteSubTitulo);
        subNombre.setBounds(0 , 88 , 700 , 87);
        JLabel logo = new JLabel();
        logo.setBounds(700 , 0 , 300 , 175);
        try {
            File f = new File("src/main/java/com/NoisyCrow/ClassMateProject/DATA/LogoFinal.png");
            ImageIcon img = new ImageIcon(f.getPath());
            Image img2 = img.getImage().getScaledInstance(250, 175, Image.SCALE_DEFAULT);
            ImageIcon img3 = new ImageIcon(img2);
            logo.setIcon(img3);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        add(nombre);
        add(subNombre);
        add(logo);
    }
    
}