package XestorOnline;

import Tienda.Videojuegos;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class XestionProductos {

    private static Map<String, Producto> inventario = new HashMap<>();

    public static void main(String[] args) {
        cargarInventarioBinario();

        Scanner sc = new Scanner(System.in);
        int opcion;

        try {
            do {
                System.out.println("""
                    --- MENÚ ---
                    0. Salir
                    1. Añadir producto
                    2. Eliminar producto
                    3. Modificar stock
                    4. Listar productos ordenados por nombre
                    5. Guardar productos en fichero
                    Elige una opción:
                """);
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 0 -> System.out.println("Saliendo del programa.");
                    case 1 -> añadirProducto(sc);
                    case 2 -> eliminarProductos(sc);
                    case 3 -> modificarStock(sc);
                    case 4 -> listarProductos();
                    case 5 -> guardarInventarioBinario();
                    default -> System.out.println("Opción no válida.");
                }
            } while (opcion != 0);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    private static void añadirProducto(Scanner sc) {
        try {
            System.out.print("Código: ");
            String codigo = sc.nextLine();

            if (inventario.containsKey(codigo)) {
                System.out.println("Ese código ya existe.");
                return;
            }

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Precio: ");
            double precio = Double.parseDouble(sc.nextLine());
            System.out.print("Stock: ");
            int stock = Integer.parseInt(sc.nextLine());

            Producto producto = new Producto(codigo, nombre, precio, stock);
            inventario.put(codigo,producto);
            System.out.println("Producto añadido correctamente.");
        } catch (Exception e) {
            System.out.println("Error al añadir: " + e.getMessage());
        }
    }

    private static void listarProductos() {
        if (inventario.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("\nLista de productos ordenados por nombre:");

        inventario.values().stream()
                .sorted(Comparator.comparing(Producto::getNombre)) //  ordena alfabéticamente por nombre
                .forEach(System.out::println);
    }

    private static void eliminarProductos(Scanner sc) {
        if (inventario.isEmpty()) {
            System.out.println("Inventario vacío.");
            return;
        }

        listarProductos();
        System.out.print("Introduce el código del producto a eliminar: ");
        String codigo = sc.nextLine();

        if (inventario.remove(codigo) != null) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("No se encontró ese código.");
        }
    }

    private static void modificarStock(Scanner sc) {
        listarProductos();

        System.out.print("Código del producto: ");
        String codigo = sc.nextLine();

        if (inventario.containsKey(codigo)) {
            System.out.print("Nuevo stock: ");
            int nuevoStock = Integer.parseInt(sc.nextLine());
            inventario.get(codigo).setStock(nuevoStock);
            System.out.println("Stock actualizado.");
        } else {
            System.out.println("No existe ese producto.");
        }
    }

    private static void cargarInventarioBinario() {
        File fichero = new File("inventario.dat");

        if (!fichero.exists()) {
            System.out.println("No hay inventario previo.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            inventario = (Map<String, Producto>) ois.readObject();
            System.out.println("Inventario cargado desde fichero binario.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        }
    }


    private static void guardarInventarioBinario() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("inventario.dat"))) {
            oos.writeObject(inventario); // Guarda todo el HashMap
            System.out.println("Inventario guardado correctamente en formato binario.");
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }
}
