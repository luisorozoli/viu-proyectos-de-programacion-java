package Menus;

import BBDD.TrabajosBBDD;
import Entidades.Usuarios;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    //Declaración de botones y otros componentes
    public VistaInformacion() {

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
        JButton btnSimulacion, btnTrabajosSinAsignar, btnTrabajosEnProceso, btnHistoricoTrabajos;
        btnSimulacion = new JButton("Simular Operaciones");
        btnTrabajosSinAsignar = new JButton("Trabajos sin asignar");
        btnTrabajosEnProceso = new JButton("Trabajos en proceso");
        btnHistoricoTrabajos = new JButton("Histórico trabajos");

        this.getContentPane().add(btnSimulacion, new AbsoluteConstraints(150, 10, 200, 20));
        this.getContentPane().add(btnTrabajosSinAsignar, new AbsoluteConstraints(150, 30, 200, 20));
        this.getContentPane().add(btnTrabajosEnProceso, new AbsoluteConstraints(150, 50, 200, 20));
        this.getContentPane().add(btnHistoricoTrabajos, new AbsoluteConstraints(150, 70, 200, 20));

        btnSimulacion.addActionListener((ActionListener) new BotonSimularListener());

        btnTrabajosSinAsignar.addActionListener((ActionListener) new BotonTrabajosSinAsignar());
        btnTrabajosEnProceso.addActionListener((ActionListener) new BotonTrabajosEnProceso());
        btnHistoricoTrabajos.addActionListener((ActionListener) new BotonHistoricoTrabajos());

        JTable tblDatos = new JTable();
        JScrollPane scroll = new JScrollPane();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id_Trabajo");
        model.addColumn("Identificador");
        model.addColumn("Cantidad Oper.");
        model.addColumn("Propietario");
        model.addColumn("Centro");

        tblDatos.setModel(model);
        scroll.setViewportView(tblDatos);
        this.getContentPane().add(scroll, new AbsoluteConstraints(20, 100, 200, 20));

    }

    class BotonSimularListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            VistaSimulacion vistaSimulacion = new VistaSimulacion();
            vistaSimulacion.VistaSimulacion();
        }
    }

    class BotonTrabajosSinAsignar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Has pulsado un botón");
            refrescarTabla();

        }
    }

    class BotonTrabajosEnProceso implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class BotonHistoricoTrabajos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public void refrescarTabla() {
       
    }
}
