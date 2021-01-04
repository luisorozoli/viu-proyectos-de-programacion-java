package Menus;

import BBDD.*;
import Entidades.Trabajos;
import Entidades.Usuarios;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Crea la ventana de Trabajos que se abre al hacer clic en el boton Trabajos de
 * la ventana principal
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class VistaTrabajos extends JFrame {

    //Declaración de botones y otros componentes
    JLabel lblBienvenida, lblId, lblCantidad, lblPropietario, lblCentros;
    JTextField txtIdentificador, txtPropietario;
    JSpinner spnCantidad;
    JScrollPane scroll;
    DefaultTableModel model;
    JTable tblDatos;
    JButton btnAgregar, btnEliminar, btnModificar, btnLimpiar;
    int trabajoId;
    JComboBox cboCentros, cboListaPropietarios;

    Usuarios userp = new Usuarios();
    Trabajos trabajo = new Trabajos();

    UsuariosBBDD daoUsuarios = new UsuariosBBDD();
    TrabajosBBDD daoTrabajos = new TrabajosBBDD();
    CentrosBBDD daoCentros = new CentrosBBDD();

    public VistaTrabajos(Usuarios u) throws SQLException {

        userp = u;
        //Información para centrar la ventana en la pantalla
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = pantalla.getScreenSize();
        int altPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocation((anchoPantalla / 2) - 250, (altPantalla / 2) - 250);
        setResizable(false);
        setTitle("Centros de Supercomputación - Trabajos");

        setLayout(new AbsoluteLayout());

        //Se agregan los componentes de la ventana de Gestión de Trabajos
        //ETIQUETAS
        lblBienvenida = new JLabel("Gestión de Trabajos");
        lblBienvenida.setFont(new java.awt.Font("Arial", 1, 16));
        this.getContentPane().add(lblBienvenida, new AbsoluteConstraints(20, 20, 450, 20));
        //Identificador trabajo
        lblId = new JLabel("Identificador:");
        lblId.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblId, new AbsoluteConstraints(10, 60, 100, 20));

        //Cantidad de operaciones trabajo
        lblCantidad = new JLabel("Cantidad Oper.:");
        lblCantidad.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblCantidad, new AbsoluteConstraints(10, 90, 100, 20));

        //Usuario/propietario trabajo
        lblPropietario = new JLabel("Usuario Prop.:");
        lblPropietario.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblPropietario, new AbsoluteConstraints(10, 120, 100, 20));
        
        //Centros
        lblCentros = new JLabel("Centros");
        lblCentros.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblCentros, new AbsoluteConstraints(10,150,100,20));
        
        //CUADROS DE TEXTO
        txtIdentificador = new JTextField();
        this.getContentPane().add(txtIdentificador, new AbsoluteConstraints(120, 60, 200, 20));
        spnCantidad = new JSpinner();
        this.getContentPane().add(spnCantidad, new AbsoluteConstraints(120, 90, 200, 20));
