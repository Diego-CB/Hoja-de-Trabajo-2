/*******************************************************
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos
* Profesor: Moises Gonzales
* CalcImplementation.java
* Autor: Diego Cordova
* Ultima modificacion: 2021-02-3
*
* Interfaz Calculadora
* Interface de la calculadora
********************************************************/

import java.io.*;
import java.util.Scanner;

public class Calc_I implements calculadora{

    private Stack<String> stack; // contains the numbers and operators to be operated
    private Stack<String> aux; // used as auxiliar stack during operating
    private Stack<String> op; // contains the ondecoded operation 
    private String operation_list; // contains the textual representation of the done operations and their steps

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
    * @throws IllegalArgumentException
    */
    @Override
    public int operar(Stack x) {
        
        this.operation_list += "\nENTRADA\t\tOPERACION\t\t\t\t\tRESULTADOS\n";
        String temp_s = ""; // contains the actual peek from the stack
        int temp_i,a,b; // a,b are the numbers for teh operations and temp_i is the variable udes for validate if the stack peek is a number or not

        // while stack is not empty
        while(!this.stack.empty()){

            temp_s = this.stack.peek();

            /**
             * if the stack peek is a number
             * then it's pushed to the auxiliar stack
             * and finally the constance of the operation is aded to operation_list
             */
            try {
                temp_i = Integer.parseInt(temp_s);
                this.operation_list += temp_s + "\t\tpush operando\n";
                this.aux.push(this.stack.pop());

            /**
             * if the stack peek is not a number
             * then it's evaluated to the four different operators
             * then operated
             */
            } catch (Exception e) {
                
                temp_s = this.stack.pop();

                /**
                 * for every operations
                 * the numbers are pushed out of the auxiliar stack
                 * used as parameters on their pertinent operations (view methods declared before on this class)
                 * the result of the operation is pushed to the auxiliar stack
                 * finally the constance of the operation is added to operation_list
                 */
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
                    this.aux.push(Integer.toString(division(a, b)));
                    operation_list += temp_s + "\t\tDividir:pop, pop y push del resultado\t\t" + this.aux.peek() + "\n";
                }
            }
        }

        /**
         * if the auxiliar stack is not empty 
         * (it means that the param stack x was't null and the operation it contained was done)
         * then it returns the result of the operation
         */
        if (!this.aux.empty()){
            return Integer.parseInt(this.aux.pop());

        /**
         * if the auxiliar stack is empty 
         * (it means that the param stack x was null)
         * then trows IllegalArgumentationException
         */
        } else{
            throw new IllegalArgumentException("\nERROR:No hay stack de datos que operar\n");
        }
    }

    /**
     * It returns a string with the list of operations done to execute the instructions on the file readed and it's result
     * @param a name of the .txt file (in the same folder as this class) for readding instrucctions  
     */
    @Override
    public String decode(String a) { 
        
        File file = new File((a + ".txt")); // Creates a file object with the path given as parameter

        /**
         * tries to read the file given as parameter
         * then it pushes it's rows (operations) to the op stack
         */
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                op.push(scan.nextLine());
            }

        /**
         * if the file is not founded
         * then it handles FIleNotFoundException and throws alert into operation_List (it will be returned later on the method)
         */
        } catch (FileNotFoundException e) { 
            this.operation_list = "\nEL archivo " + a + ".txt no se encontro en la carpeta, vuelva a intentar";
        }

        String temp_string = ""; //it temporarelly contains a row from the file
        String[] temp_array; // It temporarelly contains every character in a row of the file
        int result; // it contains the result of the operation

        /**
         * while the op stack is not empty(the file has been readen and it contains at least a row with operations)
         * then a row of the file in string format is contained un tem_string
         * then is is splitted and the resulting arrray is content in tem_array
         * then every character on temp_array is pushed in order to stack Stack
         * then tie stack is operaed (view operar(Stack x) method)
         * finally the result of operation is added to result and the constance of the result is added to operation_list
         */
        while (!this.op.empty()){
            
            temp_string = this.op.pop();
            temp_array = temp_string.split(" ");

            for (int i = temp_array.length - 1; i > -1; i--){
                this.stack.push(temp_array[i]);
            }

            result = operar(this.stack);
            this.operation_list += "\nEL RESULTADO FINAL ES: " + result + "\n\n";
        }

        return operation_list; // the textual representation of the procees is returned
    }
}