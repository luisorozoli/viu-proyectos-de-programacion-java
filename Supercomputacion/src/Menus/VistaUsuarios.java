package Menus;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import Entidades.*;
import BBDD.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que crea la ventana de <strong>Gestión de Usuarios</strong> que se abre al hacer
 * clic en el boton <strong>Usuarios</strong> de la ventana principal.
 * 
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class VistaUsuarios extends JFrame {
    
    JLabel lblBienvenida, lblId, lblClave, lblTipoUsuario;
    JTextField txtIdentificador, txtClave;
    JComboBox cboTipoUsuario;
    JScrollPane scroll;
    DefaultTableModel model;
    JTable tblDatos;
    JButton btnAgregar, btnEliminar, btnModificar, btnLimpiar;
    UsuariosBBDD daoUsuarios = new UsuariosBBDD();
    int userId;
    Usuarios user = new Usuarios();
    
    
    /**
     * Método contructor de la clase <strong>VistaUsuarios</strong>
     */
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
        
        
        //Se agregan los componentes de la ventana de Gestión de Usuarios
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
        
        txtIdentificador = new JTextField();
        this.getContentPane().add(txtIdentificador, new AbsoluteConstraints(120, 60, 200, 20));
        
        txtClave = new JTextField();
        this.getContentPane().add(txtClave, new AbsoluteConstraints(120, 90, 200, 20));
        
        
        //Se crean las opciones que tendrá el combo de tipo de usuario
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
        btnEliminar.setEnabled(false);
        
        btnModificar = new JButton("Modificar");
        this.getContentPane().add(btnModificar, new AbsoluteConstraints(360, 120, 100, 20));
        btnModificar.setEnabled(false);
        
        btnLimpiar = new JButton("Limpiar");
        this.getContentPane().add(btnLimpiar, new AbsoluteConstraints(360, 150, 100, 20));
        
        
        //Se crea la tabla donde se muestra el listado de usuarios
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
        
        //Se agregan los Listeners a los botones y a la tabla
        btnAgregar.addActionListener((ActionListener) new BotonAgregarListener());
        btnEliminar.addActionListener((ActionListener) new BotonEliminarListener());
        btnModificar.addActionListener((ActionListener) new BotonModificarListener());
        btnLimpiar.addActionListener((ActionListener) new BotonLimpiarListener());
        tblDatos.addMouseListener((MouseListener) new MouseClic());
        
        refrescarTabla();
        
    }
    
    
    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Agregar</strong>.<br>
     * Cuando se hace clic en el botón <strong>Agregar</strong>, luego de
     * validar la información contenida en los campos, se procede a crear el
     * usuario en la tabla de <strong>Usuarios</strong> de la base de datos.<br>
     * Si se pudo crear el usuario, se limpian todos los campos de la ventana y se
     * refresca la información de la tabla de usuarios para mostrar también el
     * usuario recientemente creado.
     */
    class BotonAgregarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(txtIdentificador.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El identificador no puede estar vacío");
            } else if (txtClave.getText().equals("")){
                JOptionPane.showMessageDialog(null, "La clave no puede estar vacía");
            } else {
                
                if(!daoUsuarios.crearUsuario(txtIdentificador.getText(), txtClave.getText(), cboTipoUsuario.getSelectedItem().toString())){
                    JOptionPane.showMessageDialog(null, "No se insertó registro");
                }
                
                limpiarCampos();
                refrescarTabla(); 
            }
            
        }

    }
    
    
    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Eliminar</strong>.<br>
     * Cuando se hace clic en el botón <strong>Eliminar</strong>, luego de
     * solicitar la validación del usuario de que se está seguro de querer
     * eliminarlo, se procede a eliminarlo de la tabla de <strong>Usuarios</strong>.<br>
     * Si se pudo eliminar el usuario, se limpian todos los campos de la ventana
     * y se refresca la información de la tabla de usuarios para que no se
     * muestre el usuario eliminado.
     */
    class BotonEliminarListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
                        
            int x = JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR ESTE USUARIO?");
            if(x == 0){
                try {
                    if(daoUsuarios.eliminarUsuario(userId)){
                        limpiarCampos();
                        refrescarTabla();
                        
                    } else{
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario");
                    }
                } catch (SQLException ex) {
                }
            }
        }
        
    }
    
    
    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Modificar</strong>.<br>
     * Cuando se hace clic en el botón <strong>Modificar</strong>, luego de
     * realizar las validaciones correspondientes, se procede a modificarlo
     * en la tabla de <strong>Usuarios</strong>.<br>
     * Si se pudo modificar el usuario, se limpian todos los campos de la ventana
     * y se refresca la información de la tabla de usuarios para que se
     * muestre la información actualizada.
     */
    class BotonModificarListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {

            if(txtIdentificador.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El identificador no puede estar vacío");
            } else if (txtClave.getText().equals("")){
                JOptionPane.showMessageDialog(null, "La clave no puede estar vacía");
            } else {
                
                user.setUserId(userId);
                user.setIdentificafor(txtIdentificador.getText());
                user.setClave(txtClave.getText());
                user.setTipoUsuario(cboTipoUsuario.getSelectedItem().toString());
            
                int x = JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE MODIFICAR ESTE USUARIO?");
                if(x == 0){
                    try {
                        if(daoUsuarios.modificarUsuario(user)){
                            limpiarCampos();
                            refrescarTabla();
                        } else{
                            JOptionPane.showMessageDialog(null, "No se pudo modificar el usuario");
                        }
                    } catch (SQLException ex) {
                    }
                }
                
            }
  
        }
        
    }
    
    
    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Limpiar</strong>.<br>
     * Cuando se hace clic en el botón <strong>Limpiar</strong>, se limpian
     * todos los campos de la ventana, con la excepción de la tabla que muestra
     * la lista de usuarios.
     */
    class BotonLimpiarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            limpiarCampos();
        }

    }
    
    
    /**
     * Clase que implementa el <strong>MouseListener</strong> del ratón cuando
     * se hace clic en una fila de la tabla que muestra la lista de
     * <strong>Usuarios</strong>.<br>
     * Cuando se hace clic en un fila de la tabla, se carga en los componentes
     * de la vista la información del usuario seleccionado y se habilitan los 
     * botones <strong>Eliminar</strong> y <strong>Modificar</strong>.
     */
    class MouseClic implements MouseListener {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            
            int fila = tblDatos.getSelectedRow();
            
            userId = (int) tblDatos.getValueAt(fila, 0);
            txtIdentificador.setText((String) tblDatos.getValueAt(fila, 1));
            txtClave.setText((String) tblDatos.getValueAt(fila, 2));
            cboTipoUsuario.setSelectedItem((String) tblDatos.getValueAt(fila, 3));
            
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
        
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
    
    }   

    
    /**
     * Método que carga y refresca la información contenida en la tabla que
     * lista a los <strong>Usuarios</strong>.
     */
    public void refrescarTabla(){
        while(this.model.getRowCount()>0){
            this.model.removeRow(0);
        }
        
        ResultSet lista = null;
        try {
            lista = daoUsuarios.listarUsuarios();
            Object item[] = new Object [4];
            
            while(lista.next()){
                item[0] = lista.getInt("idusuario");
                item[1] = lista.getString("identificador");
                item[2] = lista.getString("clave");
                item[3] = lista.getString("tipousuario");
                this.model.addRow(item);
            }
            
            this.tblDatos.setModel(this.model);
            
        } catch (SQLException ex) {
        }
        
    }

    
    /**
     * Método que limpia los campos de la ventana de <strong>Gestión de Usuarios</strong>
     * y deshabilita los botones <strong>Eliminar</strong> y <strong>Modificar</strong>.
     */
    public void limpiarCampos(){
        txtIdentificador.setText("");
        txtClave.setText("");
        cboTipoUsuario.setSelectedIndex(0);
        
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
    }
    
}
