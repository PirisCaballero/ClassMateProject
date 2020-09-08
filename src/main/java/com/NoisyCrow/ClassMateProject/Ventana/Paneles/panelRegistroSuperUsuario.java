package com.NoisyCrow.ClassMateProject.Ventana.Paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.NoisyCrow.ClassMateProject.Objetos.Usuario;
import com.NoisyCrow.ClassMateProject.Objetos.superUsuario;
import com.NoisyCrow.ClassMateProject.funciones.gestorBBDD;
import com.NoisyCrow.ClassMateProject.funciones.lectorArchvivos;

public class panelRegistroSuperUsuario extends JPanel{

     /**
     * Aitor Piris
     */
    private static final long serialVersionUID = 1L;
    private gestorBBDD GBS;
    private Font fuenteTitulo = new Font("Times New Roman", Font.PLAIN, 30);
    private Font fuenteLabels = new Font("Times New Roman", Font.PLAIN, 17);
    private Font fuenteInstrucciones = new Font("Times New Roman", Font.PLAIN, 12);
    private Thread gestorLabels;
    private JPanel contenedorPrincipal;
    private Pattern patNombre, patApellidos, patCorreo, patDNi, patFechaNacimiento , patPassword;
    private JTextField nombreT, apellidosT, correoT, dniT, fechaNacimientoT;
    private Matcher matNombre, matApelldios, matCorreo, matDNI, matFechaNacimiento , matPassword;
    private boolean nombreValido, apellidosValidos, correoValido, dniValido, fechaNacimientoValida, tipoValido , passValida;
    private JLabel passwordL; private JPasswordField passwordT;
    private Choice tipoC;
    private lectorArchvivos lA = new lectorArchvivos();
    private Thread ejecucionListener;
    private boolean passVisible = false;
    private JButton verPass;

