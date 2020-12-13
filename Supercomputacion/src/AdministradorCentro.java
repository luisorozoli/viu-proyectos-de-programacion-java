
import Entidades.Usuarios;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class AdministradorCentro extends Usuarios {
    
    private String adminCentroId;
        
    public AdministradorCentro(int id, String identif, String pass, String tipo_usuario) {
        super(id, identif, pass, tipo_usuario);
    }
    
    public void setAdminCentroId(String centroId){
        this.adminCentroId = centroId;
    }
    
    public String getAdminCentroId(){
        return adminCentroId;
    }
}
