import java.util.Scanner;

public class Driver {

    private static void print(Object o){
        System.out.println(o);
    }

    private static Scanner scan = new Scanner(System.in);

    public static void main (String[] args){

        calculadora calc = new Calc_I();
        print("Bienvenido a la caluladora Posfix UVG!!\n\n");

        
        print("Leyendo archivo \"" +  + ".txt\"\n");
        print(calc.decode("xd"));
        print("Gracias por utilizar la calculadora posfix UVG\n\n");
    }
}