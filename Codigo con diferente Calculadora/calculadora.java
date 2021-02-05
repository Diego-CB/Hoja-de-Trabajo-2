/*******************************************************
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos
* Profesor: Moises Gonzales
* Stack.java
* Autor: Clase
* Ultima modificacion: 2021-02-2
*
* Interfaz Calculadora
* Interface de la calculadora
********************************************************/

public interface calculadora 
{
    public int suma(int x, int y);
    public int resta(int x, int y);
    public int multiplicacion(int x, int y);
    public int division(int x, int y);
    public int operar(stack x);//operar recibe un stack en formato postfix, y lo opera para devolver un entero que es igual al valor total de la operación
    public String decode(String a);//funcion que se encarga de leer el archivo y decodificar cada línea para realizar las operaciones de cada una
}