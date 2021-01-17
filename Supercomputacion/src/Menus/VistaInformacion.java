package Menus;

import BBDD.CentrosBBDD;
import BBDD.ProcesamientosBBDD;
import BBDD.TrabajosBBDD;
import Entidades.Usuarios;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Crea la ventana de Información que se abre al hacer clic en el boton
 * Información de la ventana principal
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class VistaInformacion extends JFrame {

    JTable tblDatos;
    JScrollPane scroll;
    DefaultTableModel model;
    JComboBox cboCentros;
    TrabajosBBDD daoTrabajos = new TrabajosBBDD();
    CentrosBBDD daoCentro = new CentrosBBDD();
    ProcesamientosBBDD daoProcesamiento = new ProcesamientosBBDD();
    JLabel lblInformacion;
    //Declaración de botones y otros componentes
    public VistaInformacion() throws SQLException {

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
        setTitle("Centros de Supercomputación - Información");

        setLayout(new AbsoluteLayout());

        //Botones
        JButton btnSimulacion, btnColaTrabajosCentro, btnTrabajosSinAsignar, btnTrabajosEnProceso, btnHistoricoTrabajos;
        btnSimulacion = new JButton("Simular Operaciones");

        btnColaTrabajosCentro = new JButton("Cola de trabajos por Centro");
        btnTrabajosSinAsignar = new JButton("Trabajos sin asignar");
        btnTrabajosEnProceso = new JButton("Trabajos en proceso por Centro");
        btnHistoricoTrabajos = new JButton("Histórico trabajos");

        this.getContentPane().add(btnSimulacion, new AbsoluteConstraints(150, 10, 200, 20));
        this.getContentPane().add(btnColaTrabajosCentro, new AbsoluteConstraints(150, 50, 200, 20));
        this.getContentPane().add(btnTrabajosSinAsignar, new AbsoluteConstraints(150, 80, 200, 20));
        this.getContentPane().add(btnTrabajosEnProceso, new AbsoluteConstraints(150, 110, 200, 20));
        this.getContentPane().add(btnHistoricoTrabajos, new AbsoluteConstraints(150, 140, 200, 20));

        //Etiqueta informacion
        lblInformacion = new JLabel("Seleccione centro en: Cola de Trab. por Centro y Trab. en Proc. por Centro");
        this.getContentPane().add(lblInformacion, new AbsoluteConstraints(13,170,500, 20));
        btnSimulacion.addActionListener((ActionListener) new BotonSimularListener());
        
        btnTrabajosSinAsignar.addActionListener((ActionListener) new BotonTrabajosSinAsignar());
        btnColaTrabajosCentro.addActionListener((ActionListener) new BotonColaTrabajosCentro());
        btnTrabajosEnProceso.addActionListener((ActionListener) new BotonTrabajosEnProceso());
        btnHistoricoTrabajos.addActionListener((ActionListener) new BotonHistoricoTrabajos());

        //Se crea la tabla donde se muestra el listado
        tblDatos = new JTable();
        scroll = new JScrollPane();
        model = new DefaultTableModel();
        tblDatos.setModel(model);
        scroll.setViewportView(tblDatos);
        this.getContentPane().add(scroll, new AbsoluteConstraints(13, 190, 460, 260));

        //Lista de centros
        //Se crea el combo de los centros a los que pertenecen los trabajos
        ArrayList<String> listaCentrosTrabajos = new ArrayList<>();
        try {
            listaCentrosTrabajos = (ArrayList<String>) daoCentro.listarCentrosTrabajos();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        cboCentros = new JComboBox();
        for (int i = 0; i < listaCentrosTrabajos.size(); i++) {
            cboCentros.addItem(listaCentrosTrabajos.get(i));
        }
        this.getContentPane().add(cboCentros, new AbsoluteConstraints(50, 50, 100, 20));
    }

    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>BotonSimularListener</strong> de la ventana <strong>Información</strong>.<br>
     */
    class BotonSimularListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            VistaSimulacion vistaSimulacion = new VistaSimulacion();
            vistaSimulacion.VistaSimulacion();
        }
    }

    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>BotonTrabajosSinAsignar</strong> de la ventana <strong>Información</strong>.<br>
     */
    class BotonTrabajosSinAsignar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            MostrarTrabajosSinAsignar();
        }
    }

    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>BotonColaTrabajosCentro</strong> de la ventana <strong>Información</strong>.<br>
     */
    class BotonColaTrabajosCentro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String sIdentCentro = cboCentros.getSelectedItem().toString();
            MostrarColaTrabajosCentro(sIdentCentro);
        }
    }

    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>BotonTrabajosEnProceso</strong> de la ventana <strong>Información</strong>.<br>
     */
    class BotonTrabajosEnProceso implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String sIdentCentro = cboCentros.getSelectedItem().toString();
            MostrarTrabajosEnProcesoCentro(sIdentCentro);
        }
    }

    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>BotonHistoricoTrabajos</strong> de la ventana <strong>Información</strong>.<br>
     */
    class BotonHistoricoTrabajos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            MostrarHistoricoTrabajosProcesados();
        }
    }

    /**
     * Método que muestra los trabajos sin asignar
     * @return true o false dependiendo de si se pudo o no eliminar el usuario.
     * @throws SQLException 
     */
    public void MostrarTrabajosSinAsignar() {
        while (this.model.getRowCount() > 0) {
            this.model.removeRow(0);
        }
        int i = 0;
        if(tblDatos.getColumnCount()>0) {
                model.setColumnCount(0);
        }


        System.out.println(tblDatos.getColumnCount());
        model.addColumn("Id_Trabajo");
        model.addColumn("Identificador");
        model.addColumn("Cant. Operaciones");
        model.addColumn("Propietario");
        model.addColumn("Centro");
        System.out.println("Estás en MostrarTrabajosSinAsignar");
        Object item[] = new Object[5];

        ResultSet listaTrabSinAsignar = null;
        try {
            listaTrabSinAsignar = daoTrabajos.listarTrabajosSinAsignar();
            while (listaTrabSinAsignar.next()) {
                item[0] = listaTrabSinAsignar.getInt("idtrabajos");
                item[1] = listaTrabSinAsignar.getString("identificador");
                item[2] = listaTrabSinAsignar.getInt("cantidadoperaciones");
                item[3] = listaTrabSinAsignar.getString("propietario");
                item[4] = listaTrabSinAsignar.getString("centroTrabajo");
                this.model.addRow(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    /**
     * Método que muestra el cola de los trabajos
     * @return true o false dependiendo de si se pudo o no eliminar el usuario.
     * @throws SQLException 
     */
    public void MostrarColaTrabajosCentro(String identCentro) {
        while (this.model.getRowCount() > 0) {
            this.model.removeRow(0);
        }
        
        if(tblDatos.getColumnCount()>0) {
                model.setColumnCount(0);
        }

        model.addColumn("Id_Trabajo");
        model.addColumn("Identificador");
        model.addColumn("Cant. Operaciones");
        model.addColumn("Propietario");
        model.addColumn("Estado Trabajo");
        model.addColumn("Centro");
        System.out.println("Estás en MostrarColaTrabajosCentro");
        Object item[] = new Object[6];

        ResultSet listaTrabSinAsignar = null;
        try {

            listaTrabSinAsignar = daoCentro.colaTrabajosCentro(identCentro);
            while (listaTrabSinAsignar.next()) {
                item[0] = listaTrabSinAsignar.getInt("idtrabajos");
                item[1] = listaTrabSinAsignar.getString("identificador");
                item[2] = listaTrabSinAsignar.getInt("cantidadoperaciones");
                item[3] = listaTrabSinAsignar.getString("propietario");
                item[4] = listaTrabSinAsignar.getString("estadotrabajo");
                item[5] = listaTrabSinAsignar.getString("centroTrabajo");
                this.model.addRow(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método que muestra el historico de los trabajos en proceso
     * @return true o false dependiendo de si se pudo o no eliminar el usuario.
     * @throws SQLException 
     */
    public void MostrarTrabajosEnProcesoCentro(String identCentro) {
        while (this.model.getRowCount() > 0) {
            this.model.removeRow(0);
        }
        
        if(tblDatos.getColumnCount()>0) {
                model.setColumnCount(0);
        }

        model.addColumn("Id_Trabajo");
        model.addColumn("Identificador");
        model.addColumn("Cant. Operaciones");
        model.addColumn("Propietario");
        model.addColumn("Estado Trabajo");
        model.addColumn("Operaciones restantes");
        model.addColumn("Centro");
        System.out.println("Estás en MostrarTrabajosEnProcesoCentro");
        Object item[] = new Object[7];

        ResultSet listaTrabajosEnProcCentro = null;
        try {

            listaTrabajosEnProcCentro = daoCentro.trabajosEnProcesoCentro(identCentro);
            while (listaTrabajosEnProcCentro.next()) {
                item[0] = listaTrabajosEnProcCentro.getInt("idtrabajos");
                item[1] = listaTrabajosEnProcCentro.getString("identificador");
                item[2] = listaTrabajosEnProcCentro.getInt("cantidadoperaciones");
                item[3] = listaTrabajosEnProcCentro.getString("propietario");
                item[4] = listaTrabajosEnProcCentro.getString("estadotrabajo");
                item[5] = listaTrabajosEnProcCentro.getString("operacionesrestantes");
                item[6] = listaTrabajosEnProcCentro.getString("centroTrabajo");
                this.model.addRow(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método que muestra el historico de los trabajos procesados
     * @return true o false dependiendo de si se pudo o no eliminar el usuario.
     * @throws SQLException 
     */
    public void MostrarHistoricoTrabajosProcesados() {
        while (this.model.getRowCount() > 0) {
            this.model.removeRow(0);
        }
        
        if(tblDatos.getColumnCount()>0) {
                model.setColumnCount(0);
        }

        model.addColumn("Id_Trabajo");
        model.addColumn("Identificador");
        model.addColumn("Propietario");
        model.addColumn("Centro");
        model.addColumn("Estado Trabajo");

        System.out.println("Estás en MostrarHistoricoTrabajosProcesados");
        Object item[] = new Object[5];

        ResultSet listaHistTrabajos = null;
        try {

            listaHistTrabajos = daoProcesamiento.listaHistoricoTrabajos();
            while (listaHistTrabajos.next()) {
                item[0] = listaHistTrabajos.getInt("idtrabajos");
                item[1] = listaHistTrabajos.getString("identificador");
                item[2] = listaHistTrabajos.getString("propietario");
                item[3] = listaHistTrabajos.getString("centroTrabajo");
                item[4] = listaHistTrabajos.getString("estadotrabajo");
                this.model.addRow(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
