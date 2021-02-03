/*******************************************************
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos
* Profesor: Moises Gonzales
* Stack.java
* Autor: Diego Cordova
* Ultima modificacion: 2021-02-2
*
* Interfaz Calculadora
* Interface de la calculadora
********************************************************/

import java.util.ArrayList;

public class Stack_I implements Stack<String> {

    private ArrayList<String> stack; // Arraylist used as stack

    /**
    * Constructor for StackImplementation class 
    */
    public Stack_I (){
        this.stack = new ArrayList<String>();
    }

    /**
    * Adds the item to the artibute stack
    * @param item 
    */
    @Override
    public void push(String item) {
        this.stack.add(item);
    }

    /**
    * Returns the las item added to the stack atribute 
    */
    @Override
    public String pop() {
        if (empty()){
            return null;
            
        } else {
            return this.stack.remove(this.stack.size() - 1);
        }
    }

    /**
    * Returns last item added to the stack 
    */
    @Override
    public String peek() {
        if (empty()){
            return null;
            
        } else {
            return this.stack.get(this.stack.size() - 1);
        }
    }

    /**
    * Returns true if stack is empty and false if not 
    */
    @Override
    public boolean empty() {
        return (stack.size() == 0);
    }

    /**
    * Returns the number of elements in the stack
    */
    @Override
    public int size() {
        return stack.size();
    }
}