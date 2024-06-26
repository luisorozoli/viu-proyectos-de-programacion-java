package BBDD;

import Entidades.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase utilizada para el acceso a los datos de la tabla <strong>usuarios</strong>
 * de la base de datos.
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class UsuariosBBDD {
    
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private final ConexionBBDD conexion = new ConexionBBDD();
    

    /**
     * Método utilizado para comprobar el login.<br>
     * Realiza una consulta de todos los <strong>usuarios</strong> según el 
     * <strong>identificador</strong> y <strong>clave</strong> recibidos como 
     * parámetros.
     * @param sIdentificador String con el identificador del usuario.
     * @param sClave Striong que contiene la clave de acceso del usuario.
     * @return rs ResultSet con los usuarios que cumplen con el criterio de
     * consulta.
     * @throws SQLException 
     */
    public ResultSet comprobarLogin(String sIdentificador, String sClave) throws SQLException {
        
        String sSQL = "SELECT * from usuarios where identificador  = ? and clave = ?";
        Connection con = conexion.ConexionBBDD();
        
        pst = con.prepareStatement(sSQL);
        
        //Asignar parametros
        pst.setString(1, sIdentificador);
        pst.setString(2, sClave);
        
        ResultSet rs = pst.executeQuery();
        pst.close();
        pst = null;
        conexion.desconectar();
        
        return rs;
    }

    
    /**
     * Método que realiza una consulta de todos los registros de la tabla de <strong>
     * usuarios</strong>.
     * @return rs ResultSet con todos los registros de la tabla de <strong>
     * usuarios</strong>.
     * @throws SQLException 
     */
    public ResultSet listarUsuarios() throws SQLException {
        
        String sSQL = "SELECT * FROM usuarios";
        
        try {
            
            Connection con = conexion.ConexionBBDD();
            
            st = con.createStatement();
            rs = st.executeQuery(sSQL);
            
        } catch(SQLException ex) {
        } finally {
            st.close();
            st = null;
            conexion.desconectar();
        }
        return rs;
    }
    
    
    /**
     * Método que realiza una consulta de los registros de la tabla de <strong>
     * usuarios</strong> que sean AdministradorCentro
     * @return lista ArrayList con todos los AdministradoreCentro de la tabla de <strong>
     * usuarios</strong>.
     * @throws SQLException 
     */
    public ArrayList<String> listarAdministradoresCentro() throws SQLException {
        
        ArrayList<String> lista = new ArrayList<>();
        
        String sSQL = "SELECT * FROM usuarios WHERE tipousuario = 'AdministradorCentro'";
        
        try {
            
            Connection con = conexion.ConexionBBDD();
            
            st = con.createStatement();
            rs = st.executeQuery(sSQL);
            
        } catch(SQLException ex) {
        } finally {
            st.close();
            st = null;
            conexion.desconectar();
        }
        
        try{
            while(rs.next()){
                lista.add(rs.getString(2));
            }
        } catch(SQLException e){
        }
        
        return lista;
    }
    
    
    /**
     * Método que inserta un nuevo registro en la tabla de <strong>usuarios</strong>
     * @param identif String con el <strong>identificador</strong> del usuario
     * @param clave String con la <strong>clave</strong> del usuario
     * @param tipo String con el tipo de usuario: <i>Administrador</i>,
     * <i>AdministradorCentro</i>, <i>Usuario</i>.
     * @return true o false dependiendo de si se pudo insertar el registro o no.
     */
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
    

    /**
     * Método que realiza el <i>update</i> de un nuevo registro en la tabla de
     * <strong>usuarios</strong>.
     * @param u Usuario que será modificado
     * @return true o false dependiendo de si se pudo o no realizar el <i>update</i>.
     * @throws SQLException 
     */
    public boolean modificarUsuario(Usuarios u) throws SQLException {

        try {
            String sql = "UPDATE usuarios SET identificador=?, clave=?, tipousuario=? WHERE idusuario =?";
            try (PreparedStatement ps = conexion.ConexionBBDD().prepareStatement(sql)) {
                ps.setString(1, u.getIdentificador());
                ps.setString(2, u.getClave());
                ps.setString(3, u.getTipoUsuario());
                ps.setInt(4, u.getUserId());
                ps.execute();
                ps.close();
            }
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }


    /**
     * Método que elimina un registro de la tabla de <strong>usuarios</strong>.
     * @param id userId del usuario que debe ser eliminado.
     * @return true o false dependiendo de si se pudo o no eliminar el usuario.
     * @throws SQLException 
     */
    public boolean eliminarUsuario(int id) throws SQLException {
        try{
            String sSQL = "DELETE FROM usuarios WHERE idusuario = ?";
            
            try (PreparedStatement ps = conexion.ConexionBBDD().prepareStatement(sSQL)) {
                ps.setInt(1, id);
                ps.execute();
                ps.close();
            }
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    /**
     * Método que lista a los usuarios diferentes a AdministradorCentro y Administrador
     * de la tabla <strong>usuarios</strong>.
     * @return true o false dependiendo de si se pudo listar.
     * @throws SQLException 
     */
    public ArrayList<String> listarPropietariosTrabajos(Usuarios u) throws SQLException {
        
        ArrayList<String> lista = new ArrayList<>();
        String sSQL = "";
        if ("administrador".equals(u.getTipoUsuario().toLowerCase())) {
            sSQL = "SELECT * from usuarios where tipousuario != 'AdministradorCentro' and tipousuario!='Administrador'";
            try {
            Connection con = conexion.ConexionBBDD();
            st = con.createStatement();
            rs = st.executeQuery(sSQL);
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            st.close();
            conexion.desconectar();
        }
        } else {
            sSQL = "SELECT * from usuarios where idusuario = ?";
            try {
                Connection con = conexion.ConexionBBDD();
                pst = con.prepareStatement(sSQL);
                pst.setInt(1, u.getUserId());
                rs = pst.executeQuery();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                pst.close();
                conexion.desconectar();
            }
            
        }
        
        try {
            while (rs.next()) {
                lista.add(rs.getString(2));
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
