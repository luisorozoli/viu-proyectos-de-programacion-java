package Menus;

import BBDD.ConsultasBBDD;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class MenuLogin extends JFrame {

    JFrame frame = new JFrame("Ventana Login");
    JTextField usuarioTexto;
    JTextField claveTexto;
//
//    public MenuLogin() {
//    
//    }

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

//        JTextField
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

    //Eventos Botones
    class CancelarBotonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Has pulsado el botón Cancelar");
            frame.setVisible(false);
            frame.dispose();
        }
    }

    class AceptarBotonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Has pulsado el botón Aceptar");
            String sIdentificador = usuarioTexto.getText();
            System.out.println("sUSUARIO: "+ sIdentificador);
            String sClave = claveTexto.getText();
            System.out.println("sClave: "+sClave);
            Boolean bCamposVacios = false;
            
            
            if("".equals(sIdentificador) || sIdentificador== null || "".equals(sClave) || sClave == null) {
                JOptionPane.showMessageDialog(null, "Usuario o clave no pueden estar vacíos");
                bCamposVacios = true;
            }
            
            
            ConsultasBBDD comprobarLogin = new ConsultasBBDD();
            try {
                ResultSet resultado = comprobarLogin.comprobarLogin(sIdentificador, sClave);
                Boolean existe = resultado.next();
                String sTipoUsuario = "";
                
                if (existe) {
                    
                    sTipoUsuario = resultado.getString(4);
                    MenuPrincipal menuPrincipal = new MenuPrincipal();
                    menuPrincipal.MostrarMenu(sTipoUsuario);
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
