package BBDD;

import Entidades.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase utilizada para el acceso a los datos de la tabla <strong>centros</strong>
 * de la base de datos.
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class CentrosBBDD {

    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private final ConexionBBDD conexion = new ConexionBBDD();

    
    /**
     * Método que realiza una consulta de todos los registros de la tabla de <strong>
     * centros</strong>.
     * @return ResultSet con todos los registros de la tabla de <strong>
     * centros</strong>.
     * @throws SQLException
     */
    public ResultSet listarCentros() throws SQLException {

        String sSQL = "SELECT * FROM centros";

        try {
            Connection con = conexion.ConexionBBDD();

            st = con.createStatement();
            rs = st.executeQuery(sSQL);

            conexion.desconectar();

        } catch (SQLException ex) {
        } finally {
            st.close();
        }
        return rs;
    }

    
    /**
     * Método se utiliza para obtener un listado de centros en base al usuario que realiza la consulta
     * @param u Tiene la información del usuario que está realizando la consulta
     * @return ResultSet rs.<br>
     * Si el usuario recibido por parámetro es Administrador, devuelve todos los centros.<br>
     * Si el usuario es AdministradorCentro, devuelve solo los centros que administra el usuario
     * @throws SQLException
     */
    public ResultSet listarCentrosAdmin(Usuarios u) throws SQLException {

        Usuarios userAdmin = new Usuarios();
        userAdmin = u;

        if(userAdmin.getTipoUsuario().equals("Administrador")){

            String sSQL = "SELECT * FROM centros";
            try {
                Connection con = conexion.ConexionBBDD();

                st = con.createStatement();
                rs = st.executeQuery(sSQL);

                conexion.desconectar();
            } catch (SQLException ex) {
            } finally {
                st.close();
            }
            return rs;
        } else {

            String sSQL = "SELECT * from centros where administrador = ?";
            try {
                Connection con = conexion.ConexionBBDD();
                pst = con.prepareStatement(sSQL);

                pst.setString(1, userAdmin.getIdentificador());
                rs = pst.executeQuery();
                conexion.desconectar();
            } catch (SQLException ex) {
            } finally {
                pst.close();
            }
            return rs;
        }
    }

    
    /**
     * Método que inserta un nuevo registro en la tabla de <strong>centros</strong>
     * @param identif String con el <strong>identificador</strong> del centro
     * @param cap int con la <strong>capacidadprocesamiento</strong> del centro
     * @param tamCola int con la <strong>tamanomaxcola</strong> del centro
     * @param admin String con el <strong>administrador</strong> del centro
     * @return true o false dependiendo de si se pudo insertar el registro o no.
     * @throws SQLException
     */
    public boolean crearCentro(String identif, int cap, int tamCola, String admin) throws SQLException {

        try{

            String sSQL = "INSERT INTO supercomputacion.centros(identificador,capacidadprocesamiento,tamanomaxcola,administrador)VALUES(?,?,?,?)";
            Connection con = conexion.ConexionBBDD();
            pst = con.prepareStatement(sSQL);

            pst.setString(1, identif);
            pst.setInt(2, cap);
            pst.setInt(3, tamCola);
            pst.setString(4, admin);

            pst.execute();
            pst.close();
            pst = null;

            conexion.desconectar();

            return true;
        } catch (SQLException ex){
            return false;
        }

    }

    
    /**
     * Método que realiza el <i>update</i> de un nuevo registro en la tabla de
     * <strong>centros</strong>.
     * @param center Centro que será modificado
     * @return true o false dependiendo de si se pudo o no realizar el <i>update</i>.
     * @throws SQLException
     */
    public boolean modificarCentro(Centros center) throws SQLException {

        try {
            String sql = "UPDATE centros SET identificador=?, capacidadprocesamiento=?, tamanomaxcola=?, administrador=? WHERE idcentro =?";
            try (PreparedStatement ps = conexion.ConexionBBDD().prepareStatement(sql)) {

                ps.setString(1, center.getsIdentificadorCentro());
                ps.setInt(2, center.getCapacidadProcesamiento());
                ps.setInt(3, center.getTamanoMaxCola());
                ps.setString(4, center.getsAdministrador());
                ps.setInt(5, center.getIdCentro());
                ps.execute();
            }
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    
    /**
     * Método que elimina un registro de la tabla de <strong>centros</strong>.
     * @param id idcentro del centro que debe ser eliminado.
     * @return true o false dependiendo de si se pudo o no eliminar el centro.
     * @throws SQLException
     */
    public boolean eliminarCentro(int id) throws SQLException {

        try{
            String sSQL = "DELETE FROM centros WHERE idcentro = ?";

            try (PreparedStatement ps = conexion.ConexionBBDD().prepareStatement(sSQL)) {
                ps.setInt(1, id);
                ps.execute();
            }
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            return false;
        }

}
    
        public ArrayList<String> listarCentrosTrabajos() throws SQLException {
        ArrayList<String> lista = new ArrayList<>();

        String sSQL = "SELECT * FROM centros";

        try {
            Connection con = conexion.ConexionBBDD();

            st = con.createStatement();
            rs = st.executeQuery(sSQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            st.close();
        }

        try {
            while (rs.next()) {
                lista.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
