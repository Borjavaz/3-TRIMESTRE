package Tienda;

import java.io.*;
import java.util.*;

public class TiendaVideojuegos {

        private static final String FICHEIRO = "videojuegos.txt";
        private static Map<String, Libro> inventario = new HashMap<>();

        public static void main(String[] args) {
            cargarInventario();

            Scanner sc = new Scanner(System.in);
            int opcion;

            try {
                do {
                    System.out.println("""
                    --- MENÚ ---
                    0. Salir
                    1. Añadir videojuego
                    2. Listar videojuegos
                    3. Eliminar videojuego
                    4. Modificar stock
                    5. Guardar inventario
                    Elige una opción:
                """);
                    opcion = Integer.parseInt(sc.nextLine());

                    switch (opcion) {
                        case 0 -> System.out.println("Saliendo del programa.");
                        case 1 -> añadirVideojuego(sc);
                        case 2 -> listarVideojuegos();
                        case 3 -> eliminarVideojuego(sc);
                        case 4 -> modificarStock(sc);
                        case 5 -> guardarInventario();
                        default -> System.out.println("Opción no válida.");
                    }
                } while (opcion != 0);
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }

            sc.close();
        }

        private static void añadirVideojuego(Scanner sc) {
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

                Libro juego = new Libro(codigo, nombre, precio, stock);
                inventario.put(codigo, juego);
                System.out.println("Videojuego añadido correctamente.");
            } catch (Exception e) {
                System.out.println("Error al añadir: " + e.getMessage());
            }
        }

        private static void listarVideojuegos() {
            if (inventario.isEmpty()) {
                System.out.println("No hay videojuegos registrados.");
            } else {
                System.out.println("\nLista de videojuegos:");
                for (Libro v : inventario.values()) {
                    System.out.println(v);
                }
            }
        }

        private static void eliminarVideojuego(Scanner sc) {
            if (inventario.isEmpty()) {
                System.out.println("Inventario vacío.");
                return;
            }

            listarVideojuegos();
            System.out.print("Introduce el código del videojuego a eliminar: ");
            String codigo = sc.nextLine();

            if (inventario.remove(codigo) != null) {
                System.out.println("Videojuego eliminado.");
            } else {
                System.out.println("No se encontró ese código.");
            }
        }

        private static void modificarStock(Scanner sc) {
            System.out.print("Código del videojuego: ");
            String codigo = sc.nextLine();

            if (inventario.containsKey(codigo)) {
                System.out.print("Nuevo stock: ");
                int nuevoStock = Integer.parseInt(sc.nextLine());
                inventario.get(codigo).setStock(nuevoStock);
                System.out.println("Stock actualizado.");
            } else {
                System.out.println("No existe ese videojuego.");
            }
        }

        private static void cargarInventario() {
            try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    // Formato: codigo:nombre:precio:stock
                    String[] partes = linea.split(":");
                    if (partes.length == 4) {
                        String codigo = partes[0];
                        String nombre = partes[1];
                        double precio = Double.parseDouble(partes[2]);
                        int stock = Integer.parseInt(partes[3]);
                        inventario.put(codigo, new Libro(codigo, nombre, precio, stock));
                    }
                }
            } catch (IOException e) {
                System.out.println("No se pudo cargar el fichero.");
            }
        }

        private static void guardarInventario() {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO))) {
                for (Libro v : inventario.values()) {
                    bw.write(v.getCodigo() + ":" + v.getNombre() + ":" + v.getPrecio() + ":" + v.getStock());
                    bw.newLine();
                }
                System.out.println("Inventario guardado correctamente.");
            } catch (IOException e) {
                System.out.println("Error al guardar el inventario: " + e.getMessage());
            }
        }
    }

