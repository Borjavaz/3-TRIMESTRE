package Boletin13_2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class EliminarNumeros {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();
        Random rand = new Random();

        // Inserir 100 números aleatorios entre 1 e 10
        for (int i = 0; i < 100; i++) {
            numeros.add(rand.nextInt(10) + 1); // rand.nextInt(10) devolve 0-9, +1 → 1-10
        }

        // Mostrar antes da eliminación
        System.out.println("Antes da eliminación:");
        System.out.println(numeros);

        // Eliminar os números 5 e 7
        Iterator<Integer> it = numeros.iterator();
        while (it.hasNext()) {
            int n = it.next();
            if (n == 5 || n == 7) {
                it.remove();
            }
        }

        // Mostrar despois da eliminación
        System.out.println("\nDespois da eliminación (sen 5 nin 7):");
        System.out.println(numeros);
    }
}
