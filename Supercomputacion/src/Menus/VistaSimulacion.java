/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import BBDD.CentrosBBDD;
import BBDD.ProcesamientosBBDD;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class VistaSimulacion extends JFrame {

    JFrame frame = new JFrame("Simulación");
    JSpinner spnSegundos;
    CentrosBBDD daoCentro = new CentrosBBDD();
    ProcesamientosBBDD daoProcesamiento = new ProcesamientosBBDD();

    public void VistaSimulacion() {
        JPanel panel = new JPanel();

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.add(panel);

        panel.setLayout(null);

        //Usuario
        JLabel etiquetaOperaciones = new JLabel("Tiempo a simular");
        etiquetaOperaciones.setBounds(40, 20, 80, 25);
        panel.add(etiquetaOperaciones);

        //Spinner
        spnSegundos = new JSpinner();
        spnSegundos.setBounds(120, 20, 165, 25);
        panel.add(spnSegundos);

        //Botones
        JButton botonSimular = new JButton("Simular");
        botonSimular.setBounds(80, 100, 80, 25);
        botonSimular.setSize(new Dimension(85, 40));

        panel.add(botonSimular);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.setBounds(180, 100, 80, 25);
        botonCancelar.setSize(new Dimension(85, 40));

        panel.add(botonCancelar);

        //Eventos
        botonSimular.addActionListener(new VistaSimulacion.SimularBotonListener());
        botonCancelar.addActionListener(new VistaSimulacion.CancelarBotonListener());

        frame.setVisible(true);
    }

    /**
     * Clase que implementa el <strong>ActionListener</strong> del botón
     * <strong>Cancelar</strong> de la ventana de
     * <strong>Simulación</strong>.<br>
     * Al hacer clic en el botón <strong>Cancelar</strong> se cerrará la ventana
     * de simulación.
     */
    class CancelarBotonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            frame.dispose();
        }
    }

    class SimularBotonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int iSegundos = (Integer) spnSegundos.getValue();
            int iCapacidadProc = 0;
            int iCantidadOperaciones = 0;
            int iOperRestantes = 0 ;
            if (iSegundos > 0) {
                try {
                    ResultSet listaCentros = daoCentro.listarCentros();
                    while (listaCentros.next()) {

                        iCapacidadProc = listaCentros.getInt(3);
                        iCantidadOperaciones = iSegundos * iCapacidadProc;
                        //Lista trabajos del centro
                        ResultSet listaTrabajos = daoProcesamiento.TrabajosEnCola(listaCentros.getInt(1));
                        System.out.println("Centro: "+ listaCentros.getString(2));
                        System.out.println("listaTrabajos: "+listaTrabajos);
                        while (listaTrabajos.next()) {
                            System.out.println("Trabajo ID: "+listaTrabajos.getInt(2));
                            if (iCantidadOperaciones < listaTrabajos.getInt(5)) {
                                iOperRestantes = listaTrabajos.getInt(5);
                                iOperRestantes = iOperRestantes - iCantidadOperaciones;
                                System.out.println("Operaciones Restantes Trabajo en Ejecución: "+iOperRestantes);
                                if (!daoProcesamiento.actualizarOperRestantes(listaTrabajos.getInt(1), iOperRestantes, "Ejecutando")) {
                                    JOptionPane.showMessageDialog(null, "No se pudo actualizar las operaciones restantes.");
                                }
                                break;
                            } else if (iCantidadOperaciones>listaTrabajos.getInt(5)){
                                iOperRestantes = listaTrabajos.getInt(5);
                                iOperRestantes = iOperRestantes - iCantidadOperaciones;
                                iCantidadOperaciones = iCantidadOperaciones - iOperRestantes - (10*iCapacidadProc) ;
                                System.out.println("Operaciones Restantes Trabajo Finalizado: "+iOperRestantes);
                                if (!daoProcesamiento.actualizarOperRestantes(listaTrabajos.getInt(1), 0, "Finalizado")) {
                                    JOptionPane.showMessageDialog(null, "No se pudo actualizar las operaciones restantes.");
                                }
                            } else {
                                iOperRestantes = listaTrabajos.getInt(5);
                                iOperRestantes = iOperRestantes - iCantidadOperaciones;
                                iCantidadOperaciones = iCantidadOperaciones - iOperRestantes - (10*iCapacidadProc) ;
                                System.out.println("Operaciones Restantes Trabajo Finalizado: "+iOperRestantes);
                                if (!daoProcesamiento.actualizarOperRestantes(listaTrabajos.getInt(1), 0, "Finalizado")) {
                                    JOptionPane.showMessageDialog(null, "No se pudo actualizar las operaciones restantes.");
                                }
                            }
                        }
                    }
                     JOptionPane.showMessageDialog(null, "Simulación finalizada.");
                } catch (SQLException ex) {
                }
            } else {
                JOptionPane.showMessageDialog(null, "El tiempo no puede ser menor o igual a 0.");
            }
        }
    }
}
