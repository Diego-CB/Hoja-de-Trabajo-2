/*******************************************************
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos
* Profesor: Moises Gonzales
* Calc_ITest.java
* Autor: Diego Cordova
* Ultima modificacion: 2021-02-3
*
* Clase CalcI_Test
* Uitariy test for Calc_I class
********************************************************/

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Random;
    
public class Calc_ITest {

    private static calculadora calc = new Calc_I(); // Instance of Calc_I 
    private static Random rand = new Random();
    
    @Test
    public void suma_ceroWithInts() {
        assertEquals(0, calc.suma(0,0));
    }

    @Test
    public void resta_negative() {
        assertEquals(-1, calc.resta(5,6));
    }

    @Test
    public void multiplicacion_forOne() {
        int temp = rand.nextInt(100);
        assertEquals(temp, calc.multiplicacion(temp,1));
    }

    @Test
    public void division_inpropia() {
        int temp = 4/8;
        assertEquals(temp, calc.division(4,8));
    }

    
    @Test
    public void operar_withEmptyStack() {
        Stack<String> stack = new Stack_I();
        try {
            assertEquals(null, calc.operar(stack));
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Test
    public void decode_withNoFile() {
        String a = "archivo";
        assertEquals("\nEL archivo " + a + ".txt no se encontro en la carpeta, vuelva a intentar", calc.decode(a));
    }
}
    