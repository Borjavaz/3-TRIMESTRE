package Repaso;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestorContraseñas {

    private static final String FICHEIRO = "GestorContraseñas.txt";
    private static Map<String, String> lista = new HashMap<>();

    public static void main(String[] args) {

        cargarLista();

        Scanner sc = new Scanner(System.in);
        int opcion;
        try {
            do {
                System.out.println("""
                    --- MENÚ ---
                    0. Salir
                    1. Añadir servicio y contraseña
                    2. Listar servicios
                    3. Eliminar servicio
                    4. Guardar
                    Elixe unha opción:
                """);
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 0 -> System.out.println("Saliendo del programa");
                    case 1 -> añadirServicio(sc);
                    case 2 -> listarServicios();
                    case 3 -> eliminarServicio(sc);
                    case 4 -> guardar();

                    default -> System.out.println("Opción non válida.");
                }

            } while (opcion != 0);
        }catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void añadirServicio(Scanner sc) {
        try {
            System.out.print("Servicio: ");
            String servicio = sc.nextLine();
            System.out.print("Contraseña: ");
            String contraseña = sc.nextLine();

            lista.put(servicio, contraseña);
            System.out.println("Guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarServicios() {
        try {
            if (lista.isEmpty()) {
                System.out.println("Non hai servicio registrado.");
            } else {
                System.out.println("\nLista de servicios:");
                for (Map.Entry<String, String> contraseña : lista.entrySet()) {
                    System.out.println("- " + contraseña.getKey() + ": " + contraseña.getValue());
                }
            }

        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarServicio(Scanner sc) {
        try {

            if (lista.isEmpty()) {
                System.out.println("Non hai tareas.");
                return;
            }

            listarServicios();
            System.out.print("Escribe el nombre del servicio que desea eliminar: ");
            String servicioEliminar = sc.nextLine();

            if (lista.remove(servicioEliminar) != null) {
                System.out.println("Servicio eliminado correctamente.");
            } else {
                System.out.println("Este servicio no existe.");
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void cargarLista() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    lista.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Non se puido ler o ficheiro.");
        }
    }

    private static void guardar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO))) {
            for (Map.Entry<String, String> contraseña : lista.entrySet()) {
                bw.write(contraseña.getKey() + ":" + contraseña.getValue());
                bw.newLine();
            }
            System.out.println("Servicios gardadas correctamente.");
        } catch (IOException e) {
            System.out.println("Erro ao gardar os servicios: " + e.getMessage());
        }
    }
}
