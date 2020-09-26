package com.NoisyCrow.ClassMateProject.Ventana.Paneles;

import com.itextpdf.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import com.NoisyCrow.ClassMateProject.Objetos.Usuario;
import com.NoisyCrow.ClassMateProject.Objetos.botonAtras;
import com.NoisyCrow.ClassMateProject.funciones.gestorBBDD;

public class panelVerUsuarios extends JPanelE {

    /**
     * Aitor Piris
     */
    private static final long serialVersionUID = 1L;
    private gestorBBDD GBS;
    private JPanel contenedorPrincipal;
    private java.awt.Font fuenteTitulo = new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 30);
    private java.awt.Font fuenteLabels = new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 17);
    private DefaultTableModel modeloTabla;
    private JTable tablaUsuarios;
    private JScrollPane scroll;
    private Thread objetThread;
    private com.itextpdf.text.Font fuenteTituloPDF = new com.itextpdf.text.Font(FontFactory.getFont("Times New Roman", 30 , Font.NORMAL, BaseColor.YELLOW));
    private JButton botonGenerarPDF;
    private botonAtras bAtras;

    public panelVerUsuarios(gestorBBDD GBD) {
        this.GBS = GBD;
        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setBounds(0, 0, 1000, 625);
        contenedorPrincipal.setBackground(Color.white);
        contenedorPrincipal.setLayout(null);
        bAtras = new botonAtras();
        contenedorPrincipal.add(bAtras);
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
                refresh.setBounds(500, 475, 150, 30);
                refresh.setFocusable(false);
                refresh.setFont(fuenteLabels);
                refresh.setBackground(Color.white);
                contenedorPrincipal.add(refresh);

                botonGenerarPDF = new JButton("Generar PDF");
                botonGenerarPDF.setBounds(350 , 475 , 150 , 30);
                botonGenerarPDF.setFocusable(false);
                botonGenerarPDF.setFont(fuenteLabels);
                botonGenerarPDF.setBackground(Color.white);
                contenedorPrincipal.add(botonGenerarPDF);

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
                ActionListener pdf = new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        generarPDF();
                        abirNavegador();
                    }
                };
                botonGenerarPDF.addActionListener(pdf);
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
    public void generarPDF(){
        Document pdf = new Document();
        try {
            FileOutputStream out = new FileOutputStream("src/main/java/com/NoisyCrow/ClassMateProject/DATA/verUsuarios.pdf");
            PdfWriter.getInstance(pdf, out).setInitialLeading(40);
            pdf.open();
            pdf.addTitle("Titulo: Ver usuarios");
            pdf.addAuthor("Aitor Piris Caballero");

            FontFactory.registerDirectories();
            Font f = FontFactory.getFont("Times New Roman" , 20 , Font.NORMAL , BaseColor.BLACK);
            Paragraph p = new Paragraph("PDF generado para ver la lista de los usuarios registrados." , f);
            p.setAlignment(Element.ALIGN_CENTER);
            pdf.add(p);
            pdf.add(Chunk.NEWLINE);
            pdf.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(110f);
            String [] nomColum = {"Tipo USuario" , "DNI" , "Nombre" , "Apellidos" , "Correo" , "Fecha Nacimiento"};
            for(String str : nomColum){
                tabla.addCell(str);
            }
            ArrayList<Usuario> lU = this.GBS.getUsuarios();
            for(Usuario u : lU){
                tabla.addCell(u.getTipo());
                tabla.addCell(""+u.getDNI());
                tabla.addCell(u.getNombre());
                tabla.addCell(u.getApellidos());
                tabla.addCell(u.getCorreo());
                tabla.addCell(u.getFechaNacimiento());
            }
            pdf.add(tabla);
            pdf.close();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public void abirNavegador(){
        if(java.awt.Desktop.isDesktopSupported()){
            java.awt.Desktop escritorio = java.awt.Desktop.getDesktop();
            if(escritorio.isSupported(java.awt.Desktop.Action.BROWSE)){
                try {
                    String url = System.getProperty("user.dir").replaceAll("\\\\", "/")+"/src/main/java/com/NoisyCrow/ClassMateProject/DATA/verUsuarios.pdf";
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