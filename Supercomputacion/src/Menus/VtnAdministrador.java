/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class VtnAdministrador extends JFrame{
    
    public VtnAdministrador(){
        
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = pantalla.getScreenSize();
        int altPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocation((anchoPantalla/2)-350, (altPantalla/2)-250);
        setResizable(false);
        setTitle("Centros de Supercomputación");
        
        PanelPrincipal panel = new PanelPrincipal();
        add(panel);
        
        
        
        
        
    }
}

class PanelPrincipal extends JPanel{
    
    public PanelPrincipal(){
        
        setLayout(new BorderLayout());
        //Se agrega la barra de menús
        JMenuBar barramenu = new JMenuBar();
        
        
        //Se agregan los elementos a la barra de menús
        JMenu menuUsuarios = new JMenu ("Usuarios");
        JMenu menuCentros = new JMenu ("Centros");
        JMenu menuTrabajos = new JMenu ("Trabajos");
        JMenu menuInformacion = new JMenu ("Información");
        
        barramenu.add(menuUsuarios);
        barramenu.add(menuCentros);
        barramenu.add(menuTrabajos);
        barramenu.add(menuInformacion);
        
        //Se agregan las opciones del menú Usuarios
        
        JMenuItem altaUsuarios = new JMenuItem("Alta");
        JMenuItem bajaUsuarios = new JMenuItem("Baja");
        JMenuItem modificacionUsuarios = new JMenuItem("Modificación");
        
        menuUsuarios.add(altaUsuarios);
        menuUsuarios.add(bajaUsuarios);
        menuUsuarios.add(modificacionUsuarios);
        
               
        //Se agregan las opciones del menú Centros
        
        JMenuItem altaCentros = new JMenuItem("Alta");
        JMenuItem bajaCentros = new JMenuItem("Baja");
        JMenuItem modificacionCentros = new JMenuItem("Modificación");
        
        menuCentros.add(altaCentros);
        menuCentros.add(bajaCentros);
        menuCentros.add(modificacionCentros);
        
        //Se agregan las opciones del menú Trabajos
        
        JMenuItem altaTrabajos = new JMenuItem("Alta");
        JMenuItem modificacionTrabajos = new JMenuItem("Modificación");
        JMenuItem eliminarTrabajos = new JMenuItem("Eliminar");
        JMenuItem asignarTrabajos = new JMenuItem("Asignar");
        
        menuTrabajos.add(altaTrabajos);
        menuTrabajos.add(modificacionTrabajos);
        menuTrabajos.add(eliminarTrabajos);
        menuTrabajos.add(asignarTrabajos);
        
        //Se agregan las opciones del menú Información
        
        JMenu mEstadoActual = new JMenu("Estado Actual");
        
        JMenuItem colasTrabajosCentro = new JMenuItem("Colas trabajos centro");
        JMenuItem trabajosSinAsignar = new JMenuItem("Trabajos sin asignar");
        JMenuItem trabajosEnProceso = new JMenuItem("Trabajos en proceso");
        
        mEstadoActual.add(colasTrabajosCentro);
        mEstadoActual.add(trabajosSinAsignar);
        mEstadoActual.add(trabajosEnProceso);
        
        menuInformacion.add(mEstadoActual);
        
        JMenuItem historicoTrabajos = new JMenuItem("Histórico de trabajos");
        
        menuInformacion.add(historicoTrabajos);
        
        //Agrego la JMenuBar en el borderLayout.NORTH
        add(barramenu, BorderLayout.NORTH);
        
        panelCentral = new JPanel();
        
    }
    
    private JPanel panelCentral;
    
}
