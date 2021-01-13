package Menus;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Crea la ventana de Información que se abre al hacer clic en el boton
 * Información de la ventana principal
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class VistaInformacion extends JFrame {

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

        JButton btnSimulacion;

        //Botones
        btnSimulacion = new JButton("Simular Operaciones");

        this.getContentPane().add(btnSimulacion, new AbsoluteConstraints(150, 60, 200, 20));

        btnSimulacion.addActionListener((ActionListener) new BotonSimularListener());
    }

    class BotonSimularListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            VistaSimulacion vistaSimulacion = new VistaSimulacion();
            
            vistaSimulacion.VistaSimulacion();

        }

    }

}
