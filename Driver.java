/*******************************************************
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos
* Profesor: Moises Gonzales
* Driver.java
* Autor: Diego Cordova
* Ultima modificacion: 2021-02-3
*
* Clase Driver
* Driver Program
********************************************************/

import java.util.Scanner;

public class Driver {

    private static calculadora calc = new Calc_I();
    private static Scanner scan = new Scanner(System.in);

    private static void print(Object o){
        System.out.println(o);
    }

    private static String input(){

        print("Insgrese el nombre del archivo a leer sin postfijo de tipo\n");
        
        return scan.next();
    }

    public static void main (String[] args){

        print("Bienvenido a la caluladora Posfix UVG!!\n\n");
        String file = input();
        print("Leyendo archivo \"" + file + ".txt\"\n");
        print(calc.decode(file));
        print("Gracias por utilizar la calculadora posfix UVG\n\n");
    }
}