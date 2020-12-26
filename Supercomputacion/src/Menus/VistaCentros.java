package Menus;

import BBDD.UsuariosBBDD;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import Entidades.*;
import BBDD.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 * Crea la ventana de Centros que se abre al hacer clic en el botón <strong>Centros</strong> 
 * de la ventana principal
 * 
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class VistaCentros extends JFrame {
        
    //Declaración de etiquetas, botones y demás componentes
    JLabel lblBienvenida, lblId, lblCapacidad, lblTamanoCola, lblAdministrador;
    JTextField txtIdentificador, txtCapacidad, txtTamanoCola;
    JComboBox cboAdministrador;
    JScrollPane scroll;
    DefaultTableModel model;
    JTable tblDatos;
    JButton btnAgregar, btnEliminar, btnModificar, btnLimpiar;
    int userId;
    Usuarios user = new Usuarios();
    Usuarios userp = new Usuarios();
    UsuariosBBDD daoUsuarios = new UsuariosBBDD();
    CentrosBBDD daoCentros = new CentrosBBDD();
    
    
    /**
     * Método contructor de la clase <strong>VistaCentros</strong>
     * @param u Usuarios - Corresponde al usuario que está logueado y abre la
     * ventana de <strong>Gestión de centros</strong>.
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
        
        txtCapacidad = new JTextField();
        this.getContentPane().add(txtCapacidad, new AbsoluteConstraints(120, 90, 200, 20));
        
        txtTamanoCola = new JTextField();
        this.getContentPane().add(txtTamanoCola, new AbsoluteConstraints(120, 120, 200, 20));
        
        //Se crean las opciones que tendrá el combo de usuario Administrador de centro
        ArrayList<String> listaAdministradores = new ArrayList<String>();
        listaAdministradores = daoUsuarios.listarAdministradoresCentro();
        
        cboAdministrador = new JComboBox();
        
        for(int i = 0; i< listaAdministradores.size(); i++){
            cboAdministrador.addItem(listaAdministradores.get(i));
        }
        this.getContentPane().add(cboAdministrador, new AbsoluteConstraints(120, 150, 200, 20));
        
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
        
        refrescarTabla(); 
    }
    
    
    /**
     * Método que carga y refresca la información contenida en la tabla que
     * lista a los <strong>Centros</strong>.
     */
    public void refrescarTabla(){
        while(this.model.getRowCount()>0){
            this.model.removeRow(0);
        }
        
        ResultSet lista = null;
        try {
            lista = daoCentros.listarCentros();
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
    
}
