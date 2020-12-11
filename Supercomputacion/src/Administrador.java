
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
    
    public Administrador(String id, String pass, TipoUsuario tipo_usuario) {
        super(id, pass, tipo_usuario);
    }
    
    public void setAdminId(String id){
        this.adminId = id;
    }
    
    public String getAdminId(){
        return adminId;
    }
    
    public int crearUsuario(String id, String pass, TipoUsuario tipo_usuario){
        
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("bbdd-21giin.city7zvgthab.us-east-1.rds.amazonaws.com","admin","0Xzrc7&q77NT");
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate("INSERT INTO Usuarios (userId, clave, tipo) VALUES (id, pass, tipo_usuario)");
            if (rs != 1){
                // El usuario no se pudo crear, ya existe
                return 0;
            }
            conn.close();
        }
        
        catch (SQLException e){
            System.out.println(e);
        }
        
        Usuarios user = new Usuarios (id,pass,tipo_usuario);  // A esta instancia habría que ponerla en algún lado
        
        // El usuario se creó
        return 1;
    }
}
