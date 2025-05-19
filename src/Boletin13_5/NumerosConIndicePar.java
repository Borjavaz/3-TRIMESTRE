package Boletin13_5;

import java.util.ArrayList;
import java.util.Scanner;

public class NumerosConIndicePar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> positivos = new ArrayList<>();
        int num;

        // Solicitar números enteiros ata que se introduza -1
        do {
            System.out.print("Introduce un número entero (-1 para rematar): ");
            num = sc.nextInt();

            if (num > 0) {
                positivos.add(num); // Gardamos só os positivos
            }

        } while (num != -1);

        // Mostrar os números dos índices pares multiplicados por 100
        System.out.println("\nNúmeros nos índices pares multiplicados por 100:");
        for (int i = 0; i < positivos.size(); i++) {
            if (i % 2 == 0) {  // Se o índice é par
                System.out.println("Índice " + i + ": " + positivos.get(i) * 100);
            }
        }

        sc.close();
    }
}
