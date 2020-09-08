package com.NoisyCrow.ClassMateProject.Ventana.Paneles;

import javax.swing.*;

import com.NoisyCrow.ClassMateProject.Ventana.ventanaPrincipal;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.*;

public class panelPrincipal extends JPanel{

    /**
     *Aitor Piris
     */
    private static final long serialVersionUID = 1L;
    private Font fuenteLabels = new Font("Times New Roman", Font.ITALIC , 20);
    private Thread cargaElementos , crearElementos;
    private JPanel panelPrincipal;
    private JLabel dniL; private JTextField dniT;
    private JLabel passwordL ; private JTextField passwordT;
    private JButton logIn , singIN;
    private Pattern patDNI; private Matcher matDNI;

    public panelPrincipal() {
        setLayout(null);
        setBounds(0, 175, 1000, 625);
        setBorder(BorderFactory.createLineBorder(Color.green));
        setVisible(true);
        setBackground(Color.white);
        panelPrincipal = new JPanel();
        panelPrincipal.setBounds(0 , 0 , 1000 , 625);
        panelPrincipal.setBackground(Color.white);
        panelPrincipal.setLayout(null);

        crearElementos = new Thread(){
            @Override
            public void run(){
                singIN = new JButton("Registrarse");
                logIn = new JButton("Iniciar sesión");

                singIN.addActionListener(new ActionListener() {
			
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ventanaPrincipal.setPanel("panelRegistroSuperUsuario");
                    }
                });    
            }
        };
        crearElementos.start();
        cargaElementos = new Thread(){
            @Override
            public void run(){
                dniL = new JLabel("DNI del superUsuario");
                dniL.setBounds(150 , 200 , 300 , 30);
                dniL.setHorizontalAlignment(JLabel.CENTER);
                dniL.setFont(fuenteLabels);
                dniT = new JTextField();
                dniT.setBounds(500 , 200 , 300 , 30);
                patDNI = Pattern.compile("^[0-9]{8}$");
                //
                passwordL = new JLabel("Contraseña del superUsuario");
                passwordL.setBounds(150 , 250 , 300 , 30);
                passwordL.setHorizontalAlignment(JLabel.CENTER);
                passwordL.setFont(fuenteLabels);
                passwordT = new JTextField();
                passwordT.setBounds(500 , 250 , 300 , 30);
                //
                logIn.setBounds( 275 , 350 , 200 , 30);
                logIn.setBackground(Color.white);
                logIn.setFocusable(false);
                singIN.setBounds(480 , 350 , 200 , 30);
                singIN.setBackground(Color.white);
                singIN.setFocusable(false);

                panelPrincipal.add(dniL); panelPrincipal.add(dniT);
                panelPrincipal.add(passwordL);panelPrincipal.add(passwordT);
                panelPrincipal.add(logIn);panelPrincipal.add(singIN);
                repaint();

                while(this.isAlive()){
                    if(!dniT.getText().isEmpty()){
                        matDNI = patDNI.matcher(dniT.getText());
                        if(matDNI.find()){
                            dniT.setBorder(BorderFactory.createLineBorder(Color.green));
                        }else{
                            dniT.setBorder(BorderFactory.createLineBorder(Color.red));
                        }
                    }else{
                        dniT.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                    }
                }
            }
        };
        cargaElementos.start();  
          
        add(panelPrincipal);
    }
    
}