/**
 * Implementa la interfaz de iCalculadora
 * Se encarga de decodificar los datos y hacer las operaciones correspondientes
 * @author Luis Santos
 * @version 1.0
*/

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Calculadora implements iCalculadora {
    @Override
    public int resta(int x, int y) {
        /**
         * Devuelve el resultado de una resta entre dos números
         * @param x El valor al que se le resta otro numero
         * @param y El valor que se resta
         * @return El resultado de la resta de X - Y
         */

        return (x - y);
    }

    @Override
    public int suma(int x, int y) {
        /**
         * Devuelve el resultado de una suma entre dos números
         * @param x El primero número a sumar
         * @param y El segundo número a sumar
         * @return El resultado de la suma de X + Y
         */

        return (x + y);
    }

    @Override
    public int multiplicacion(int x, int y) {
        /**
         * Devuelve el resultado de una multiplicación entre dos números
         * @param x El primer número a multiplicar
         * @param y El segundo número a multiplicar
         * @return El resultado de la multiplicación de X * Y
         */

        return (x * y);
    }

    @Override
    public int division(int x, int y) {
        /**
         * Devuelve el resultado de una división entre dos números
         * @param x el numero a dividir
         * @param y El divisor
         * @return El resultado de la división de X / Y
         */

        return (x / y);
    }

    @Override
    public String decode(String file) {
        /**
         * Devuelve un string con los resultados de las operaciones realizadas
         * El resultado de cada operación es indicado en una nueva linea
         * @param file El archivo a decidificar y operar
         * @return Los resultados de las operaciones realizadas
         */

        Scanner archivo = new Scanner(System.in);

        try { //Intenta accesar el archivo
            archivo = new Scanner(new File(file)); //Crea un scanner que lee el archivo
        } catch(FileNotFoundException e) {
            return("Archivo no encontrado");
        }    

        Stack<String> Lineas = new Stack<String>();
        archivo.useDelimiter("\n"); //Separa los datos en el line break

        while(archivo.hasNext()) { //Mientras hayan más lineas
            String x = archivo.next();  
            Lineas.push(x); //Añade el  archivo al stack
        }

        Stack<String> LineasInverso = new Stack<String>();

        while(!Lineas.empty()) { //Crea un stack inverso
            LineasInverso.push(Lineas.pop());
        }

        String result = "";

        int operacionesRealizadas = 0;

        while(LineasInverso.size() > 0) { //Se repite por cada linea de postfix
            String Linea = LineasInverso.pop(); //Obtiene la operación a realizar
            Stack<String> Datos = new Stack<String>();

            Scanner scanLinea = new Scanner(Linea);

            while(scanLinea.hasNext()) { //Por cada caracter
                String x = scanLinea.next(); 
                Datos.push(x); //Añade el  dato al stack
            }

            Stack<String> DatosInverso = new Stack<String>();

            while(!Datos.empty()) { //Invierte el stack
                DatosInverso.push(Datos.pop());
            }

            scanLinea.close(); //Cierra el scanner

            int res = operar(DatosInverso); //Obtiene el resultado de la operación del stack

            operacionesRealizadas++;
            result = result + "Resultado operación " + operacionesRealizadas + ": " + Integer.toString(res) + "\n"; //Añade el resultado al string
        }

        archivo.close(); //Cierra el scanner
        return "\n" + result; //Devuelve el resultado
    }

    @Override
    public int operar(iStack Datos) {
        /**
         * Devuelve el resultado de las operaciones realizadas con un stack
         * @param Datos Los datos a utilizar para las operaciones
         * @return El resultado de todas las operaciones realizadas
         */

        if (Datos.size() == 0) { //Verifica si el stack esta vacío
            return 0;
        }

        Stack<String> enOperacion = new Stack<String>(); 

        System.out.println("\nOperaciones:");

        while (!Datos.empty()) { //Mientras el stack no esta vacio
            String x = Datos.pop().toString(); //Último dato obtenido del stack
            int num1; //Primer número para la operación
            int num2; //Segundo número para la operación
            int res; //Resultado de la operación

            switch (x) {
                case "+": //Si es suma
                    num1 = Integer.parseInt(enOperacion.pop());
                    num2 = Integer.parseInt(enOperacion.pop());

                    res = num1 + num2;

                    System.out.println("Se realizó una suma de " + num1 + " + " + num2 + " con resultado " + res);

                    enOperacion.push(Integer.toString(res));

                    break;

                case "-": //Si es resta
                    num1 = Integer.parseInt(enOperacion.pop());
                    num2 = Integer.parseInt(enOperacion.pop());

                    res = num1 - num2;

                    System.out.println("Se realizó una resta de " + num1 + " - " + num2 + " con resultado " + res);

                    enOperacion.push(Integer.toString(res));

                    break;

                case "/": //Si es división
                    num1 = Integer.parseInt(enOperacion.pop());
                    num2 = Integer.parseInt(enOperacion.pop());

                    res = num1 / num2;

                    System.out.println("Se realizó una división de " + num1 + " / " + num2 + " con resultado " + res);

                    enOperacion.push(Integer.toString(res));

                    break;

                case "*": //Si es multiplicación
                    num1 = Integer.parseInt(enOperacion.pop());
                    num2 = Integer.parseInt(enOperacion.pop());

                    res = num1 * num2;

                    System.out.println("Se realizó una multiplicación de " + num1 + " x " + num2 + " con resultado " + res);

                    enOperacion.push(Integer.toString(res));

                    break;

                //Si es un número
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    enOperacion.push(x); //Añade el número al stack de operaciones
                    System.out.println("Se añadió un "+ x + " a la pila");
                    break;
            }
        }

        int result = Integer.parseInt(enOperacion.pop());
        return result; //Devuelve el resultado de la operación

    }
}
