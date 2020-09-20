package com.NoisyCrow.ClassMateProject.Ventana.Paneles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import com.NoisyCrow.ClassMateProject.Objetos.Usuario;
import com.NoisyCrow.ClassMateProject.funciones.gestorBBDD;

public class panelVerUsuarios extends JPanelE {

    /**
     * Aitor Piris
     */
    private static final long serialVersionUID = 1L;
    private gestorBBDD GBS;
    private JPanel contenedorPrincipal;
    private Font fuenteTitulo = new Font("Times New Roman", Font.PLAIN, 30);
    private Font fuenteLabels = new Font("Times New Roman", Font.PLAIN, 17);
    private DefaultTableModel modeloTabla;
    private JTable tablaUsuarios;
    private JScrollPane scroll;
    private Thread objetThread;

    public panelVerUsuarios(gestorBBDD GBD) {
        this.GBS = GBD;
        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setBounds(0, 0, 1000, 625);
        contenedorPrincipal.setBackground(Color.white);
        contenedorPrincipal.setLayout(null);
        objetThread = new Thread() {
            @Override
            public void run() {
                JLabel titulo = new JLabel("Ver Usuarios", JLabel.CENTER);
                titulo.setBounds(0, 0, 1000, 100);
                titulo.setFont(fuenteTitulo);
                /*
                 * creacion de la tabla
                 */
                modeloTabla = new DefaultTableModel();
                tablaUsuarios = new JTable(modeloTabla);
                scroll = new JScrollPane(tablaUsuarios);
                scroll.setBounds(50, 150, 850, 300);
                String[] columnas = { "Tipo de usuario", "DNI", "Nombre", "Apellidos", "Correo",
                        "Fecha de nacimiento" };
                modeloTabla.setColumnIdentifiers(columnas);
                tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(50);
                tablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(130);
                try {
                    setData();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    System.out.println(e2);
                    e2.printStackTrace();
                }

                contenedorPrincipal.add(titulo);
                contenedorPrincipal.add(scroll);
                JButton refresh = new JButton("Recargar");
                refresh.setBounds(425, 475, 150, 30);
                refresh.setFocusable(false);
                refresh.setFont(fuenteLabels);
                refresh.setBackground(Color.white);
                contenedorPrincipal.add(refresh);

                ActionListener refreshL = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            refresh();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            System.out.println(e1);
                            e1.printStackTrace();
                        }
                    }
                };
                refresh.addActionListener(refreshL);
            }
        };
        objetThread.start();
        add(contenedorPrincipal);
    }

    public void setData() throws SQLException {
        ArrayList<Usuario> lU = this.GBS.getUsuarios();
        for(Usuario u : lU){
            modeloTabla.addRow(u.toArray());
        }
    }
    public void refresh() throws SQLException {
        int a = modeloTabla.getRowCount()-1;
        for(int i = a; i>=0 ; i--){
            modeloTabla.removeRow(i);
        }
        setData();
    }
    
}