/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
import java.util.InputMismatchException;
import java.util.Scanner;
 
public class supercomputacion {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
 
            System.out.println("1. Usuarios");
            System.out.println("2. Centros");
            System.out.println("3. Trabajos");
            System.out.println("4. Asignar Trabajos");
            System.out.println("5. Información");
            System.out.println("6. Salir");
 
            try {
 
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion 1 - USUARIOS");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2 - CENTROS");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3 - TRABAJOS");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion 4 - ASIGNAR TRABAJOS");
                        break;
                    case 5:
                        System.out.println("Has seleccionado la opcion 5 - INFORMACIÓN");
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 6");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
 
    }
 
}
