package Repaso;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TareasPendientes {
    private static final String FICHEIRO = "tareasPendientes.txt";
    private static ArrayList<String> lista = new ArrayList<>();

    public static void main(String[] args) {

        cargarLista();

        Scanner sc = new Scanner(System.in);
        int opcion;
        try {
            do {
                System.out.println("""
                    --- MENÚ ---
                    0. Salir
                    1. Añadir nueva tarea
                    2. Listar tareas
                    3. Marcar tarea como finalizada (eliminarla)
                    4. Guardar la lista
                    Elixe unha opción:
                """);
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 0 -> System.out.println("Saliendo del programa");
                    case 1 -> añadirTarea(sc);
                    case 2 -> listarTareas();
                    case 3 -> marcarCompletadas(sc);
                    case 4 -> guardarLista();

                    default -> System.out.println("Opción non válida.");
                }

            } while (opcion != 0);
        }catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void añadirTarea(Scanner sc) {
        System.out.print("Producto a gardar: ");
        String tarea = sc.nextLine();
        lista.add(tarea);
        System.out.println("Tarea guardada.");
    }

    private static void listarTareas() {
        try {

            if (lista.isEmpty()) {
                System.out.println("Non hai tareas.");
            } else {
                System.out.println("\nLista de tareas:");
                for (String tarea : lista) {
                    System.out.println("- " + tarea);
                }
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void marcarCompletadas(Scanner sc) {
        try {

            if (lista.isEmpty()) {
                System.out.println("Non hai tareas.");
                return;
            }

            listarTareas();
            System.out.print("Escribe el nombre de la tarea que ha completado: ");
            String completada = sc.nextLine();

            if (lista.remove(completada)) {
                System.out.println("Tarea completada.");
            } else {
                System.out.println("Esta tarea no existe.");
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void cargarLista() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Non se puido ler o ficheiro.");
        }
    }

    private static void guardarLista() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO))) {
            for (String tarea : lista) {
                bw.write(tarea);
                bw.newLine();
            }
            System.out.println("Tareas gardadas correctamente.");
        } catch (IOException e) {
            System.out.println("Erro ao gardar os productos: " + e.getMessage());
        }
    }
}
