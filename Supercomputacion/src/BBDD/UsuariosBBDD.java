/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import Entidades.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class UsuariosBBDD {
    
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
        
        String sSQL = "SELECT * FROM usuarios";
        
        try {
            
            Connection con = conexion.ConexionBBDD();
            
            st = con.createStatement();
            rs = st.executeQuery(sSQL);
            
        } catch(SQLException ex) {
        } finally {
            st.close();
        }
        return rs;
    }
    
    //Crear usuario
    public boolean crearUsuario(String identif, String clave, String tipo) {
        try{
           
            String sSQL = "INSERT INTO supercomputacion.usuarios(identificador,clave,tipousuario)VALUES(?,?,?)";
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);
            
            pst.setString(1, identif);
            pst.setString(2, clave);
            pst.setString(3, tipo);
            
            pst.execute();
            pst.close();
            pst = null;
            
            conexion.desconectar();
            
            return true;           
        } catch (SQLException ex){
            return false;
        }
    }
    
    //Modificar usuario
    public boolean modificarUsuario(Usuarios u) throws SQLException {

        try {
            String sql = "UPDATE usuarios SET identificador=?, clave=?, tipousuario=? WHERE idusuario =?";
            PreparedStatement ps = conexion.ConexionBBDD().prepareStatement(sql);
            ps.setString(1, u.getIdentificador());
            ps.setString(2, u.getClave());
            ps.setString(3, u.getTipoUsuario());
            ps.setInt(4, u.getUserId());
            ps.execute();
            ps.close();
            ps = null;
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    //Eliminar usuario
    public boolean eliminarUsuario(int id) throws SQLException {
        try{
            String sSQL = "DELETE FROM usuarios WHERE idusuario = ?";
            
            PreparedStatement ps = conexion.ConexionBBDD().prepareStatement(sSQL);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            ps = null;
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
