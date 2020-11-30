/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class MenuPrincipal {
    
     private Frame ventana;

    public void MostrarMenu(String sTipoUsuario){
        ventana = new Frame("Supercomputacion");
        System.out.println("El tipo de usuario es: "+sTipoUsuario);
        MenuBar principal = new MenuBar();

        //crearemos un menú clásico para que se entienda la idea
        java.awt.Menu usuarios = new java.awt.Menu("Usuarios");
            MenuItem usuarios_accion1 = new MenuItem("Alta de usuario");
            MenuItem usuarios_accion2 = new MenuItem("Baja de usuario");
            MenuItem usuarios_accion3 = new MenuItem("Modificación de usuario");
            MenuItem usuarios_salir = new MenuItem("Salir");
        
        java.awt.Menu centros = new java.awt.Menu("Centros");
            MenuItem centros_accion1 = new MenuItem("Alta de centro");
            MenuItem centros_accion2 = new MenuItem("Baja de centro");
            MenuItem centros_accion3 = new MenuItem("Modificaión de centro");
        
        java.awt.Menu trabajos = new java.awt.Menu("Trabajos");
            MenuItem trabajos_accion1 = new MenuItem("Crear trabajo");
            MenuItem trabajos_accion2 = new MenuItem("Modificar trabajo");
            MenuItem trabajos_accion3 = new MenuItem("Eliminar trabajo");
        
        java.awt.Menu informacion = new java.awt.Menu("Informacion");
            MenuItem informacion_accion1 = new MenuItem("Colas de trabajo de cada centro");
            MenuItem informacion_accion2 = new MenuItem("Trabajos sin asignar");
            MenuItem informacion_accion3 = new MenuItem("Trabajos en proceso en cada centro");
            MenuItem informacion_accion4 = new MenuItem("Histórico de trabajos");
        
        java.awt.Menu salir = new java.awt.Menu("Salir");

        //ahora, agregamos los elementos del menú USUARIOS.
        usuarios.add(usuarios_accion1);
        usuarios.add(usuarios_accion2);
        usuarios.add(usuarios_accion3);
        usuarios.addSeparator(); //agrega línea divisoria
        usuarios.add(usuarios_salir);
        
        //ahora, agregamos los elementos del menú CENTROS.
        centros.add(centros_accion1);
        centros.add(centros_accion2);
        centros.add(centros_accion3);
        centros.addSeparator(); //agrega línea divisoria
        
        //ahora, agregamos los elementos del menú TRABAJOS.
        trabajos.add(trabajos_accion1);
        trabajos.add(trabajos_accion2);
        trabajos.add(trabajos_accion3);
        trabajos.addSeparator(); //agrega línea divisoria
        
        //ahora, agregamos los elementos del menú INFORMACION.
        informacion.add(informacion_accion1);
        informacion.add(informacion_accion2);
        informacion.add(informacion_accion3);
        informacion.add(informacion_accion4);
        informacion.addSeparator(); //agrega línea divisoria
                
         //agregar el listener para los elementos del menú
        MenuPrincipal.EscuchaItemMenu eim = new MenuPrincipal.EscuchaItemMenu();
            usuarios_accion1.addActionListener(eim);
            usuarios_accion2.addActionListener(eim);
            usuarios_accion3.addActionListener(eim);
            usuarios_salir.addActionListener(eim);
            
            centros_accion1.addActionListener(eim);
            centros_accion2.addActionListener(eim);
            centros_accion3.addActionListener(eim);
            
            trabajos_accion1.addActionListener(eim);
            trabajos_accion2.addActionListener(eim);
            trabajos_accion3.addActionListener(eim);
            
            informacion_accion1.addActionListener(eim);
            informacion_accion2.addActionListener(eim);
            informacion_accion3.addActionListener(eim);
            informacion_accion4.addActionListener(eim);
        
        //ahora, indicamos que el menú "principal" forma parte de la barra de menú
        principal.add(usuarios);
        principal.add(centros);
        principal.add(trabajos);
        principal.add(informacion);

        //Por último, le decimos a la ventana cuál es su barra de menu
        ventana.setMenuBar(principal);

        ventana.setSize(300, 300);
        ventana.setResizable(false);
        ventana.addWindowListener(new WindowListener(){
            public void windowOpened(WindowEvent e){}
            public void windowActivated(WindowEvent e){}
            public void windowDeactivated(WindowEvent e){}
            public void windowIconified(WindowEvent e){}
            public void windowDeiconified(WindowEvent e){}
            public void windowClosed(WindowEvent e){}
            public void windowClosing(WindowEvent e){
                ventana.setVisible(false);
                ventana.dispose();
            }
        });
        ventana.setVisible(true);
    }
    
        class EscuchaItemMenu implements ActionListener{
        public void actionPerformed(ActionEvent e){
            MenuItem item = (MenuItem)e.getSource();
            String texto = item.getLabel();
            System.out.println("Opción seleccionada: " + texto);
            if (texto.equals("Salir")){
                ventana.setVisible(false);
                ventana.dispose();
            }
        }
    }
}
