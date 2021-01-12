/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gonzalodiaz
 */
public class ProcesamientosBBDD {
    
    private final Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private final ConexionBBDD conexion = new ConexionBBDD();
    
    public ResultSet TrabajosEnCola (int idC) throws SQLException{
        
        String sSQL = "SELECT * from procesamiento where idcentro = ? and estadotrabajo != 'Finalizado'";
        
        try {
                Connection con = conexion.ConexionBBDD();
                pst = con.prepareStatement(sSQL);

                pst.setInt(1, idC);
                rs = pst.executeQuery();
                conexion.desconectar();
            } catch (SQLException ex) {
            } finally {
                pst.close();
            }
            return rs;
        
    }
    
    public boolean AsignarProcesamiento(int identificadorTrabajo, int identificadorCentro,  int operaciones) throws SQLException {
        
        try {
        
            String sSQL = "INSERT INTO supercomputacion.procesamiento(idtrabajo, idcentro, estadotrabajo, operacionesrestantes)VALUES(?,?,'Asignado',?)";
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);
            //Asignar parámetros
            pst.setInt(1, identificadorTrabajo);
            pst.setInt(2, identificadorCentro);
            pst.setInt(3, operaciones);

            int iFilasInsertadas = pst.executeUpdate();
        
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
