package Boletin13_4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SepararEFiltrarNumeros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> positivos = new ArrayList<>();
        ArrayList<Double> negativos = new ArrayList<>();

        double num;

        // Ler números reais ata introducir un 0
        do {
            System.out.print("Introduce un número real (0 para rematar): ");
            num = sc.nextDouble();

            if (num > 0) {
                positivos.add(num);
            } else if (num < 0) {
                negativos.add(num);
            }

        } while (num != 0);

        // Mostrar coleccións e sumar os seus elementos
        System.out.println("\n--- Resultados iniciais ---");
        mostrarECalcular(positivos, "Positivos");
        mostrarECalcular(negativos, "Negativos");

        // Eliminar elementos > 10 en positivos e < -10 en negativos
        eliminarForaRango(positivos, 10.0, true);   // maiores de 10
        eliminarForaRango(negativos, -10.0, false); // menores de -10

        // Mostrar coleccións filtradas
        System.out.println("\n--- Despois de eliminar >10 e <-10 ---");
        mostrarECalcular(positivos, "Positivos");
        mostrarECalcular(negativos, "Negativos");

        sc.close();
    }

    // Método para mostrar os elementos e calcular a suma
    public static void mostrarECalcular(ArrayList<Double> lista, String nome) {
        System.out.println(nome + ": " + lista);
        double suma = 0;
        for (double d : lista) {
            suma += d;
        }
        System.out.println("Suma de " + nome.toLowerCase() + ": " + suma + "\n");
    }

    // Eliminar con iterador os elementos fora de rango
    public static void eliminarForaRango(ArrayList<Double> lista, double limite, boolean maiorQue) {
        Iterator<Double> it = lista.iterator();
        while (it.hasNext()) {
            double valor = it.next();
            if ((maiorQue && valor > limite) || (!maiorQue && valor < limite)) {
                it.remove();
            }
        }
    }
}
