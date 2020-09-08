package com.NoisyCrow.ClassMateProject.funciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Set;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FilenameUtils;

public class lectorArchvivos {

    private HashMap<String, String> tipos;
    private JsonParser parser;
    private FileReader fr;
    private File f;

    public lectorArchvivos() {
        tipificarArchivos();
    }

    public Object getArchivo (File fi){
        int tipoArchivo = Integer.parseInt(tipos.get(FilenameUtils.getExtension(fi.getName())));
        switch (tipoArchivo) {
            case 0:
                return getJson(fi);
            case 1:
                return getImage(fi);
            default:
                return null;
        }
    }
    private ImageIcon getImage(File fi){
        try {
            BufferedImage img = ImageIO.read(new FileInputStream(fi));
            ImageIcon image = new ImageIcon(img);
            return image;
          } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
            return null;
          }
    }
    private HashMap getJson(File f){
        FileReader Jfr;
        HashMap<String , String> tipos = new HashMap<String,String>();
        try {
            Jfr = new FileReader(f);
            JsonObject datos = (JsonObject)parser.parse(Jfr);
            Set keys = datos.keySet();
            Object[] str = keys.toArray();
            for(Object o : str){
                tipos.put((String)o , datos.get((String)o).toString());
            }
            return tipos;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se ha podido encontrar o leer el archivos");
            return null;
        }
    }

    private void tipificarArchivos() {
        tipos = new HashMap<String,String>();
        parser = new JsonParser();
        f = new File("src/main/java/com/NoisyCrow/ClassMateProject/funciones/tiposDeArchivos.json");
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se ha podido encontrar o leer el archivos");
        }
        JsonObject datos = (JsonObject)parser.parse(fr);
        Set keys = datos.keySet();
        Object[] str = keys.toArray();
        for(Object o : str){
            tipos.put((String)o , datos.get((String)o).toString());
        }
    }
    
}
