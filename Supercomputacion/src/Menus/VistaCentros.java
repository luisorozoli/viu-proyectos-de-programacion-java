package Menus;

import BBDD.UsuariosBBDD;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import Entidades.*;
import BBDD.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 * Crea la ventana de <strong>Gestión de Centros</strong> que se abre al hacer clic en el botón <strong>Centros</strong> 
 * de la ventana principal
 * 
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class VistaCentros extends JFrame {
        
    //Declaración de etiquetas, botones y demás componentes
    JLabel lblBienvenida, lblId, lblCapacidad, lblTamanoCola, lblAdministrador;
    JTextField txtIdentificador;
    JSpinner spnCapacidad, spnTamanoCola;
    JComboBox cboAdministrador;
    JScrollPane scroll;
    DefaultTableModel model;
    JTable tblDatos;
    JButton btnAgregar, btnEliminar, btnModificar, btnLimpiar;
    int centerId;
    Usuarios userp = new Usuarios();
    Centros centro = new Centros();
    UsuariosBBDD daoUsuarios = new UsuariosBBDD();
    CentrosBBDD daoCentros = new CentrosBBDD();
    
    
    /**
     * Método contructor de la clase <strong>VistaCentros</strong>
     * @param u Usuarios - Corresponde al usuario que está logueado y abre la
     * ventana de <strong>Gestión de Centros</strong>.
     * @throws java.sql.SQLException
     */
    public VistaCentros(Usuarios u) throws SQLException{
        
        userp = u;
        
        
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
        setTitle("Centros de Supercomputación - Centros");
        
        setLayout(new AbsoluteLayout());
                
        //Se agregan los componentes de la ventana de Gestión de Centros
        lblBienvenida = new JLabel("Gestión de Centros");
        lblBienvenida.setFont(new java.awt.Font("Arial", 1, 16));
        this.getContentPane().add(lblBienvenida, new AbsoluteConstraints(20, 20, 450, 20));
        
        lblId = new JLabel("Identificador:");
        lblId.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblId, new AbsoluteConstraints(10, 60, 100, 20));
        
        lblCapacidad = new JLabel("Capacidad:");
        lblCapacidad.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblCapacidad, new AbsoluteConstraints(10, 90, 100, 20));
        
        lblTamanoCola = new JLabel("Tamaño cola:");
        lblTamanoCola.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblTamanoCola, new AbsoluteConstraints(10, 120, 100, 20));
        
        lblAdministrador = new JLabel("Administrador:");
        lblAdministrador.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblAdministrador, new AbsoluteConstraints(10, 150, 100, 20));
        
        txtIdentificador = new JTextField();
        this.getContentPane().add(txtIdentificador, new AbsoluteConstraints(120, 60, 200, 20));
        
        spnCapacidad = new JSpinner();
        this.getContentPane().add(spnCapacidad, new AbsoluteConstraints(120, 90, 200, 20));
        
        spnTamanoCola = new JSpinner();
        this.getContentPane().add(spnTamanoCola, new AbsoluteConstraints(120, 120, 200, 20));
        
        //Se crean las opciones que tendrá el combo de usuario Administrador de centro
        ArrayList<String> listaAdministradores = new ArrayList<>();
        listaAdministradores = daoUsuarios.listarAdministradoresCentro();
        
        cboAdministrador = new JComboBox();
        
        for(int i = 0; i< listaAdministradores.size(); i++){
            cboAdministrador.addItem(listaAdministradores.get(i));
        }
        this.getContentPane().add(cboAdministrador, new AbsoluteConstraints(120, 150, 200, 20));

        //Se crea la tabla donde se muestra el listado de centros
        tblDatos = new JTable();
        scroll = new JScrollPane();
        model = new DefaultTableModel();
        model.addColumn("Id_centro");
        model.addColumn("Identificador");
        model.addColumn("Capacidad");
        model.addColumn("Tamaño cola");
        model.addColumn("Administrador");
        tblDatos.setModel(model);
        scroll.setViewportView(tblDatos);
        this.getContentPane().add(scroll, new AbsoluteConstraints(20, 190, 460, 260));
        
        
        //Se crean los botones
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar");
        btnLimpiar = new JButton("Limpiar");
        
        //Si el usuario es Administrador se muestran todos los componentes
        //Si el usuario es AdministradorCentro se restringe su funcionalidad
        if(userp.getTipoUsuario().equals("Administrador")){
            this.getContentPane().add(btnAgregar, new AbsoluteConstraints(360, 60, 100, 20));
            this.getContentPane().add(btnEliminar, new AbsoluteConstraints(360, 90, 100, 20));
            btnEliminar.setEnabled(false);
            this.getContentPane().add(btnModificar, new AbsoluteConstraints(360, 120, 100, 20));
            btnModificar.setEnabled(false);
            this.getContentPane().add(btnLimpiar, new AbsoluteConstraints(360, 150, 100, 20));
        } else {
            this.getContentPane().add(btnModificar, new AbsoluteConstraints(360, 60, 100, 20));
            btnModificar.setEnabled(false);
            this.getContentPane().add(btnLimpiar, new AbsoluteConstraints(360, 90, 100, 20));
            txtIdentificador.setEnabled(false);
            cboAdministrador.setVisible(false);
            lblAdministrador.setVisible(false);
        }
        
        
        //Se agregan los Listeners a los botones y a la tabla
        tblDatos.addMouseListener((MouseListener) new MouseClic());
        btnAgregar.addActionListener((ActionListener) new BotonAgregarListener());
        btnEliminar.addActionListener((ActionListener) new BotonEliminarListener());
        btnModificar.addActionListener((ActionListener) new BotonModificarListener());
        btnLimpiar.addActionListener((ActionListener) new BotonLimpiarListener());
                
        refrescarTabla(userp); 
    }
    
    
    /**
     * Clase que implementa el <strong>MouseListener</strong> del ratón cuando
     * se hace clic en una fila de la tabla que muestra la lista de
     * <strong>Centros</strong>.<br>
     * Al hacer clic en un fila de la tabla, se carga en los componentes
     * de la vista la información del centro seleccionado y se habilitan los 
     * botones <strong>Eliminar</strong> y <strong>Modificar</strong>.
     */
    class MouseClic implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            int fila = tblDatos.getSelectedRow();
            
            centerId = (int) tblDatos.getValueAt(fila, 0);
            txtIdentificador.setText((String) tblDatos.getValueAt(fila, 1));
            spnCapacidad.setValue( Integer.parseInt(tblDatos.getValueAt(fila, 2).toString()));
            spnTamanoCola.setValue( Integer.parseInt(tblDatos.getValueAt(fila, 3).toString()));
            cboAdministrador.setSelectedItem((String) tblDatos.getValueAt(fila, 4));
            
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
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Agregar</strong> de la ventana <strong>Gestión de Centros</strong>.<br>
     * Al hacer clic en el botón <strong>Agregar</strong>, luego de
     * validar la información contenida en los componentes, se procede a crear el
     * centro en la tabla de <strong>Centros</strong> de la base de datos.<br>
     * Si se pudo crear el centro, se limpian todos los componentes de la ventana y se
     * refresca la información de la tabla de centros para mostrar también el
     * centro recientemente creado.
     */
    class BotonAgregarListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(txtIdentificador.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El identificador no puede estar vacío");
            } else if(txtIdentificador.getText().length()>30 ){
                JOptionPane.showMessageDialog(null, "El identificador no puede tener más de 30 caracteres");               
            } else if (Integer.parseInt(spnCapacidad.getValue().toString()) <= 0){
                JOptionPane.showMessageDialog(null, "La capacidad no puede ser menor o igual a cero");
            } else if(Integer.parseInt(spnTamanoCola.getValue().toString()) <= 0){
                JOptionPane.showMessageDialog(null, "El tamaño de la cola no puede ser menor o igual a cero");
            } else {
                
                try {
                    if(daoCentros.crearCentro(txtIdentificador.getText(), Integer.parseInt(spnCapacidad.getValue().toString()), Integer.parseInt(spnTamanoCola.getValue().toString()),cboAdministrador.getSelectedItem().toString())){
                        limpiarCampos();
                        refrescarTabla(userp);
                    }else{
                        JOptionPane.showMessageDialog(null, "Identificador existente - Intente con un identificador distinto");
                    }
                } catch (SQLException ex) {
                }
            }
        }
    }
    
    
    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Eliminar</strong> de la ventana <strong>Gestión de Centros</strong>.<br>
     * Al hacer clic en el botón <strong>Eliminar</strong>, luego de
     * solicitar la validación del usuario de que se está seguro de querer
     * eliminarlo, se procede a su eliminación de la tabla de <strong>Centros</strong>.<br>
     * Si se pudo eliminar el centro, se limpian todos los componentes de la ventana
     * y se refresca la información de la tabla de centros para que no se
     * muestre el centro eliminado.
     */
    class BotonEliminarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            int x = JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR ESTE CENTRO?");
            if(x == 0){
                try {
                    if(daoCentros.eliminarCentro(centerId)){
                        limpiarCampos();
                        refrescarTabla(userp);
                            
                    } else{
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el centro");
                    }
                } catch (SQLException ex) {
                }
            }
        }
    }
    
    
    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Modificar</strong> de la ventana de <strong>Gestión de Centros</strong>.<br>
     * Al hacer clic en el botón <strong>Modificar</strong>, luego de
     * realizar las validaciones correspondientes, se procede a modificar el centro
     * en la tabla de <strong>Centros</strong>.<br>
     * Si se pudo realizar el update, se limpian todos los componentes de la ventana
     * y se refresca la información de la tabla de centros para que se
     * muestre la información actualizada.
     */
    class BotonModificarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(txtIdentificador.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El identificador no puede estar vacío");           
            } else if(txtIdentificador.getText().length()>30 ){
                JOptionPane.showMessageDialog(null, "El identificador debe tener menos de 30 caracteres");
            } else if (Integer.parseInt(spnCapacidad.getValue().toString()) <= 0){
                JOptionPane.showMessageDialog(null, "La capacidad no puede ser menor o igual a cero");
            } else if(Integer.parseInt(spnTamanoCola.getValue().toString()) <= 0){
                JOptionPane.showMessageDialog(null, "El tamaño de la cola no puede ser menor o igual a cero");
            } else {
                centro.setIdCentro(centerId);
                centro.setsIdentificadorCentro(txtIdentificador.getText());
                centro.setCapacidadProcesamiento(Integer.parseInt(spnCapacidad.getValue().toString()));
                centro.setTamanoMaxCola(Integer.parseInt(spnTamanoCola.getValue().toString()));
                centro.setsAdministrador(cboAdministrador.getSelectedItem().toString());
            
                int x = JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE MODIFICAR ESTE CENTRO?");
                if(x == 0){
                    try {
                        if(daoCentros.modificarCentro(centro)){
                            limpiarCampos();
                            refrescarTabla(userp);
                        } else{
                            JOptionPane.showMessageDialog(null, "Identificador existente - Intente con un identificador distinto");
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
     * todos los componentes de la ventana, con la excepción de la tabla que muestra
     * la lista de centros.
     */
    class BotonLimpiarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            limpiarCampos();
        }
    }
        
    
    /**
     * Método que carga y refresca la información contenida en la tabla que
     * lista los <strong>Centros</strong>.
     * @param userCentro Si el usuario es Administrador, verá todos los centros,
     * en cambio, si es AdministradorCentro solo verá los que administra.
     */
    public void refrescarTabla(Usuarios userCentro){
        while(this.model.getRowCount()>0){
            this.model.removeRow(0);
        }
        
        Usuarios us = new Usuarios();
        us = userCentro;
        
        ResultSet lista = null;
        try {
            lista = daoCentros.listarCentrosAdmin(us);
            Object item[] = new Object [5];
            
            while(lista.next()){
                item[0] = lista.getInt("idcentro");
                item[1] = lista.getString("identificador");
                item[2] = lista.getInt("capacidadprocesamiento");
                item[3] = lista.getInt("tamanomaxcola");
                item[4] = lista.getString("administrador");
                this.model.addRow(item);
            }
            
            this.tblDatos.setModel(this.model);
            
        } catch (SQLException ex) {
        }
    }
    
    
    /**
     * Método que limpia los campos de la ventana de <strong>Gestión de Centros</strong>
     * y deshabilita los botones <strong>Eliminar</strong> y <strong>Modificar</strong>.
     */
    public void limpiarCampos(){
        txtIdentificador.setText("");
        spnCapacidad.setValue(0);
        spnTamanoCola.setValue(0);
        cboAdministrador.setSelectedIndex(0);
        
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
    }
    
}
