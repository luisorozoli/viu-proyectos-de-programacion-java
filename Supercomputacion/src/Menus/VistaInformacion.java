
package Menus;

//Crea la ventana de Información que se abre al hacer clic en el boton Información
//de la ventana principal

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;


public class VistaInformacion extends JFrame {
    
    //Declaración de botones y otros componentes
    
    public VistaInformacion(){
        
        //Información para centrar la ventana en la pantalla
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = pantalla.getScreenSize();
        int altPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocation((anchoPantalla/2)-250, (altPantalla/2)-100);
        setResizable(false);
        setTitle("Centros de Supercomputación - Información");
        
        setLayout(new AbsoluteLayout());
        
        
    }
    
}
