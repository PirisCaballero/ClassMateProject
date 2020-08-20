package com.NoisyCrow.ClassMateProject.funciones;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;
import com.google.gson.Gson;

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
    
}