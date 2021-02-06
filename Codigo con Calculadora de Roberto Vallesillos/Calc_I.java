//Autor: Roberto Vallecillos.
//Modificación: 04-02-2021
//Clase: Calculadora

import java.util.*;
import java.io.*;

public class Calc_I implements calculadora 
{

  //Varaibles para comparar cada símbolo
  private static final String MAS = "+";
  private static final String MENOS = "-";
  private static final String POR = "*";
  private static final String SOBRE = "/";

  //Variables para la pila principal, la pila auxiliar y el string de operacion
  private stack<String> pila = new Stack_I();
  private stack<String> aux = new Stack_I();
  private String operacion;


  /**
  *Constructor de la clase calculadora.
  */
  public Calc_I(){
    pila = new Stack_I();
    aux = new Stack_I();
  }
  /**
  * @override
  */
  public int suma(int x, int y){
    int adicion;
    adicion = x + y;
    return adicion;
  }
  /**
  * @override
  */
  public int resta(int x, int y){
    int substracion;
    substracion = x - y;
    return substracion;
  }
  /**
  * @override
  */
  public int multiplicacion(int x, int y){
    int producto;
    producto = x * y;
    return producto;
  }
  /**
  * @override
  *En caso de una division sobre 0k tira un error y menciona que no se pudo hacer la operacion
  */
  public int division(int x, int y){
    int cociente;
    try{
      cociente = x/y;
    }catch (Exception e){
      System.out.println("No se puede realizar la operacion");
      throw new IllegalArgumentException("Division sobre 0");
    }
    return cociente;
  }

  public int operar(stack x){
    for(int y = this.aux.size(); y > 0; y--){
      String actual = this.aux.pop();
      operacion += actual;
      //Añade el elemtno más reciente del auxiliar, y lo añade al string para la operacion
      if(MAS.equals(actual)){
        int operando2 = Integer.parseInt(pila.pop());
        int operando1 = Integer.parseInt(pila.pop());
        int respuesta = suma(operando1, operando2);
        pila.push(String.valueOf(respuesta));
        operacion += "         Sumar: pop, pop y push del resultado \n";
        //Cuando se suma, se agarra los dos operandos, se hace la operacion y añade a la pila ese valor. Pasa de String a Integer a String.
      }else if(MENOS.equals(actual)){
        int operando2 = Integer.parseInt(pila.pop());
        int operando1 = Integer.parseInt(pila.pop());
        int respuesta = resta(operando1, operando2);
        pila.push(String.valueOf(respuesta));
        operacion += "         Restar: pop, pop y push del resultado \n";
        //Cuando se resta, se agarra los dos operandos, se hace la operacion y añade a la pila ese valor. Pasa de String a Integer a String.
      }else if (POR.equals(actual)){
        int operando2 = Integer.parseInt(pila.pop());
        int operando1 = Integer.parseInt(pila.pop());
        int respuesta = multiplicacion(operando1, operando2);
        pila.push(String.valueOf(respuesta));
        operacion += "         Multiplicar: pop, pop y push del resultado \n";
        //Cuando se multiplica, se agarra los dos operandos, se hace la operacion y añade a la pila ese valor. Pasa de String a Integer a String.
      }else if (SOBRE.equals(actual)){
        int operando2 = Integer.parseInt(pila.pop());
        int operando1 = Integer.parseInt(pila.pop());
        int respuesta = division(operando1, operando2);
        pila.push(String.valueOf(respuesta));
        operacion += "         Dividir: pop, pop y push del resultado \n";
        //Cuando se divide, se agarra los dos operandos, se hace la operacion y añade a la pila ese valor. Pasa de String a Integer a String.
      }else{
        try{
          Integer.parseInt(actual);
        }catch(Exception e){
           System.out.println("No se puede realizar la operacion");
          throw new IllegalArgumentException("Simbolo no operable");
        }
        operacion += "         push operando \n";
        pila.push(actual);
      }
      //En el caso que no una de las variables establecidas al principi, revisa que simbolo es. Si es un número, lo añade a la pila. Si no, se termina al no poder hacer dicha operacion. En cualquier caso, siempre se añade que operacion esta haciendo, el pop or push,
    }
    String result = pila.pop();
    if(!pila.empty()){
       throw new IllegalArgumentException("No hay más operando que realizar");
    }
    return Integer.parseInt(result);
    //Se saca el valor mas reciente que seria la respuesta final. Si esta vacio despupes de eso, devuelve la repuesta. Si no, tira un error que no pudo realizarse la operacion.
  }
    
  public String decode(String a) {
    operacion = "Entrada   Operacion\n";
    BufferedReader br = null;
    String line = "";
    try{
      br = new BufferedReader(new FileReader(a));
      
      line = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    finally {
        try {
				  br.close();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
      }
    //Métodos para abrir el archivo de texto. Lo lee y añade todos los símbolos a un array.
    String[] orden = line.split(" ");
    stack<String> reverso = new Stack_I<String>();
    for(int x = 0; x < orden.length; x++){
      reverso.push(orden[x]);
    }
    for(int x = reverso.size(); x > 0; x--){
      String actual = reverso.pop();
      this.aux.push(actual);
    }
    //Se pone el array para añadirlos al stack de reverso, para después cambiarlo al stack principal. 
    int resultado = operar(reverso);
    operacion += "Resultado: ";
    operacion += resultado;
    return operacion;
    //Devuelve todas las operaciones hechas, y al final el resultado obtenido, si se pudo hacer la operacion.
  }

}