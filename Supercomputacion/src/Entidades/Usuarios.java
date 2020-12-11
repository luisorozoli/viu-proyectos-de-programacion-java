package Entidades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class Usuarios {
    
   public enum TipoUsuario{
        administrador,
        administradorCentro,
        usuario        
    }
    
    private String userId;
    private String clave;
    private TipoUsuario tipo;
    
    public Usuarios(String id, String pass, TipoUsuario tipo_usuario){
        userId = id;
        clave = pass;
        tipo = tipo_usuario;        
    }
        
    public void setUserId(String id){
        this.userId = id;
    }
    
    public String getUserId(){
        return userId;
    }
    
    public void setClave(String pass){
        this.clave = pass;
    }
    
    public String getClave(){
        return clave;
    }
    
    public void setTipoUsuario(TipoUsuario tipo){
        this.tipo = tipo;
    }
    
    public TipoUsuario getTipoUsuario(){
        return tipo;
    }
    
    public boolean login(String user, String pass){
        return this.userId.equals(user) && this.clave.equals(pass);
    }
}
