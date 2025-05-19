package Boletin13_9;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class EstacionMeteoroloxica {
    private static final List<RexistroTemperatura> rexistros = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n--- Menú Estación Meteorolóxica ---");
            System.out.println("1. Novo rexistro");
            System.out.println("2. Listar rexistros");
            System.out.println("3. Mostrar estatística");
            System.out.println("4. Saír");
            System.out.println("5. Cargar rexistros desde ficheiro");
            System.out.print("Escolle unha opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> engadirRexistro();
                case 2 -> listarRexistros();
                case 3 -> mostrarEstatistica();
                case 4 -> gardarRexistros();
                case 5 -> {
                    System.out.print("Introduce o nome do ficheiro (ex: rexistros20250518.dat): ");
                    sc.nextLine(); // Limpar buffer
                    String nome = sc.nextLine();
                    cargarEListarFicheiro(nome);
                }
                default -> System.out.println("Opción non válida");
            }
        } while (opcion != 4);
    }

    private static void engadirRexistro() {
        System.out.print("Introduce a temperatura (°C): ");
        double temp = sc.nextDouble();
        rexistros.add(new RexistroTemperatura(temp));
        System.out.println("Rexistro engadido.");
    }

    private static void listarRexistros() {
        if (rexistros.isEmpty()) {
            System.out.println("Non hai rexistros.");
        } else {
            System.out.println("\n--- Rexistros ---");
            for (RexistroTemperatura r : rexistros) {
                System.out.println(r);
            }
        }
    }

    private static void mostrarEstatistica() {
        if (rexistros.isEmpty()) {
            System.out.println("Non hai rexistros para calcular estatísticas.");
            return;
        }

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double suma = 0;

        for (RexistroTemperatura r : rexistros) {
            double temp = r.getTemperatura();
            if (temp < min) min = temp;
            if (temp > max) max = temp;
            suma += temp;
        }

        double media = suma / rexistros.size();
        System.out.printf("Mínima: %.2f°C, Máxima: %.2f°C, Promedio: %.2f°C%n", min, max, media);
    }

    private static void gardarRexistros() {
        if (rexistros.isEmpty()) {
            System.out.println("Non hai rexistros que gardar.");
            return;
        }

        String data = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String nomeFicheiro = "rexistros" + data + ".dat";

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFicheiro))) {
            out.writeObject(rexistros);
            System.out.println("Rexistros gardados en: " + nomeFicheiro);
        } catch (IOException e) {
            System.out.println("Erro ao gardar o ficheiro: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    private static void cargarEListarFicheiro(String nomeFicheiro) {
        File file = new File(nomeFicheiro);
        if (!file.exists()) {
            System.out.println("O ficheiro non existe.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            List<RexistroTemperatura> rexistrosLidos = (List<RexistroTemperatura>) in.readObject();
            if (rexistrosLidos.isEmpty()) {
                System.out.println("O ficheiro non contén rexistros.");
            } else {
                System.out.println("--- Rexistros cargados desde " + nomeFicheiro + " ---");
                for (RexistroTemperatura r : rexistrosLidos) {
                    System.out.println(r);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }
}
