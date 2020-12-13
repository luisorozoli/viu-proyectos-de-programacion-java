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
    
    private int userId;
    private String identificador;
    private String clave;
    private String tipo;
    
    public Usuarios(){
        
    }
    
    public Usuarios(int id, String identif, String pass, String tipo_usuario){
        userId = id;
        identificador = identif;
        clave = pass;
        tipo = tipo_usuario;        
    }
        
    public void setUserId(int id){
        this.userId = id;
    }
   
    public int getUserId(){
        return userId;
    }
    
    public void setIdentificafor(String identif){
        this.identificador = identif;
    }
    
    public String getIdentificador(){
        return identificador;
    }
    
    public void setClave(String pass){
        this.clave = pass;
    }
    
    public String getClave(){
        return clave;
    }
    
    public void setTipoUsuario(String tipo){
        this.tipo = tipo;
    }
    
    public String getTipoUsuario(){
        return tipo;
    }

}
