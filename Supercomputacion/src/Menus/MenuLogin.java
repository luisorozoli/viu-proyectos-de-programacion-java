package Menus;

import BBDD.UsuariosBBDD;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Entidades.*;

/**
 * Crea la clase que gestiona el login de los usuarios.
 * 
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class MenuLogin extends JFrame {

    JFrame frame = new JFrame("Ventana Login");
    JTextField usuarioTexto;
    JTextField claveTexto;
    Usuarios u = new Usuarios();

    public void MenuLogin() {
        
        JPanel panel = new JPanel();

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);

        panel.setLayout(null);

        //Usuario
        JLabel etiquetaUsuario = new JLabel("Usuario");
        etiquetaUsuario.setBounds(40, 20, 80, 25);
        panel.add(etiquetaUsuario);

        //JTextField
        usuarioTexto = new JTextField();
        usuarioTexto.setBounds(120, 20, 165, 25);
        panel.add(usuarioTexto);

        //Clave
        JLabel etiquetaClave = new JLabel("Clave");
        etiquetaClave.setBounds(40, 50, 80, 25);
        panel.add(etiquetaClave);

        claveTexto = new JTextField();
        claveTexto.setBounds(120, 50, 165, 25);
        panel.add(claveTexto);

        //Botones
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.setBounds(80, 100, 80, 25);
        botonAceptar.setSize(new Dimension(85, 40));
        
        //Evento
        botonAceptar.addActionListener(new AceptarBotonListener());

        panel.add(botonAceptar);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.setBounds(180, 100, 80, 25);
        botonCancelar.setSize(new Dimension(85, 40));

        botonCancelar.addActionListener(new CancelarBotonListener());
        panel.add(botonCancelar);

        frame.setVisible(true);

    }

    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Cancelar</strong> de la ventana de <strong>Login</strong>.<br>
     * Al hacer clic en el botón <strong>Cancelar</strong> se cerrará la ventana de login.
     */
    class CancelarBotonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            frame.dispose();
        }
    }
    
    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Aceptar</strong> de la ventana de <strong>Login</strong>.<br>
     * Al hacer clic en el botón <strong>Aceptar</strong> se comprobará el usuario y 
     * contraseña del usuario. Si usuario/clave son válidos nos mostrará la pantalla de la aplicación
     * sino aparecerá un mensaje de error indicándonos que el usuario/clave no son correctos.
     */
    class AceptarBotonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String sIdentificador = usuarioTexto.getText();
            String sClave = claveTexto.getText();
            Boolean bCamposVacios = false;
            
            
            if("".equals(sIdentificador) || sIdentificador== null || "".equals(sClave) || sClave == null) {
                JOptionPane.showMessageDialog(null, "Usuario o clave no pueden estar vacíos");
                bCamposVacios = true;
            }
            
            
            UsuariosBBDD comprobarLogin = new UsuariosBBDD();
            try {
                ResultSet resultado = comprobarLogin.comprobarLogin(sIdentificador, sClave);
                Boolean existe = resultado.next();
                                
                if (existe) {
                    
                    u.setUserId(resultado.getInt(1));
                    u.setIdentificafor(resultado.getString(2));
                    u.setClave(resultado.getString(3));
                    u.setTipoUsuario(resultado.getString(4));
                    
                    Vista v = new Vista(u);
                    frame.setVisible(false);
                    frame.dispose();

                } else {

                    if (!bCamposVacios) {
                        JOptionPane.showMessageDialog(null, "Usuario o clave incorrectas");
                        bCamposVacios = false;
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
