import Menus.*;
import java.sql.ResultSet;
import BBDD.*;
import java.sql.SQLException;

/**
 * Clase principal que inicia la ejecución del <strong>Sistema de Gestión de
 * Supercomputadores</strong>.
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class supercomputacion{

    public static void main(String[] args) throws SQLException {
               
        MenuLogin menuLogin = new MenuLogin();
        menuLogin.MenuLogin();
               
        ProcesamientosBBDD proc = new ProcesamientosBBDD();
        ResultSet rs = proc.TrabajosEnCola(4);
        
        while(rs.next()){
            System.out.println(rs.getInt(2)+"Hola");
        }
    }
}