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
    
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private ConexionBBDD conexion = new ConexionBBDD();
    
        //Comprobar Login
    
    public ResultSet comprobarLogin(String sIdentificador, String sClave) throws SQLException {
        
        String sSQL = "SELECT * from usuarios where identificador  = ? and clave = ?";
        Connection con = conexion.ConexionBBDD();
        
        pst = con.prepareStatement(sSQL);
        //Asignar parametros
        pst.setString(1, sIdentificador);
        pst.setString(2, sClave);
        
        ResultSet rs = pst.executeQuery();
        
        return rs;
    }

//Listar usuarios
    public ResultSet listarUsuarios() throws SQLException {
        
//        Statement st = null;
//        ResultSet rs = null;
        
        String sSQL = "SELECT * FROM usuarios";
        
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
    
//Crear usuario
    public int crearUsuario() {
        return 0;
    }
    
//Modificar usuario
    
    public int modificarUsuario(String identificador) throws SQLException {
        
//        PreparedStatement pst = null;
        
        
        String sSQL = "SELECT * FROM USUARIOS WHERE identificador = ?";
        
        Connection con = conexion.ConexionBBDD();
        
        pst = con.prepareStatement(sSQL);
        pst.setString(1, identificador);
       
        int filasModificadas = pst.executeUpdate();
        
        return filasModificadas;
    }

//Eliminar usuario
    
    public int eliminarUsuario(String identificador) throws SQLException {
//        PreparedStatement pst = null;
        
        String sSQL = "DELETE FROM USUARIOS WHERE IDENTIFICADOR = ?";
        
        Connection con = conexion.ConexionBBDD();
        
        pst = con.prepareStatement(sSQL);
        int filasEliminadas = pst.executeUpdate();
        
        return filasEliminadas;
    }
}
