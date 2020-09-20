package com.NoisyCrow.ClassMateProject.funciones;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;
import com.google.gson.Gson;
import com.itextpdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class funcionesBasicas {
    private final String ruta = "src/main/java/com/NoisyCrow/ClassMateProject/ventana/windowVars.JSON";
    private Gson gson;
    private BufferedReader br;
    private FileReader fr;
    private String linea;
    private String fichero;

    public double suma (double x , double y){
        return x+y;
    }

    public String JSON(String atributo){
        gson = new Gson();
        fichero = "";
        try {
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);
            while( (linea = br.readLine()) != null ){
                fichero += linea;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Properties properties = gson.fromJson(fichero, Properties.class);
        return properties.getProperty(atributo);
        
    }
    public static void generarPDF(){
        //Creo el documento
        Document documento = new Document();
        try {
            //Creo el output que va a generar el documento
            FileOutputStream out = new FileOutputStream("src/main/java/com/NoisyCrow/ClassMateProject/DATA/prueba.pdf");
            PdfWriter.getInstance(documento, out).setInitialLeading(20);
            documento.open();

            documento.add(new Paragraph("Esto es un parrafo"));
            PdfPTable tabla = new PdfPTable(4);
            for(int i = 0; i<16 ; i++){
                tabla.addCell("Celda: "+i);
            }
            documento.add(tabla);
            documento.close();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        generarPDF();
    }
    
}