package Boletin12_2;

import java.io.*;
import java.util.*;

public class XestorNotas {
    private static final String FICHEIRO = "notas.txt";
    private static List<String> notas = new ArrayList<>();

    public static void main(String[] args) {
        cargarNotas();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("""
                --- MENÚ ---
                1. Engadir nova nota
                2. Listar todas as notas
                3. Buscar notas por palabra clave
                4. Saír
                Elixe unha opción:
            """);
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> engadirNota(sc);
                case 2 -> listarNotas();
                case 3 -> buscarNotas(sc);
                case 4 -> gardarNotas();
                default -> System.out.println("Opción non válida.");
            }

        } while (opcion != 4);
    }

    private static void engadirNota(Scanner sc) {
        System.out.println("Engade a túa nova nota (texto libre): ");
        String nota = sc.nextLine();
        notas.add(nota);
        System.out.println("Nota engadida correctamente.");
    }

    private static void listarNotas() {
        if (notas.isEmpty()) {
            System.out.println("Non hai notas gardadas.");
        } else {
            System.out.println("\nLista de notas:");
            for (String nota : notas) {
                System.out.println("- " + nota);
            }
        }
    }

    private static void buscarNotas(Scanner sc) {
        System.out.print("Introduce a palabra clave para buscar nas notas: ");
        String palabraClave = sc.nextLine().toLowerCase();

        boolean encontrado = false;
        for (String nota : notas) {
            if (nota.toLowerCase().contains(palabraClave)) {
                System.out.println("- " + nota);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Non se atoparon notas que contiñan a palabra clave.");
        }
    }

    private static void gardarNotas() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHEIRO))) {
            for (String nota : notas) {
                pw.println(nota);
            }
            System.out.println("Notas gardadas correctamente.");
        } catch (IOException e) {
            System.out.println("Erro ao gardar as notas.");
            e.printStackTrace();
        }
    }

    private static void cargarNotas() {
        File f = new File(FICHEIRO);
        if (!f.exists()) return;

        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String nota = sc.nextLine();
                notas.add(nota);
            }
            System.out.println("Notas cargadas desde ficheiro.");
        } catch (IOException e) {
            System.out.println("Erro ao cargar as notas.");
            e.printStackTrace();
        }
    }
}
