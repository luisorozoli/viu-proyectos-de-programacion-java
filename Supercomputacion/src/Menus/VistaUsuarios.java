
package Menus;

//Crea la ventana de Usuarios que se abre al hacer clic en el boton Usuarios
//de la ventana principal

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class VistaUsuarios extends JFrame {
    
    JLabel lblBienvenida, lblId, lblClave, lblTipoUsuario;
    JTextField txtId, txtClave;
    JComboBox cboTipoUsuario;
    JScrollPane scroll;
    DefaultTableModel model;
    JTable tblDatos;
    JButton btnAgregar, btnEliminar, btnModificar, btnLimpiar;
    
    public VistaUsuarios(){
        
        //Información para centrar la ventana en la pantalla
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = pantalla.getScreenSize();
        int altPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocation((anchoPantalla/2)-250, (altPantalla/2)-250);
        setResizable(false);
        setTitle("Centros de Supercomputación - Usuarios");
        
        setLayout(new AbsoluteLayout());
        
        lblBienvenida = new JLabel("Gestión de Usuarios");
        lblBienvenida.setFont(new java.awt.Font("Arial", 1, 16));
        this.getContentPane().add(lblBienvenida, new AbsoluteConstraints(20, 20, 450, 20));
        
        lblId = new JLabel("Identificador:");
        lblId.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblId, new AbsoluteConstraints(10, 60, 100, 20));
        
        lblClave = new JLabel("Clave:");
        lblClave.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblClave, new AbsoluteConstraints(10, 90, 100, 20));
        
        lblTipoUsuario = new JLabel("Tipo Usuario:");
        lblTipoUsuario.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblTipoUsuario, new AbsoluteConstraints(10, 120, 100, 20));
        
        txtId = new JTextField();
        this.getContentPane().add(txtId, new AbsoluteConstraints(120, 60, 200, 20));
        
        txtClave = new JTextField();
        this.getContentPane().add(txtClave, new AbsoluteConstraints(120, 90, 200, 20));
        
        Object items[] = new Object [3];
        items[0]="Administrador";
        items[1]="AdministradorCentro";
        items[2]="Usuario";
        
        cboTipoUsuario = new JComboBox(items);
        this.getContentPane().add(cboTipoUsuario, new AbsoluteConstraints(120, 120, 200, 20));
        
        btnAgregar = new JButton("Agregar");
        this.getContentPane().add(btnAgregar, new AbsoluteConstraints(360, 60, 100, 20));
        
        btnEliminar = new JButton("Eliminar");
        this.getContentPane().add(btnEliminar, new AbsoluteConstraints(360, 90, 100, 20));
        
        btnModificar = new JButton("Modificar");
        this.getContentPane().add(btnModificar, new AbsoluteConstraints(360, 120, 100, 20));
        
        btnLimpiar = new JButton("Limpiar");
        this.getContentPane().add(btnLimpiar, new AbsoluteConstraints(360, 150, 100, 20));
        
        tblDatos = new JTable();
        scroll = new JScrollPane();
        model = new DefaultTableModel();
        model.addColumn("Id_usuario");
        model.addColumn("Identificador");
        model.addColumn("Clave");
        model.addColumn("Tipo Usuario");
        tblDatos.setModel(model);
        scroll.setViewportView(tblDatos);
        this.getContentPane().add(scroll, new AbsoluteConstraints(20, 190, 460, 260));
        
    }
    
}
