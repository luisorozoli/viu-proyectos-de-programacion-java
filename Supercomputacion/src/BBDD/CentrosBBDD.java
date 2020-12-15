/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import Entidades.Centros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class CentrosBBDD {

    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private ConexionBBDD conexion = new ConexionBBDD();

    //Listar centros
    public ResultSet listarCentros() throws SQLException {

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
        return rs;
    }

    //Crear centro
    public int crearCentro(Centros centro) throws SQLException {

        String sSQL = "INSERT INTO supercomputacion.centros(identificador,capacidadprocesamiento,tamanomaxcola,administrador)VALUES(?,?,?,?)";
        Connection con = conexion.ConexionBBDD();
        pst = con.prepareStatement(sSQL);
        //Asignar parámetros
        pst.setString(1, centro.getsIdentificadorCentro());
        pst.setString(2, centro.getsCapacidadProcesamiento());
        pst.setString(3, centro.getsTamanoMaxCola());
        pst.setString(4, centro.getsAdministrador());

        int iFilasInsertadas = pst.executeUpdate();

        return iFilasInsertadas;

    }

    //Modificar centro
    public int modificarCentro(String sIdCentro, Centros centro) throws SQLException {
        String sSQL = "UPDATE centros set identificador = ?, capacidadprocesamiento = ?, tamanomaxcola = ?, administrador = ? where idcentro = ?";
        Connection con = conexion.ConexionBBDD();
        pst = con.prepareStatement(sSQL);

        //Asignar parámetros
        pst.setString(1, centro.getsIdentificadorCentro());
        pst.setString(2, centro.getsCapacidadProcesamiento());
        pst.setString(3, centro.getsTamanoMaxCola());
        pst.setString(4, centro.getsAdministrador());
        pst.setString(5, sIdCentro );

        int iFilasModificadas = pst.executeUpdate();

        return iFilasModificadas;
    }
    
    //Eliminar centro
    public int eliminarUsuario(String sIdCentro) throws SQLException {
        String sSQL = "DELETE FROM centros WHERE idcentro = ?";
        
        Connection con = conexion.ConexionBBDD();
        
        pst = con.prepareStatement(sSQL);
        int filasEliminadas = pst.executeUpdate();
        
        return filasEliminadas;
    }
}