//        txtPropietario = new JTextField();
//        this.getContentPane().add(txtPropietario, new AbsoluteConstraints(120, 120, 200, 20));
        
       

        //Listar propietarios
        
        ArrayList<String> listaPropietariosTrabajos = new ArrayList<>();
        listaPropietariosTrabajos = (ArrayList<String>)daoUsuarios.listarPropietariosTrabajos(userp);
        
        cboListaPropietarios = new JComboBox();
        
        for (int i = 0; i<listaPropietariosTrabajos.size();i++) {
           cboListaPropietarios.addItem(listaPropietariosTrabajos.get(i));
        }
        
        //Se crea el combo de los centros a los que pertenecen los trabajos
        ArrayList<String> listaCentrosTrabajos = new ArrayList<>();
        listaCentrosTrabajos = (ArrayList<String>)daoCentros.listarCentrosTrabajos();
        
        this.getContentPane().add(cboListaPropietarios, new AbsoluteConstraints(120, 120, 200, 20));
        
        cboCentros = new JComboBox();
        
        for (int i=0;i<listaCentrosTrabajos.size();i++) {
            cboCentros.addItem(listaCentrosTrabajos.get(i));
        }
        
        this.getContentPane().add(cboCentros, new AbsoluteConstraints(120, 150, 200, 20));

        //Se crea la tabla donde se muestra el listado de trabajos
        tblDatos = new JTable();
        scroll = new JScrollPane();
        model = new DefaultTableModel();
        model.addColumn("Id_Trabajo");
        model.addColumn("Identificador");
        model.addColumn("Cantidad Oper.");
        model.addColumn("Propietario");
        model.addColumn("Centro");

        tblDatos.setModel(model);
        scroll.setViewportView(tblDatos);
        this.getContentPane().add(scroll, new AbsoluteConstraints(20, 190, 460, 260));

        //Se crean los botones
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar");
        btnLimpiar = new JButton("Limpiar");

        
    if (("administradorcentro").equals(userp.getTipoUsuario().toLowerCase())) {
        this.getContentPane().add(btnAgregar, new AbsoluteConstraints(360, 60, 100, 20));
        btnAgregar.setEnabled(false);
        this.getContentPane().add(btnEliminar, new AbsoluteConstraints(360, 90, 100, 20));
        btnEliminar.setEnabled(false);
        this.getContentPane().add(btnModificar, new AbsoluteConstraints(360, 120, 100, 20));
        btnModificar.setEnabled(false);
        this.getContentPane().add(btnLimpiar, new AbsoluteConstraints(360, 150, 100, 20));
    } else {
        this.getContentPane().add(btnAgregar, new AbsoluteConstraints(360, 60, 100, 20));
        btnAgregar.setEnabled(true);
        this.getContentPane().add(btnEliminar, new AbsoluteConstraints(360, 90, 100, 20));
        btnEliminar.setEnabled(false);
        this.getContentPane().add(btnModificar, new AbsoluteConstraints(360, 120, 100, 20));
        btnModificar.setEnabled(false);
        this.getContentPane().add(btnLimpiar, new AbsoluteConstraints(360, 150, 100, 20));
    }


        //Se agregan los Listeners a los botones y a la tabla
        
        
        tblDatos.addMouseListener((MouseListener) new MouseClic());
        btnAgregar.addActionListener((ActionListener) new BotonAgregarListener());
        btnEliminar.addActionListener((ActionListener) new BotonEliminarListener());
        btnModificar.addActionListener((ActionListener) new BotonModificarListener());
        btnLimpiar.addActionListener((ActionListener) new BotonLimpiarListener());
        
        
        refrescarTabla(userp);
    }

    class MouseClic implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = tblDatos.getSelectedRow();
            trabajoId = (int) tblDatos.getValueAt(fila, 0);
            txtIdentificador.setText((String) tblDatos.getValueAt(fila, 1));
            spnCantidad.setValue(Integer.parseInt(tblDatos.getValueAt(fila, 2).toString()));
            cboListaPropietarios.setSelectedItem((String)tblDatos.getValueAt(fila, 3));
            cboCentros.setSelectedItem((String)tblDatos.getValueAt(fila, 4));
            btnEliminar.setEnabled(true);
            
           if(("administradorcentro").equals(userp.getTipoUsuario().toLowerCase())){
               btnModificar.setEnabled(false);
           } else {
               btnModificar.setEnabled(true);
           }
                    
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
    
    class BotonAgregarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(("").equals(txtIdentificador.getText())) {
                JOptionPane.showMessageDialog(null, "El identificador no puede estar vacío");
            } else if(txtIdentificador.getText().length()>30) {
                JOptionPane.showMessageDialog(null, "El identificador no puede tener más de 30 caracteres");
            } else if(Integer.parseInt(spnCantidad.getValue().toString())<= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad de operaciones no puede ser menor o igual a cero");
            } else {
                try {
                    if(daoTrabajos.crearTrabajo(txtIdentificador.getText(), Integer.parseInt(spnCantidad.getValue().toString()), cboListaPropietarios.getSelectedItem().toString(), cboCentros.getSelectedItem().toString())){
                        
                        limpiarCampos();
                        refrescarTabla(userp);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido dar de alta el trabajo");
                    }
                }catch (SQLException ex) {
                            
                            }
            }
        }
    }
    
    
    class BotonEliminar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int x = JOptionPane.showConfirmDialog(null,"ESTÁ SEGURO DE ELIMINAR ESTE TRABAJO");
            if(x==0) {
                try {
                    if(daoTrabajos.eliminarTrabajo(trabajoId)){
                        limpiarCampos();
                        refrescarTabla(userp);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el centro");
                    }
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    class BotonModificarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(txtIdentificador.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "El identificador no puede estar vacío");
            } else if (txtIdentificador.getText().length()>30) {
                JOptionPane.showMessageDialog(null, "El identificador debe tener menos de 30 caracteres");
            } else if (Integer.parseInt(spnCantidad.getValue().toString())<=0) {
                JOptionPane.showMessageDialog(null, "La cantidad no puede ser menos o igual a cero");
            } else {
                System.out.println("TrabajoId: "+trabajoId);
                trabajo.setiIdTrabajo(trabajoId);
                trabajo.setsIdentificadorTrab(txtIdentificador.getText());
                trabajo.setsCantidadOperaciones((spnCantidad.getValue().toString()));
                trabajo.setsPropietario(cboListaPropietarios.getSelectedItem().toString());
                trabajo.setsCentroTrabajo(cboCentros.getSelectedItem().toString());
                
                int x = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO DE MODIFICAR ESTE TRABAJO?");
                if(x==0) {
                    try {
                        if(daoTrabajos.modificarTrabajo(trabajo)) {
                            limpiarCampos();
                            refrescarTabla(userp);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha podido modificar el trabajo");
                        }
                    } catch(SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                
            }
        }
        
    }
    
    class BotonEliminarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int x = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO DE ELIMINAR EL TRABAJO");
            if(x==0) {
                try {
                    if(daoTrabajos.eliminarTrabajo(trabajoId)) {
                        limpiarCampos();
                        refrescarTabla(userp);
                    } else {
                        JOptionPane.showMessageDialog(null, "No es posible eliminar el trabajo");
                    }
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    class BotonLimpiarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            limpiarCampos();
        }
    }
    
    //Rellenar tabla de Trabajos

    public void refrescarTabla(Usuarios userTrabajo) {
        while (this.model.getRowCount() > 0) {
            this.model.removeRow(0);
        }

        Usuarios us = new Usuarios();
        us = userTrabajo;

        ResultSet lista = null;

        try {
            if ("administrador".equals(userTrabajo.getTipoUsuario().toLowerCase())) {
//                lista = daoTrabajos.listarTrabajosUsuario(us);

                lista = daoTrabajos.listarTrabajos();
            } else if ("usuario".equals(userTrabajo.getTipoUsuario().toLowerCase())){
                lista = daoTrabajos.listarTrabajosUsuario(us);
            } else {
                lista = daoTrabajos.listarTrabajosCentro(us);
            }
                Object item[] = new Object[5];

                while (lista.next()) {
                    item[0] = lista.getInt("idtrabajos");
                    item[1] = lista.getString("identificador");
                    item[2] = lista.getInt("cantidadoperaciones");
                    item[3] = lista.getString("propietario");
                    item[4] = lista.getString("centroTrabajo");
                    this.model.addRow(item);

                }
                this.tblDatos.setModel(this.model);
//            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void limpiarCampos() {
        txtIdentificador.setText("");
        spnCantidad.setValue(0);
        cboListaPropietarios.setSelectedIndex(0);
        cboCentros.setSelectedIndex(0);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);

    }

}
