/*******************************************************
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos
* Profesor: Moises Gonzales
* Driver.java
* @author Diego Cordova
* Ultima modificacion: 2021-02-3
*
* Clase Driver
* Driver Program
********************************************************/

import java.util.Scanner;

public class Driver {

    private static calculadora calc = new Calc_I();
    private static Scanner scan = new Scanner(System.in);

    
    /** 
     * simplification of System.out.println
     * @param o
     */
    private static void print(Object o){
        System.out.println(o);
    }

    
    /** 
     * input 
     * @return String
     */
    private static String input(){
        print("\nIngrese el nombre del archivo a leer sin postfijo de tipo \".txt\"\n");
        return scan.next();
    }

    
    /** 
     * @param size
     * @return boolean
     */
    private static boolean input(int size){

        print("\nEscoja una opcion:\n1. leer otro archivo\n2. Salir\n");
        while (true) { //Se repite hasta que se logre el return

            try { 
              int opcion = Integer.parseInt(scan.next());
              if (opcion > 0 && opcion < (size + 1)) {
                if (opcion == 1){
                    return true;

                } else{
                    return false;
                }

              } else {
                throw new NumberFormatException();
              }

            } catch (NumberFormatException e) {
                print("\nERROR: Escoja una opcion valida\n");
            }
        }
    }

    
    /** 
     * main method
     * @param args
     */
    public static void main (String[] args){

        print("Bienvenido a la caluladora Posfix UVG!!\n\n");
        boolean menu = true;
        
        while (menu){
            String file = input();
            print("\nLEYENDO EL ARCHIVO: \"" + file + "\n");
            print(calc.decode(file));
            menu = input(2);
        }
        
        print("\n\nGracias por utilizar la calculadora posfix UVG\n\n");
    }
}