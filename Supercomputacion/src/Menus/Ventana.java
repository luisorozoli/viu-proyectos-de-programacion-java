/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;


public class Ventana{
    //Cambiar el main por public Ventana(String sTipoUsuario) - O sea, crear el constructor
    //El constructor recibe el tipo de usuario que luego se usa para mostrar las opciones
    // public static void main(String[] args){
     
    public Ventana(String sTipoUsuario){     
    //     String sTipoUsuario = "administrador"; //Esta declaración se debe eliminar
         
         if(sTipoUsuario.equals("administrador")){
             VtnAdministrador v = new VtnAdministrador();
         } else if(sTipoUsuario.equals("AdministradorCentro")){
             //Aqui se debe instanciar la ventana de AdministradorCentro
         } else {
             //Aqui se debe instanciar la ventana de Usuarios
         }       
         
     }
}

