package com.NoisyCrow.ClassMateProject.Ventana.Paneles;

import javax.swing.*;

import com.NoisyCrow.ClassMateProject.Objetos.superUsuario;
import com.NoisyCrow.ClassMateProject.Ventana.ventanaPrincipal;
import com.NoisyCrow.ClassMateProject.funciones.gestorBBDD;
import com.NoisyCrow.ClassMateProject.funciones.lectorArchvivos;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.*;
import java.io.File;

public class panelPrincipal extends JPanel{

    /**
     *Aitor Piris
     */
    private static final long serialVersionUID = 1L;
    private Font fuenteLabels = new Font("Times New Roman", Font.ITALIC , 20);
    private Thread cargaElementos , crearElementos;
    private JPanel panelPrincipal;
    private JLabel dniL; private JTextField dniT;
    private JLabel passwordL ; private JPasswordField passwordT;
    private JButton logIn , singIN;
    private Pattern patDNI; private Matcher matDNI;
    private gestorBBDD gbd = new gestorBBDD();
    private boolean dniValido = false;
    private JButton verPass;
    private lectorArchvivos lA = new lectorArchvivos();
    private boolean passVisible = false;

    public panelPrincipal() {
        setLayout(null);
        setBounds(0, 175, 1000, 625);
        setBorder(BorderFactory.createLineBorder(Color.green));
        setVisible(false);
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
                verPass  = new JButton();
                verPass.setBounds(810 , 250 , 30 , 30);
                File fi = new File("src/main/java/com/NoisyCrow/ClassMateProject/DATA/Final_ojo.png");
                try {
                    verPass.setIcon((Icon)lA.getArchivo(fi));
                } catch (Exception e) {
                    verPass.setText("X");
                }
                verPass.setFocusable(false);
                verPass.setBackground(Color.white);

                singIN.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ventanaPrincipal.setPanel("panelRegistroSuperUsuario");
                    }
                });
                logIn.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        try {
                            if(dniValido){
                                boolean inicio = gbd.inicioSesion(Integer.parseInt(dniT.getText()), passwordT.getText());
                                if(inicio){
                                    superUsuario su = gbd.getSuperUsuario(Integer.parseInt(dniT.getText()), passwordT.getText());
                                    JOptionPane.showMessageDialog(null, "El inicio de sesion ha sido: "+inicio + " Bienvenido: " + su.getNombre());
                                    lA.iniciarSesion(su);
                                    ventanaPrincipal.setPanel("panelSesionIniciada");
                                }else{
                                    JOptionPane.showMessageDialog(null, "El inicio de sesion ha sido: " +inicio);
                                }
                            }else{
                                System.out.println("Error en el login");
                            }
                        } catch (Exception e2) {
                            System.out.println(e2);
                            e2.printStackTrace();
                        }
                    }
                });  
                verPass.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed (ActionEvent e){
                        if(!passVisible){
                            passVisible = true;
                            passwordT.setEchoChar((char)0);
                        }else{
                            passVisible = false;
                            passwordT.setEchoChar((char)'*');
                        }
                    }
                });  
            }
        };
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
                passwordT = new JPasswordField();
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
                panelPrincipal.add(verPass);
                repaint();

                while(this.isAlive()){
                    if(!dniT.getText().isEmpty()){
                        matDNI = patDNI.matcher(dniT.getText());
                        if(matDNI.find()){
                            dniT.setBorder(BorderFactory.createLineBorder(Color.green));
                            dniValido = true;
                        }else{
                            dniT.setBorder(BorderFactory.createLineBorder(Color.red));
                            dniValido = false;
                        }
                    }else{
                        dniT.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                    }
                }
            }
        };
        cargaElementos.start();  
        crearElementos.start();

        add(panelPrincipal);
    }
    
}