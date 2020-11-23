/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
import java.awt.*;
import java.awt.event.*;

public class supercomputacion{
    private Frame ventana;

    public supercomputacion(){
        ventana = new Frame("Supercomputacion");
        MenuBar principal = new MenuBar();

        //crearemos un menú clásico para que se entienda la idea
        Menu usuarios = new Menu("Usuarios");
            MenuItem usuarios_accion1 = new MenuItem("Alta de usuario");
            MenuItem usuarios_accion2 = new MenuItem("Baja de usuario");
            MenuItem usuarios_accion3 = new MenuItem("Modificación de usuario");
            MenuItem usuarios_salir = new MenuItem("Salir");
        
        Menu centros = new Menu("Centros");
            MenuItem centros_accion1 = new MenuItem("Alta de centro");
            MenuItem centros_accion2 = new MenuItem("Baja de centro");
            MenuItem centros_accion3 = new MenuItem("Modificaión de centro");
        
        Menu trabajos = new Menu("Trabajos");
            MenuItem trabajos_accion1 = new MenuItem("Crear trabajo");
            MenuItem trabajos_accion2 = new MenuItem("Modificar trabajo");
            MenuItem trabajos_accion3 = new MenuItem("Eliminar trabajo");
        
        Menu informacion = new Menu("Informacion");
            MenuItem informacion_accion1 = new MenuItem("Colas de trabajo de cada centro");
            MenuItem informacion_accion2 = new MenuItem("Trabajos sin asignar");
            MenuItem informacion_accion3 = new MenuItem("Trabajos en proceso en cada centro");
            MenuItem informacion_accion4 = new MenuItem("Histórico de trabajos");
        
        Menu salir = new Menu("Salir");

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
        EscuchaItemMenu eim = new EscuchaItemMenu();
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

    public static void main(String[] args){
        supercomputacion pm = new supercomputacion();
    }
}