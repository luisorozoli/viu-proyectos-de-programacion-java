/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import Entidades.Trabajos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class TrabajosBBDD {

    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private ConexionBBDD conexion = new ConexionBBDD();

    //Listar trabajos (Administrador)
    public ResultSet listarTrabajos() throws SQLException {

        String sSQL = "SELECT * FROM trabajos";

        try {
            Connection con = conexion.ConexionBBDD();

            st = con.createStatement();
            rs = st.executeQuery(sSQL);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            st.close();
        }
        return rs;
    }

    //Crear trabajo
    public int crearTrabajo(Trabajos trabajo) throws SQLException {

        String sSQL = "INSERT INTO supercomputacion.trabajos(identificador,cantidadoperaciones,propietario)VALUES(?,?,?)";
        Connection con = conexion.ConexionBBDD();
        pst = con.prepareStatement(sSQL);
        //Asignar parámetros
        pst.setString(1, trabajo.getsIdentificadorTrab());
        pst.setString(2, trabajo.getsCantidadOperaciones());
        pst.setString(3, trabajo.getsPropietario());

        int iFilasInsertadas = pst.executeUpdate();

        return iFilasInsertadas;

    }

    //Modificar trabajo
    public int modificarTrabajo(String sIdTrabajo, Trabajos trabajo) throws SQLException {
        String sSQL = "UPDATE trabajos set identificador = ?, cantidadoperaciones = ?, propietario = ? where idTrabajos = ?";
        Connection con = conexion.ConexionBBDD();
        pst = con.prepareStatement(sSQL);

        //Asignar parámetros
        pst.setString(1, trabajo.getsIdentificadorTrab());
        pst.setString(2, trabajo.getsCantidadOperaciones());
        pst.setString(3, trabajo.getsPropietario());
        pst.setString(4, sIdTrabajo);

        int iFilasModificadas = pst.executeUpdate();

        return iFilasModificadas;
    }

    //Eliminar trabajo
    public int eliminarTrabajo(String sIdTrabajo) throws SQLException {
        String sSQL = "DELETE FROM trabajos WHERE idtrabajos = ?";

        Connection con = conexion.ConexionBBDD();

        pst = con.prepareStatement(sSQL);
        int filasEliminadas = pst.executeUpdate();

        return filasEliminadas;
    }

    //Mostrar los trabajos de un usuario
    public ResultSet listarTrabajosUsuario(int sIdUsuario) throws SQLException {
        String sSQL = "select * from trabajos where idusuario = ?";
        try {
            Connection con = conexion.ConexionBBDD();

            st = con.createStatement();
            rs = st.executeQuery(sSQL);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            st.close();
        }

        return rs;
    }
    
    public ResultSet listarTrabajosCentro(String sIdUsuario) throws SQLException {
        String sSQL ="select tra.*, cen.*, usu.* from trabajos tra "
                + "inner join centros cen on cen.idcentro = tra.idcentro\n" +
        "inner join usuarios usu on usu.identificador = cen.administrador and usu.idusuario = ?";
        
        try {
            Connection con = conexion.ConexionBBDD();

            st = con.createStatement();
            rs = st.executeQuery(sSQL);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            st.close();
        }

        return rs;
        
    }
}
