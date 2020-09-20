package com.NoisyCrow.ClassMateProject.Objetos;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import com.NoisyCrow.ClassMateProject.Ventana.ventanaPrincipal;
import com.NoisyCrow.ClassMateProject.Ventana.Paneles.JPanelE;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class botonAtras extends JButton {

    /**
     * Aitor Piris Caballero
     */
    private static final long serialVersionUID = 1L;

    public botonAtras() {
        setText("Atrás");
        setBounds(850, 50, 75, 30);
        setFocusable(false);
        setBackground(Color.white);

        ActionListener atras = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    setPanelAtras();
                } catch (JsonParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (JsonMappingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        };
        addActionListener(atras);
    }

    public void setPanelAtras() throws JsonParseException, JsonMappingException, IOException {
        if(ventanaPrincipal.listaPanelesAbiertos.size() >= 15){
            flush();
            int penultimoPanel = ventanaPrincipal.listaPanelesAbiertos.size()-2;
            if(penultimoPanel >= 0){
                String nombrePanel = ventanaPrincipal.listaPanelesAbiertos.get(penultimoPanel).getName();
                ventanaPrincipal.setPanel(nombrePanel);
            }else{
                System.out.println("No hay paneles de atras" + penultimoPanel);
            }
        }else{
            int penultimoPanel = ventanaPrincipal.listaPanelesAbiertos.size()-2;
            if(penultimoPanel >= 0){
                String nombrePanel = ventanaPrincipal.listaPanelesAbiertos.get(penultimoPanel).getName();
                ventanaPrincipal.setPanel(nombrePanel);
            }else{
                System.out.println("No hay paneles de atras" + penultimoPanel);
            }
        }
    }
    public void flush(){
        ArrayList<JPanelE> temp = new ArrayList<JPanelE>();
        int o = ventanaPrincipal.listaPanelesAbiertos.size()-1;
        for( int i = o ; i<5 ; i--){
            temp.add(ventanaPrincipal.listaPanelesAbiertos.get(i));
        }
        ventanaPrincipal.listaPanelesAbiertos.clear();
        for(JPanelE p : temp){
            ventanaPrincipal.listaPanelesAbiertos.add(p);
        }
    }
    
}
