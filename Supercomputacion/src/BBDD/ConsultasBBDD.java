/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class ConsultasBBDD {

    //listar usuarios
    public ResultSet listarUsuarios() throws SQLException {
        
        Statement st = null;
        ResultSet rs = null;
        
        ConexionBBDD conexion = new ConexionBBDD();
        String sSQL = "SELECT * FROM USUARIOS";
        
        try {
            
            Connection con = conexion.ConexionBBDD();
            
            st = con.createStatement();
            rs = st.executeQuery(sSQL);
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            st.close();
            
        }
        return rs;
    }
    
    //Modificar usuario
    
    public int modificarUsuario(String identificador) throws SQLException {
        
        PreparedStatement pst = null;
        
        
        ConexionBBDD conexion = new ConexionBBDD();
        String sSQL = "SELECT * FROM USUARIOS WHERE identificador = ?";
        
        Connection con = conexion.ConexionBBDD();
        
        pst = con.prepareStatement(sSQL);
        pst.setString(1, identificador);
       
        int filasModificadas = pst.executeUpdate();
        
                
        
        
        return filasModificadas;
    }
}
