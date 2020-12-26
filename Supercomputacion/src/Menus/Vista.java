package Menus;

import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import Entidades.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que crea la ventana principal que se presenta luego del login exitoso
 * 
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class Vista extends JFrame{
    
    JLabel lblBienvenida, lblUsuario, lblTipoUsuario, lblMensaje;
    JButton btnUsuarios, btnCentros, btnTrabajos, btnInformacion;
    Usuarios user = new Usuarios();
    
    
    /**
     * Método constructor de la clase <strong>Vista</strong>
     * 
     * @param u Usuario. Dependiendo del tipo de usuario se muestran distintos botones
     */
    public Vista(Usuarios u){
        
        user = u;
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setLocation(100, 100);
        setResizable(false);
        setTitle("Centros de Supercomputación");
        
        setLayout(new AbsoluteLayout());
        
        lblBienvenida = new JLabel("Bienvenido al Sistema de Gestión de Supercomputadores!");
        lblBienvenida.setFont(new java.awt.Font("Arial", 1, 16));
        this.getContentPane().add(lblBienvenida, new AbsoluteConstraints(20, 20, 450, 20));
        
        lblUsuario = new JLabel("Usuario: "+ u.getIdentificador());
        lblUsuario.setFont(new java.awt.Font("Arial", 1, 12));
        this.getContentPane().add(lblUsuario, new AbsoluteConstraints(20, 50, 450, 20));
        
        lblTipoUsuario = new JLabel("Tipo de Usuario: "+u.getTipoUsuario());
        lblTipoUsuario.setFont(new java.awt.Font("Arial", 1, 12));
        this.getContentPane().add(lblTipoUsuario, new AbsoluteConstraints(20, 65, 450, 20));
        
        lblMensaje = new JLabel("Por favor, seleccione una opción para continuar:");
        lblMensaje.setFont(new java.awt.Font("Arial", 1, 12));
        this.getContentPane().add(lblMensaje, new AbsoluteConstraints(20, 95, 450, 20));
        
        
        //Se crean los botones
        btnUsuarios = new JButton("Usuarios");
        btnCentros = new JButton("Centros");
        btnTrabajos = new JButton("Trabajos");
        btnInformacion = new JButton("Información");
        
        //Los botones se muestran y ubican dependiendo del tipo de usuario
        switch (u.getTipoUsuario()) {
            case "Administrador":
                this.getContentPane().add(btnUsuarios, new AbsoluteConstraints(20, 130, 100, 20));
                this.getContentPane().add(btnCentros, new AbsoluteConstraints(130, 130, 100, 20));
                this.getContentPane().add(btnTrabajos, new AbsoluteConstraints(240, 130, 100, 20));
                this.getContentPane().add(btnInformacion, new AbsoluteConstraints(350, 130, 100, 20));
                break;
            case "AdministradorCentro":
                btnUsuarios.setVisible(false);
                this.getContentPane().add(btnCentros, new AbsoluteConstraints(20, 130, 100, 20));
                this.getContentPane().add(btnTrabajos, new AbsoluteConstraints(130, 130, 100, 20));
                this.getContentPane().add(btnInformacion, new AbsoluteConstraints(240, 130, 100, 20));
                break;
            default:
                btnUsuarios.setVisible(false);
                btnCentros.setVisible(false);
                this.getContentPane().add(btnTrabajos, new AbsoluteConstraints(20, 130, 100, 20));
                this.getContentPane().add(btnInformacion, new AbsoluteConstraints(130, 130, 100, 20));
                break;
        }
                
        btnUsuarios.addActionListener(new BotonUsuariosListener());
        btnCentros.addActionListener(new BotonCentrosListener());
        btnTrabajos.addActionListener(new BotonTrabajosListener());
        btnInformacion.addActionListener(new BotonInformacionListener());
    }
    
    
    /**
     * Clase que implementa el método <strong>ActionListener</strong> del botón
     * <strong>Usuarios</strong>.<br>
     * Si se hace clic en el botón <strong>Usuarios</strong>, se abre la ventana
     * de <strong>Gestión de Usuarios</strong>.
     */
    class BotonUsuariosListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            VistaUsuarios vu = new VistaUsuarios(user);
        }
    }
    
    
    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón de
     * <strong>Centros</strong>.<br>
     * Si se hace clic en el botón <strong>Centros</strong>,
     * se abre la ventana de <strong>Gestión de Centros</strong>.
     */
    class BotonCentrosListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            VistaCentros vc = new VistaCentros();
        }
    }
    
    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón de
     * <strong>Trabajos</strong>.<br>
     * Si se hace clic en el botón <strong>Trabajos</strong>, se abre la ventana
     * de <strong>Gestión de Trabajos</strong>.
     */
    class BotonTrabajosListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            VistaTrabajos vt = new VistaTrabajos();
        }
    }
    
    
    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Información</strong>.<br>
     * Si se hace clic en el botón <strong>Información</strong>, se abre la
     * ventana de <strong>Información</strong>.
     */
    class BotonInformacionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            VistaInformacion vi = new VistaInformacion();
        }
    }
}
