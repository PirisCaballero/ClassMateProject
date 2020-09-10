package com.NoisyCrow.ClassMateProject.funciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.NoisyCrow.ClassMateProject.DATA.inic;
import com.NoisyCrow.ClassMateProject.Objetos.superUsuario;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FilenameUtils;
import org.ibex.nestedvm.util.Sort;

import jdk.nashorn.internal.parser.JSONParser;

public class lectorArchvivos {

    private HashMap<String, String> tipos;
    private JsonParser parser;
    private FileReader fr;
    private File f;

    public lectorArchvivos() {
        tipificarArchivos();
    }

    public Object getArchivo(File fi) throws JsonParseException, JsonMappingException, IOException {
        int tipoArchivo = Integer.parseInt(tipos.get(FilenameUtils.getExtension(fi.getName())));
        switch (tipoArchivo) {
            case 0:
                return getJson(fi);
            case 1:
                return getImage(fi);
            case 2:
                return getYML(fi);
            default:
                return null;
        }
    }

    private ImageIcon getImage(File fi) {
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

    private HashMap getJson(File f) {
        FileReader Jfr;
        HashMap<String, String> tipos = new HashMap<String, String>();
        try {
            Jfr = new FileReader(f);
            JsonObject datos = (JsonObject) parser.parse(Jfr);
            Set keys = datos.keySet();
            Object[] str = keys.toArray();
            for (Object o : str) {
                tipos.put((String) o, datos.get((String) o).toString());
            }
            return tipos;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se ha podido encontrar o leer el archivos");
            return null;
        }
    }

    private HashMap getYML(File f) throws JsonParseException, JsonMappingException, IOException {
        HashMap inicio = new HashMap<String,Object>();
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        inic in = om.readValue(f, inic.class);
        inicio.put("superUsuario", in.getSuperUsuario());
        inicio.put("dni" , in.getDNI());
        inicio.put("password" , in.getPassword());
        inicio.put("nombre" , in.getNombre());
        return inicio;
    }

    public void iniciarSesion(superUsuario su) throws JsonGenerationException, JsonMappingException, IOException {
        File f = new File("src/main/java/com/NoisyCrow/ClassMateProject/DATA/inicioSesion.yml");
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        om.writeValue(f, new inic("true" , ""+su.getDNI() , su.getPassword() , su.getNombre()));
    }
    public void cerrarSesion() throws JsonGenerationException, JsonMappingException, IOException {
        File f = new File("src/main/java/com/NoisyCrow/ClassMateProject/DATA/inicioSesion.yml");
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        om.writeValue(f, new inic("false" , "", "" , ""));
    }
    public boolean estaIniciadaLaSesion() throws JsonParseException, JsonMappingException, IOException {
        File f = new File("src/main/java/com/NoisyCrow/ClassMateProject/DATA/inicioSesion.yml");
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        inic ic = om.readValue(f , inic.class);
        if(ic.getSuperUsuario().equals("false")){
            return false;
        }else{
            return true;
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
