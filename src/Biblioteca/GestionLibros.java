package Biblioteca;

import XestorOnline.Producto;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestionLibros {

    private static Map<String, Biblioteca.Libro> biblioteca = new HashMap<>();

    public static void main(String[] args) {
        cargarBiblio();

        Scanner sc = new Scanner(System.in);
        int opcion;

        try {
            do {
                System.out.println("""
                    --- MENÚ ---
                    0. Salir
                    1. Rexistrar libro
                    2. Prestar libro
                    3. Devolver libro
                    4. Ver libros disponibles
                    5. Ver libros prestados
                    Elige una opción:
                """);
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 0 -> { guardarBiblio();
                        System.out.println("Saliendo del programa.");
                    }
                    case 1 -> registrarLibro(sc);
                    case 2 -> prestarLibro(sc);
                    case 3 -> devolverLibro(sc);
                    case 4 -> verDisponibles();
                    case 5 -> verPrestados();
                    default -> System.out.println("Opción no válida.");
                }
            } while (opcion != 0);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    private static void registrarLibro(Scanner sc) {
        try {
            System.out.print("Código: ");
            String codigo = sc.nextLine();

            if (biblioteca.containsKey(codigo)) {
                System.out.println("Ese código ya existe.");
                return;
            }

            System.out.print("Titulo: ");
            String titulo = sc.nextLine();
            System.out.print("Autor: ");
            String autor = sc.nextLine();
            System.out.print("Ano publicacion: ");
            int anoPublicacion = Integer.parseInt(sc.nextLine());


            Biblioteca.Libro libro = new Biblioteca.Libro(codigo, titulo, autor, anoPublicacion,false);
            biblioteca.put(codigo,libro);
            System.out.println("Libro añadido correctamente.");
        } catch (Exception e) {
            System.out.println("Error al añadir: " + e.getMessage());
        }
    }

    private static void verDisponibles() {

        System.out.println("\nLista de libros dispoñibles:");
        if (biblioteca.isEmpty()) {
            System.out.println("Non hai libros rexistrados.");
        } else {
            boolean librosDispoñibles = false;
            for (Libro l : biblioteca.values()) {
                if (!l.isPrestado()) {  // Se o libro non está prestado
                    System.out.println(l);  // Imprimir libro dispoñible
                    librosDispoñibles = true;
                }
            }
            if (!librosDispoñibles) {
                System.out.println("Non hai libros dispoñibles.");
            }
        }
    }

        private static void verPrestados() {

            System.out.println("\nLista de libros prestados:");
            if (biblioteca.isEmpty()) {
                System.out.println("Non hai libros rexistrados.");
            } else {
                boolean librosDispoñibles = false;
                for (Libro l : biblioteca.values()) {
                    if (l.isPrestado()) {  // Se o libro está prestado
                        System.out.println(l);  // Imprimir libro prestado
                        librosDispoñibles = true;
                    }
                }
                if (!librosDispoñibles) {
                    System.out.println("Non hai libros dispoñibles.");
                }
            }
        }

    private static void prestarLibro(Scanner sc) {
            if (biblioteca.isEmpty()) {
                System.out.println("Non hai libros rexistrados.");
            }
        verDisponibles();
        System.out.print("Introduce el código del libro que quiere llevarse: ");
        String codigo = sc.nextLine();

        // Comprobamos se o libro existe na biblioteca e cambiamos o seu estado
        Libro libro = biblioteca.get(codigo);
        if (libro != null) {
            if (!libro.isPrestado()) {  // Se o libro non está prestado
                libro.setPrestado(true);  // Marcamos o libro como prestado
                System.out.println("O libro '" + libro.getTitulo() + "' foi prestado correctamente.");
            } else {
                System.out.println("O libro xa está prestado.");
            }
        } else {
            System.out.println("Non se atopou ningún libro con ese código.");
        }
    }

    private static void devolverLibro(Scanner sc) {
        verPrestados();

        System.out.print("Introduce el código del libro que quiere devolver: ");
        String codigo = sc.nextLine();

        // Comprobamos se o libro existe na biblioteca e cambiamos o seu estado
        Libro libro = biblioteca.get(codigo);
        if (libro != null) {
            if (libro.isPrestado()) {  // Se o libro está prestado
                libro.setPrestado(false);  // Marcamos o libro como devolto/dispoñible
                System.out.println("O libro '" + libro.getTitulo() + "' foi devolto correctamente.");
            } else {
                System.out.println("O libro non está prestado.");
            }
        } else {
            System.out.println("Non se atopou ningún libro con ese código.");
        }
    }

    private static void cargarBiblio() {
        File fichero = new File("biblioteca.dat");

        if (!fichero.exists()) {
            System.out.println("No hay inventario previo.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            biblioteca = (Map<String, Libro>) ois.readObject();
            System.out.println("Biblioteca cargada desde fichero binario.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        }
    }


    private static void guardarBiblio() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("biblioteca.dat"))) {
            oos.writeObject(biblioteca); // Guarda todo el HashMap
            System.out.println("Biblioteca guardada correctamente en formato binario.");
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }
}
