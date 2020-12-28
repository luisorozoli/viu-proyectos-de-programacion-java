package Menus;

import BBDD.TrabajosBBDD;
import BBDD.UsuariosBBDD;
import Entidades.*;
import java.awt.Dimension;
import java.awt.Toolkit;
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
    JLabel lblBienvenida, lblId, lblCantidad, lblAdministrador;
    JTextField txtIdentificador;
    JSpinner spnCantidad;
    JComboBox cboAdministrador;
    JScrollPane scroll;
    DefaultTableModel model;
    JTable tblDatos;
    JButton btnAgregar, btnEliminar, btnModificar, btnLimpiar;
    int trabajoId;

    Usuarios userp = new Usuarios();
    Trabajos trabajo = new Trabajos();

    UsuariosBBDD daoUsuarios = new UsuariosBBDD();
    TrabajosBBDD daoTrabajos = new TrabajosBBDD();

    public VistaTrabajos(Usuarios u) {

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
        lblAdministrador = new JLabel("Usuario Prop.:");
        lblAdministrador.setHorizontalAlignment(RIGHT);
        this.getContentPane().add(lblAdministrador, new AbsoluteConstraints(10, 120, 100, 20));

        //CUADROS DE TEXTO
        txtIdentificador = new JTextField();
        this.getContentPane().add(txtIdentificador, new AbsoluteConstraints(120, 60, 200, 20));
        spnCantidad = new JSpinner();
        this.getContentPane().add(spnCantidad, new AbsoluteConstraints(120, 90, 200, 20));

        //Se crean las opciones que tendrá el combo de usuario Administrador de centro
        ArrayList<String> listaAdministradores = new ArrayList<>();

        /**
         * *******************
         */
        //MIRAR
        /**
         * *******************
         */
        //Se crean los botones
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar");
        btnLimpiar = new JButton("Limpiar");

        //Si el usuario es Administrador se muestran todos los componentes
        //Si el usuario es AdministradorCentro se restringe su funcionalidad
        if (userp.getTipoUsuario().equals("Administrador")) {
            this.getContentPane().add(btnAgregar, new AbsoluteConstraints(360, 60, 100, 20));
            this.getContentPane().add(btnEliminar, new AbsoluteConstraints(360, 90, 100, 20));
            btnEliminar.setEnabled(false);
            this.getContentPane().add(btnModificar, new AbsoluteConstraints(360, 120, 100, 20));
            btnModificar.setEnabled(false);
            this.getContentPane().add(btnLimpiar, new AbsoluteConstraints(360, 150, 100, 20));
        } else {
            this.getContentPane().add(btnModificar, new AbsoluteConstraints(360, 60, 100, 20));
            btnModificar.setEnabled(false);
            this.getContentPane().add(btnLimpiar, new AbsoluteConstraints(360, 90, 100, 20));
            txtIdentificador.setEnabled(false);
            cboAdministrador.setVisible(false);
            lblAdministrador.setVisible(false);
        }
        
        //Se agregan los Listeners a los botones y a la tabla
    }

}