    public panelRegistroSuperUsuario(gestorBBDD GBD) {
        setLayout(null);
        this.GBS = GBD;
        setBounds(0, 175, 1000, 625);
        setBorder(BorderFactory.createLineBorder(Color.pink));
        setVisible(false);
        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setBackground(Color.white);
        contenedorPrincipal.setLayout(null);
        contenedorPrincipal.setBounds(0, 0, 1000, 625);
        JLabel titulo = new JLabel("Super Usuario", JLabel.CENTER);
        titulo.setBounds(0, 0, 1000, 100);
        titulo.setFont(fuenteTitulo);
       
        gestorLabels = new Thread() {
            @Override
            public void run() {
                JLabel nombreL = new JLabel("Nombre");
                nombreL.setFont(fuenteLabels);
                nombreL.setBounds(50, 100, 150, 50);
                contenedorPrincipal.add(nombreL);
                nombreT = new JTextField();
                nombreT.setFont(fuenteLabels);
                nombreT.setBounds(250, 110, 250, 30);
                contenedorPrincipal.add(nombreT);
                patNombre = Pattern.compile("^[a-zA-Z]+$");

                JLabel apellidosL = new JLabel("Apellidos");
                apellidosL.setFont(fuenteLabels);
                apellidosL.setBounds(50, 150, 150, 50);
                contenedorPrincipal.add(apellidosL);
                apellidosT = new JTextField();
                apellidosT.setFont(fuenteLabels);
                apellidosT.setBounds(250, 160, 250, 30);
                contenedorPrincipal.add(apellidosT);
                patApellidos = Pattern.compile("^[a-zA-Z]+\\s[a-zA-Z]+$");

                JLabel correoL = new JLabel("Correo");
                correoL.setFont(fuenteLabels);
                correoL.setBounds(50, 200, 150, 50);
                contenedorPrincipal.add(correoL);
                correoT = new JTextField();
                correoT.setFont(fuenteLabels);
                correoT.setBounds(250, 210, 250, 30);
                contenedorPrincipal.add(correoT);
                patCorreo = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                JLabel dniL = new JLabel("DNI");
                dniL.setFont(fuenteLabels);
                dniL.setBounds(50, 250, 150, 50);
                contenedorPrincipal.add(dniL);
                dniT = new JTextField();
                dniT.setFont(fuenteLabels);
                dniT.setBounds(250, 260, 250, 30);
                contenedorPrincipal.add(dniT);
                patDNi = Pattern.compile("^[0-9]{8}$");

                JLabel tipoL = new JLabel("Tipo de cuenta");
                tipoL.setFont(fuenteLabels);
                tipoL.setBounds(50, 300, 150, 50);
                contenedorPrincipal.add(tipoL);
                tipoC = new Choice();
                tipoC.setFont(fuenteLabels);
                tipoC.add("----------");
                tipoC.add("Estudiante");
                tipoC.add("Docente");
                tipoC.setBounds(250, 310, 250, 30);
                contenedorPrincipal.add(tipoC);

                JLabel fechaNacimiento = new JLabel("Fecha de Nacimiento");
                fechaNacimiento.setFont(fuenteLabels);
                fechaNacimiento.setBounds(50, 350, 150, 50);
                contenedorPrincipal.add(fechaNacimiento);
                fechaNacimientoT = new JTextField();
                fechaNacimientoT.setFont(fuenteLabels);
                fechaNacimientoT.setText("dd/mm/aaaa");
                fechaNacimientoT.setForeground(Color.gray);
                fechaNacimientoT.setBounds(250, 360, 250, 30);
                contenedorPrincipal.add(fechaNacimientoT);
                patFechaNacimiento = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");

                passwordL = new JLabel("Contraseña");
                passwordL.setFont(fuenteLabels);
                passwordL.setBounds(550 , 100 , 110 , 50);
                contenedorPrincipal.add(passwordL);
                passwordT = new JPasswordField();
                passwordT.setBounds(670 , 110 , 250 , 30);
                passwordT.setFont(fuenteLabels);
                patPassword = Pattern.compile("^[A-Za-z0-9]{6,12}$");
                contenedorPrincipal.add(passwordT);
                verPass = new JButton();
                verPass.setBounds(930, 110, 30, 30);
                verPass.setFocusable(false);
                verPass.setBackground(Color.white);
                File fi = new File("src/main/java/com/NoisyCrow/ClassMateProject/DATA/Final_ojo.png");
                verPass.setIcon((Icon)lA.getArchivo(fi));
                contenedorPrincipal.add(verPass);
                JLabel instrucciones = new JLabel();
                String instruc = "<html><body>La contraseña tiene que tener como mínimo 6 digitos y <br> 12 como máximo,"+ 
                    "<br>también debe ser una combinación alfanumerica </body></html>";
                instrucciones.setText(instruc);
                instrucciones.setFont(fuenteInstrucciones);
                instrucciones.setBounds(550 , 130 , 350 , 100);
                contenedorPrincipal.add(instrucciones);

                JButton agregarUsuario = new JButton("Agregar Usuario");
                agregarUsuario.setFont(fuenteLabels);
                agregarUsuario.setBackground(Color.white);
                agregarUsuario.setFocusable(false);
                agregarUsuario.setBounds(400, 450, 200, 30);
                contenedorPrincipal.add(agregarUsuario);
                agregarUsuario.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (validarDatos()) {
                            try {
                                JOptionPane.showMessageDialog(null, "La inscripción ha sido: " + agregarUsuario());
                            } catch (HeadlessException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Los datos aportados no son correctos, vuelva a intentarlo");
                        }
                    }
                });
                verPass.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!passVisible){
                            passwordT.setEchoChar((char)0);
                            passVisible = true;
                        }else{
                            passwordT.setEchoChar((char)'*');
                            passVisible = false;
                        }
                    }
                });
                while (this.isAlive()) {
                    fechaNacimientoFocus();
                    comprobarPatrones(this);
                }
            }
        };
        gestorLabels.start();
        ////////////////////////////

        contenedorPrincipal.add(titulo);
        add(contenedorPrincipal);
    }

    public void resetCampos() {
        nombreT.setText("");
        fechaNacimientoT.setText("");
        apellidosT.setText("");
        tipoC.select(0);
        correoT.setText("");
        dniT.setText("");
        passwordT.setText("");
    }

    public boolean agregarUsuario() throws SQLException {
        boolean usuarioCreado = this.GBS.agregarSuperUsuario(crearSuperUsuario());
        resetCampos();
        return usuarioCreado;
    }
    public void fechaNacimientoFocus(){
        if(fechaNacimientoT.hasFocus() && fechaNacimientoT.getText().equals("dd/mm/aaaa")){
            fechaNacimientoT.setText("");
            fechaNacimientoT.setForeground(Color.black);
        }else{
            if(!fechaNacimientoT.hasFocus() && fechaNacimientoT.getText().isEmpty()){
                fechaNacimientoT.setText("dd/mm/aaaa");
                fechaNacimientoT.setForeground(Color.gray);
            }
        }
    }
    public void comprobarPatrones(Thread hilo){
        /*
        *Nombre
        */
        if(!nombreT.getText().isEmpty()){
            matNombre = patNombre.matcher(nombreT.getText());
            if(matNombre.find()){
                nombreT.setBorder(BorderFactory.createLineBorder(Color.green));
                nombreValido = true;
            }else{
                nombreT.setBorder(BorderFactory.createLineBorder(Color.red));
                 nombreValido =  false;
             }
        }else{
            nombreT.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }
        /*
        *Apellidos
        */
        if(!apellidosT.getText().isEmpty()){
            matApelldios = patApellidos.matcher(apellidosT.getText());
            if(matApelldios.find()){
                apellidosT.setBorder(BorderFactory.createLineBorder(Color.green));
                apellidosValidos = true;
            }else{
                apellidosT.setBorder(BorderFactory.createLineBorder(Color.red));
                apellidosValidos =  false;
             }
        }else{
            apellidosT.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }
        /*
        *Correo
        */
        if(!correoT.getText().isEmpty()){
            matCorreo = patCorreo.matcher(correoT.getText());
            if(matCorreo.find()){
                correoT.setBorder(BorderFactory.createLineBorder(Color.green));
                correoValido = true;
            }else{
                correoT.setBorder(BorderFactory.createLineBorder(Color.red));
                 correoValido =  false;
             }
        }else{
            correoT.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }
        /*
        *DNI
        */
        if(!dniT.getText().isEmpty()){
            matDNI = patDNi.matcher(dniT.getText());
            if(matDNI.find()){
                dniT.setBorder(BorderFactory.createLineBorder(Color.green));
                dniValido = true;
            }else{
                dniT.setBorder(BorderFactory.createLineBorder(Color.red));
                 dniValido =  false;
             }
        }else{
            dniT.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }
        /*
        *Contraseña
        */
        if(!passwordT.getText().isEmpty()){
            matPassword = patPassword.matcher(passwordT.getText());
            if(matPassword.find()){
                passwordT.setBorder(BorderFactory.createLineBorder(Color.green));
                passValida = true;
            }else{
                passwordT.setBorder(BorderFactory.createLineBorder(Color.red));
                passValida = false;
            }
        }else{
            passwordT.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }
        /*
        *Fecha de nacimiento
        */
        if(!fechaNacimientoT.getText().isEmpty()){
            matFechaNacimiento = patFechaNacimiento.matcher(fechaNacimientoT.getText());
            if(matFechaNacimiento.find()){
                fechaNacimientoT.setBorder(BorderFactory.createLineBorder(Color.green));
                fechaNacimientoValida = true;
            }else{
                fechaNacimientoT.setBorder(BorderFactory.createLineBorder(Color.red));
                 fechaNacimientoValida =  false;
             }
        }else{
            fechaNacimientoT.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }
        /*
        *Tipo de usuario
        */
        if(tipoC.getSelectedIndex()==0){
            tipoValido=false;
        }else{
            tipoValido = true;
        }
    }
    public superUsuario crearSuperUsuario (){
        superUsuario user = new superUsuario();
        user.setNombre(nombreT.getText());
        user.setApellidos(apellidosT.getText());
        user.setCorreo(correoT.getText());
        user.setDNI(Integer.parseInt(dniT.getText()));
        user.setFechaNacimiento(fechaNacimientoT.getText());
        user.setPassword(passwordT.getText());
        if(tipoC.getSelectedIndex() == 1){
            user.setTipo("Estudiante");
        }else{
            if(tipoC.getSelectedIndex()==2){
                user.setTipo("Docente");
            }
        }
        return user;
    }
    public boolean validarDatos(){
        if(nombreValido && apellidosValidos && correoValido && fechaNacimientoValida && dniValido && tipoValido && passValida){
            return true;
        }else{
            return false;
        }
    }
    
}
