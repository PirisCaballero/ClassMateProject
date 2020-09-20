package com.NoisyCrow.ClassMateProject.Ventana.HTTP;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.NoisyCrow.ClassMateProject.Ventana.Paneles.JPanelE;

public class http extends JPanelE {

    /**
     *Aitor Piris Caballero
     */
    private static final long serialVersionUID = 1L;
    private JButton enlace;
    private JPanel contenedor;

    public http() {
        setName("http");
        contenedor = new JPanel();
        contenedor.setBounds(0, 0, 1000, 625);
        contenedor.setLayout(null);
        contenedor.setBackground(Color.yellow);

        enlace = new JButton("Enlace");
        enlace.setBounds(100 , 100 , 150 , 50);

        ActionListener abrir = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                abirNavegador();
            }
        };
        enlace.addActionListener(abrir);

        contenedor.add(enlace);
        add(contenedor);

    }

    public void abirNavegador(){
        if(java.awt.Desktop.isDesktopSupported()){
            java.awt.Desktop escritorio = java.awt.Desktop.getDesktop();
            if(escritorio.isSupported(java.awt.Desktop.Action.BROWSE)){
                try {
                    String url = System.getProperty("user.dir").replaceAll("\\\\", "/")+"/src/main/java/com/NoisyCrow/ClassMateProject/http/index.html";
                    System.out.println(url);
                    java.net.URI uri = new java.net.URI(url);
                    escritorio.browse(uri);
                } catch (URISyntaxException ex) {
                    System.out.println(ex);
                    ex.printStackTrace();
                }catch (IOException io){
                    System.out.println(io);
                    io.printStackTrace();
                }
            }
        }
    }
    
}
