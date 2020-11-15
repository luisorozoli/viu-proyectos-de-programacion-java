/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class AdministradorCentro extends Usuario {
    
    private String adminCentroId;
        
    public AdministradorCentro(String id, String pass, TipoUsuario tipo_usuario) {
        super(id, pass, tipo_usuario);
    }
    
    public void setAdminCentroId(String centroId){
        this.adminCentroId = centroId;
    }
    
    public String getAdminCentroId(){
        return adminCentroId;
    }
}
