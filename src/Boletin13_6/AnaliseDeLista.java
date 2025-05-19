package Boletin13_6;

import java.util.*;

public class AnaliseDeLista {
    public static void main(String[] args) {
        // Exemplo de lista con números entre 1 e 10
        List<Integer> lista = Arrays.asList(1, 3, 5, 3, 7, 2, 5, 9, 1, 10, 4, 4, 6);

        // Conxunto sen repetir (usando HashSet)
        Set<Integer> senRepetir = new HashSet<>(lista);

        // Conxunto de elementos repetidos
        Set<Integer> repetidos = new HashSet<>();
        Set<Integer> vistos = new HashSet<>();

        for (int num : lista) {
            if (!vistos.add(num)) {
                repetidos.add(num); // xa foi visto → é repetido
            }
        }

        // Conxunto de únicos (elementos que aparecen só unha vez)
        Set<Integer> unicos = new HashSet<>(lista);
        unicos.removeAll(repetidos); // elimina os que aparecen máis dunha vez

        // Mostrar resultados
        System.out.println("Lista orixinal: " + lista);
        System.out.println("Elementos sen repetir: " + senRepetir);
        System.out.println("Elementos repetidos: " + repetidos);
        System.out.println("Elementos únicos (só aparecen unha vez): " + unicos);
    }
}
