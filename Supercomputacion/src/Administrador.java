
import Entidades.Usuarios;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class Administrador extends AdministradorCentro {
    
    private String adminId;
    
    public Administrador(String id, String pass, String tipo_usuario) {
        super(id, pass, tipo_usuario);
    }
    
    public void setAdminId(String id){
        this.adminId = id;
    }
    
    public String getAdminId(){
        return adminId;
    }
    
}
