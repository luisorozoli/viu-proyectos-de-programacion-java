/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */

public class Ventana{
    //El constructor recibe el tipo de usuario que luego se usa para mostrar las opciones
     
    public Ventana(String sTipoUsuario){     
            
         if(sTipoUsuario.equals("administrador")){
             VtnAdministrador v = new VtnAdministrador();
         } else if(sTipoUsuario.equals("AdministradorCentro")){
             //Aqui se debe instanciar la ventana de AdministradorCentro
         } else {
             //Aqui se debe instanciar la ventana de Usuarios
         }       
         
     }
}

