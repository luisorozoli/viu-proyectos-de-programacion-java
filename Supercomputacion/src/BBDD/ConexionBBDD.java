/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
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

    public Connection ConexionBBDD() {
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, password);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la clase de conexión a la base de datos " + e);
            e.printStackTrace();
        }
        
        return con;
    }

}
