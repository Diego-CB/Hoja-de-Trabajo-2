/*******************************************************
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos
* Profesor: Moises Gonzales
* CalcImplementation.java
* Autor: Diego Cordova
* Ultima modificacion: 2021-02-2
*
* Interfaz Calculadora
* Interface de la calculadora
********************************************************/

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Calc_I implements calculadora{

    private Stack<String> stack;
    private Stack<String> aux; // Arraylist used as stack
    private Stack<String> op; // Arraylist used as stack
    private String operation_list;

    /**
    *constructor method it inizialize the atributes 
    */
    public Calc_I(){
        this.stack = new Stack_I();
        this.aux = new Stack_I();
        this.op = new Stack_I();
    }

    /**
    * @return the add of the parametters
    * @param x number for adding
    * @param y number for adding
    */
    @Override
    public int suma(int x, int y) {
        return (x + y);
    }

    /**
    * @return the substractions of the parametters
    * @param x number for adding
    * @param y number for adding
    */
    @Override
    public int resta(int x, int y) {
        return (x - y);
    }

    /**
    * @return the multiplication of the parametters
    * @param x number for adding
    * @param y number for adding
    */
    @Override
    public int multiplicacion(int x, int y) {
        return (x * y);
    }

    /**
    * @return the divition of the parametters
    * @param x number for adding
    * @param y number for adding
    */
    @Override
    public int division(int x, int y) {
        return (x / y);
    }

    /**
    * @return the final result of the stacked operation 
    * @param stack stack wich contains the numbers and operations  
    */
    @Override
    public int operar(Stack x) {
        
        this.operation_list += "\nENTRADA\t\tOPERACION\t\t\t\t\tRESULTADOS\n";
        String temp_s = "";
        int temp_i,a,b;

        while(!this.stack.empty()){

            temp_s = this.stack.peek();

            try {
                temp_i = Integer.parseInt(temp_s);
                this.operation_list += temp_s + "\t\tpush operando\n";
                this.aux.push(this.stack.pop());

            } catch (Exception e) {
                
                temp_s = this.stack.pop();

                if(temp_s.equals("+")){

                    b = Integer.parseInt(this.aux.pop());
                    a = Integer.parseInt(this.aux.pop());
                    this.aux.push(Integer.toString(suma(a, b)));
                    operation_list += temp_s + "\t\tSumar: pop, pop y push del resultado\t\t" + this.aux.peek() + "\n";
                    
                } else if (temp_s.equals("-")){

                    b = Integer.parseInt(this.aux.pop());
                    a = Integer.parseInt(this.aux.pop());
                    this.aux.push(Integer.toString(resta(a, b)));
                    operation_list += temp_s + "\t\tRestar: pop, pop y push del resultado\t\t" + this.aux.peek() + "\n";

                } else if (temp_s.equals("*")){

                    b = Integer.parseInt(this.aux.pop());
                    a = Integer.parseInt(this.aux.pop());
                    this.aux.push(Integer.toString(multiplicacion(a, b)));
                    operation_list += temp_s + "\t\tMultiplicar: pop, pop y push del resultado\t\t" + this.aux.peek() + "\n";

                } else if (temp_s.equals("/")){

                    b = Integer.parseInt(this.aux.pop());
                    a = Integer.parseInt(this.aux.pop());
                    this.aux.push(Integer.toString(multiplicacion(a, b)));
                    operation_list += temp_s + "\t\tDividir:pop, pop y push del resultado\t\t" + this.aux.peek() + "\n";
                }
            }
        }

        int result = Integer.parseInt(this.aux.pop());
        this.operation_list += "\nEL RESULTADO FINAL ES: " + result + "\n\n";
        return result;
    }

    /**
    * @param a 
    * @throws FileNotFoundException
    */
    @Override
    public String decode(String a) { 
        
        File file = new File((a + ".txt"));

        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                op.push(scan.nextLine());
            }

        } catch (FileNotFoundException e) {
        }

        String temp_string = "";
        String[] temp_array;
        int result;

        while (!this.op.empty()){
            
            temp_string = this.op.pop();
            temp_array = temp_string.split(" ");

            for (int i = temp_array.length - 1; i > -1; i--){
                this.stack.push(temp_array[i]);
            }

            result = operar(this.stack);
        }

        return operation_list;
    }
}