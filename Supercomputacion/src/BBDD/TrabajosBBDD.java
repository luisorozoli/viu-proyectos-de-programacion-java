/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import Entidades.Trabajos;
import Entidades.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase utilizada para el acceso a los datos de la tabla
 * <strong>trabajos</strong>
 * de la base de datos.
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class TrabajosBBDD {

    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private ConexionBBDD conexion = new ConexionBBDD();

    /**
     * Método que realiza una consulta de todos los registros de la tabla de
     * <strong>
     * trabajos</strong>.
     *
     * @return rs ResultSet con todos los registros de la tabla
     * <strong>trabajos</strong>.
     * @throws SQLException
     */
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

    public ResultSet listarTrabajosSinAsignar() throws SQLException {

        String sSQL = "SELECT * FROM trabajos where centrotrabajo = \"\" order by idtrabajos";

        try {
            Connection con = conexion.ConexionBBDD();

            st = con.createStatement();
            rs = st.executeQuery(sSQL);

        } catch (SQLException ex) {
            System.out.println("listarTrabajosSinAsignar");
            ex.printStackTrace();
        } finally {
            st.close();
        }
        return rs;
    }

    /**
     * Método que inserta un nuevo registro en la tabla de
     * <strong>trabajos</strong>
     *
     * @param sIdentificadorTrabajo String con el <strong>identificador</strong>
     * del trabajo
     * @param iCantidad int con la <strong>cantidad</strong> de operaciones del
     * trabajo
     * @param sPropietario String con el <strong>propietario</strong> del
     * trabajo
     * @return true o false dependiendo de si se pudo insertar el registro o no.
     * @throws SQLException
     */
    public boolean crearTrabajo(String sIdentificadorTrabajo, int iCantidad, String sPropietario) throws SQLException {

        try {

            String sSQL = "INSERT INTO supercomputacion.trabajos(identificador, cantidadoperaciones, propietario)VALUES(?,?,?)";
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);
            //Asignar parámetros
            pst.setString(1, sIdentificadorTrabajo);
            pst.setInt(2, iCantidad);
            pst.setString(3, sPropietario);

            int iFilasInsertadas = pst.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Método que realiza el <i>update</i> de un registro de la tabla
     * <strong>trabajos</strong>
     *
     * @param trabajo Trabajo que será modificado
     * @return true o false dependiendo de si se p udo o no realizar el
     * <i>update</i>.
     * @throws SQLException
     */
    public boolean modificarTrabajo(Trabajos trabajo) throws SQLException {

        try {

            System.out.println("idtrabajo en modificarTrabajo: " + trabajo.getiIdTrabajo());
            System.out.println("identificador: " + trabajo.getsIdentificadorTrab());

            String sSQL = "UPDATE trabajos set identificador = ?, cantidadoperaciones = ?, propietario = ?, centroTrabajo = ? where idTrabajos = ?";
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);

            //Asignar parámetros
            pst.setString(1, trabajo.getsIdentificadorTrab());
            pst.setString(2, trabajo.getsCantidadOperaciones());
            pst.setString(3, trabajo.getsPropietario());
            pst.setString(4, trabajo.getsCentroTrabajo());
            pst.setInt(5, trabajo.getiIdTrabajo());

            int iFilasModificadas = pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean modificarCentroTrabajo(int idT, String identificadorCentro) throws SQLException {

        try {

            String sSQL = "UPDATE trabajos set centroTrabajo = ? where idTrabajos = ?";
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);

            //Asignar parámetros
            pst.setString(1, identificadorCentro);
            pst.setInt(2, idT);

            int iFilasModificadas = pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Método que elimina un registro de la tabla de <strong>trabajos</strong>.
     *
     * @param trabajoId del trabajo que debe ser eliminado.
     * @return true o false dependiendo de si se pudo o no eliminar el usuario.
     * @throws SQLException
     */
    public boolean eliminarTrabajo(int trabajoId) throws SQLException {

        try {

            String sSQL = "DELETE FROM trabajos WHERE idtrabajos = ?";
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);
            pst.setInt(1, trabajoId);
            int filasEliminadas = pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Método que muestra los trabajos de un usuario propietario
     *
     * @param us Usuario propietario de los trabajos a mostrar
     * @return rs ResultSet de la lista de trabajos de un usuario de los cuales
     * es propietario
     * @throws SQLException
     */
    public ResultSet listarTrabajosUsuario(Usuarios us) throws SQLException {

        int iIdUsuario = us.getUserId();
        String sTipoUsuario = us.getTipoUsuario();
        System.out.println("iIdUsuario: " + iIdUsuario);
        System.out.println("sTipoUsuario: " + sTipoUsuario);

        String sSQL = "select * from trabajos tra inner join usuarios user on user.identificador = tra.propietario and user.idusuario = ?";
        try {
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);

            pst.setInt(1, iIdUsuario);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     * Método que muestra los trabajos de un centro.
     *
     * @param us Usuario AdministradorCentro
     * @return rs ResultSet con todos los trabajos del centro al que pertenece
     * el AdministradorCentro
     * @throws SQLException
     */
    public ResultSet listarTrabajosCentro(Usuarios us) throws SQLException {

        try {
            String sSQL = "select tra.* from trabajos tra  inner join centros cen on cen.identificador = tra.centrotrabajo inner join usuarios usu on usu.identificador = cen.administrador and usu.idusuario = ?";
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);
            pst.setInt(1, us.getUserId());
            rs = pst.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getErrorCode());
        } finally {
            pst.close();
        }

        return rs;

    }

    public int OperacionTrabajo(int idTrabajo) throws SQLException {

        String sSQL = "select * from trabajos where idtrabajos = ?";
        try {
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);

            pst.setInt(1, idTrabajo);
            rs = pst.executeQuery();

        } catch (SQLException ex) {
        }
        return rs.getInt(3);
    }
    
    
}
