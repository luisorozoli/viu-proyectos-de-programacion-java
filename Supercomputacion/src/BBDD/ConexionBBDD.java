package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilizada para conectarse y desconectarse de la base de datos de AWS.
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class ConexionBBDD {

    public String driver = "org.mariadb.jdbc.Driver";
    public String basedatos = "supercomputacion";
    public String host = "bbdd-21giin.city7zvgthab.us-east-1.rds.amazonaws.com";
    public String puerto = "3306";
    public String url = "jdbc:mysql://" + host + ":" + puerto + "/" + basedatos;
    public String usuario = "admin";
    public String password = "0Xzrc7&q77NT";
    Connection con = null;
    
    
    /**
     * Método utilizado para crear una conexión con la base de datos
     * @return con Connection. Es la conexión con la base de datos
     */
    public Connection ConexionBBDD() {
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, password);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la clase de conexión a la base de datos " + e);
            e.printStackTrace();
        }
        return con;
    }
    
    
    /**
     * Método que realiza el cierre y desconexión de la base de datos
     */
    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
        }
    }
}
