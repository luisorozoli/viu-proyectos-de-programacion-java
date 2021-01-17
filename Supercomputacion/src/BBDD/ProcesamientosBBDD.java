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

    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private final ConexionBBDD conexion = new ConexionBBDD();

    public ResultSet TrabajosEnCola(int idC) throws SQLException {

        String sSQL = "SELECT * from procesamiento where idcentro = ? and estadotrabajo != 'Finalizado'";

        try {
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);

            pst.setInt(1, idC);
            rs = pst.executeQuery();
            
        } catch (SQLException ex) {
        } finally {
            pst.close();
            conexion.desconectar();
        }
        return rs;

    }

    public boolean AsignarProcesamiento(int identificadorTrabajo, int identificadorCentro, int operaciones) throws SQLException {

        try {

            String sSQL = "INSERT INTO supercomputacion.procesamiento(idtrabajo, idcentro, estadotrabajo, operacionesrestantes)VALUES(?,?,'Asignado',?)";
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);
            //Asignar parámetros
            pst.setInt(1, identificadorTrabajo);
            pst.setInt(2, identificadorCentro);
            pst.setInt(3, operaciones);

            int iFilasInsertadas = pst.executeUpdate();
            
            pst.close();
            conexion.desconectar();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean actualizarOperRestantes(int idProcesamiento, int iOperRestantes, String sEstadoTrabajo) throws SQLException {

        try {

            String sSQL = "update supercomputacion.procesamiento set operacionesrestantes=?, estadotrabajo=? where idprocesamiento = ?";
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);
            //Asignar parámetros
            pst.setInt(1, iOperRestantes);
            pst.setString(2, sEstadoTrabajo);
            pst.setInt(3, idProcesamiento);

            int iFilasInsertadas = pst.executeUpdate();
            
            pst.close();
            conexion.desconectar();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ResultSet listaHistoricoTrabajos() {
        ResultSet rsHistTrabajos = null;
        String sSQL = "select tra.idtrabajos, tra.identificador, tra.propietario, tra.centroTrabajo, proc.estadotrabajo\n"
                + "from procesamiento proc\n"
                + "inner join trabajos tra on proc.idtrabajo = tra.idtrabajos and proc.estadotrabajo = 'Finalizado'";
        Connection con = conexion.ConexionBBDD();
        try {
            st = con.createStatement();
            rsHistTrabajos = st.executeQuery(sSQL);
            
            st.close();
            conexion.desconectar();
            
        } catch (SQLException ex) {
        }
        return rsHistTrabajos;
    }

}
