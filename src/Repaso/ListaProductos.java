package Repaso;

import java.io.*;
import java.util.*;

public class ListaProductos {
    private static final String FICHEIRO = "listaProductos.txt";
    private static Set<String> lista = new HashSet<>();

    public static void main(String[] args) {

        cargarLista();

        Scanner sc = new Scanner(System.in);
        int opcion;
try {
    do {
        System.out.println("""
                    --- MENÚ ---
                    0. Salir
                    1. Añadir producto
                    2. Listar productos
                    3. Eliminar productos
                    4. Guardar
                    Elixe unha opción:
                """);
        opcion = Integer.parseInt(sc.nextLine());

        switch (opcion) {
            case 0 -> System.out.println("Saliendo del programa");
            case 1 -> añadirProducto(sc);
            case 2 -> listarProductos();
            case 3 -> borrarProductos(sc);
            case 4 -> guardarProductos();
            default -> System.out.println("Opción non válida.");
        }

    } while (opcion != 0);
}catch (NumberFormatException e) {
    System.out.println("Error: " + e.getMessage());
}
    }

    private static void añadirProducto(Scanner sc) {
            System.out.print("Producto a gardar: ");
            String producto = sc.nextLine();
            lista.add(producto);
            System.out.println("Producto guardado.");
    }

    private static void listarProductos() {
        try {

            if (lista.isEmpty()) {
                System.out.println("Non hai productos.");
            } else {
                System.out.println("\nLista de productos:");
                for (String producto : lista) {
                    System.out.println("- " + producto);
                }
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void borrarProductos(Scanner sc) {
        try {

            if (lista.isEmpty()) {
                System.out.println("Non hai productos que borrar.");
                return;
            }

            listarProductos();
            System.out.print("Escribe el nombre del producto que quieres eliminar: ");
            String producto = sc.nextLine();

            if (lista.remove(producto)) {
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("Ese producto no existe.");
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

    private static void guardarProductos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO))) {
            for (String producto : lista) {
                bw.write(producto);
                bw.newLine();
            }
            System.out.println("Productos gardados correctamente.");
        } catch (IOException e) {
            System.out.println("Erro ao gardar os productos: " + e.getMessage());
        }
    }
}
