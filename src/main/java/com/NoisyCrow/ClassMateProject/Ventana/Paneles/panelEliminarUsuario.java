package com.NoisyCrow.ClassMateProject.Ventana.Paneles;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import com.NoisyCrow.ClassMateProject.Objetos.Usuario;
import com.NoisyCrow.ClassMateProject.funciones.gestorBBDD;
import java.awt.*;

public class panelEliminarUsuario extends JFrame {

    /**
     * Aitor Piris
     */
    private static final long serialVersionUID = 1L;
    private gestorBBDD GBD;
    private JPanel contenedorPrincipal;
    private Choice usuarios;
    private JLabel usuariosL;
    private Font fuenteLabels = new Font("Times New Roman", Font.PLAIN, 17);
    private JButton eliminar;
    private ArrayList<Usuario> lU;

    public panelEliminarUsuario(gestorBBDD gbs) throws SQLException {
        this.GBD = gbs;
        lU = this.GBD.getUsuarios();
        setSize(450, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Eliminar usuario");
        setVisible(true);

        usuariosL = new JLabel("Usuarios", JLabel.CENTER);
        usuariosL.setBounds(20, 35, 150, 30);
        usuariosL.setFont(fuenteLabels);
        usuarios = new Choice();
        usuarios.setBounds(200, 35, 200, 30);
        usuarios.add("-----------------------------------");
        setData();
        eliminar = new JButton("Eliminar");
        eliminar.setBounds(175, 100, 100, 30);
        eliminar.setFocusable(false);
        eliminar.setBackground(Color.white);

        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setBounds(0, 0, 450, 200);
        contenedorPrincipal.setBackground(Color.white);
        contenedorPrincipal.setLayout(null);
        contenedorPrincipal.add(usuariosL);
        contenedorPrincipal.add(usuarios);
        contenedorPrincipal.add(eliminar);

        ActionListener eliminarL = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (usuarios.getSelectedIndex() != 0) {
                    int res = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que quiere eliminar este usuario?",
                            "Eliminar usuario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (res != 1) {
                        try {
                            JOptionPane.showMessageDialog(null, "El usuario ha sido eliminado: " + GBD.eliminarUsuario(getDNIparaEliminar()));
                            refresh();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else {
                        System.out.println("NO");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe elegir una opción valida");
                }
            }
        };
        eliminar.addActionListener(eliminarL);

        add(contenedorPrincipal);
    }

    public void setData() throws SQLException {
        for(Usuario u : lU){
            usuarios.add(u.getDNI() + " " + u.getNombre() + " " + u.getApellidos());
        }
    }
    public int getDNIparaEliminar(){
        return lU.get(usuarios.getSelectedIndex()-1).getDNI();
    }
    public void refresh() throws SQLException {
        usuarios.removeAll();
        usuarios.add("-----------------------------------");
        lU = this.GBD.getUsuarios();
        setData();
    }
    
}