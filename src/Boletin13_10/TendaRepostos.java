package Boletin13_10;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TendaRepostos {
    private static final String FICHEIRO = "TendaRepostos.txt";
    private static Map<String, Producto> tenda = new HashMap<>();

    public static void main(String[] args) {

        cargarTienda();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("""
                --- MENÚ ---
                0. Salir y guardar
                1. Añadir producto
                2. Listar productos e cantidade
                3. Actualizar cantidade de productos
                4. Eliminar productos
                Elixe unha opción:
            """);
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 0 ->  { guardar(); System.out.println("Saliendo de la tienda");}
                case 1 -> añadirProducto(sc);
                case 2 -> listarProductos();
                case 3 -> actualizarCant(sc);
                case 4 -> borrarProducto(sc);
                default -> System.out.println("Opción non válida.");
            }

        } while (opcion != 0);
    }

    private static void añadirProducto(Scanner sc) {
try {
    System.out.print("Código: ");
    String cod = sc.nextLine();
    System.out.print("Stock: ");
    int stock = sc.nextInt();
    sc.nextLine();

    tenda.put(cod, new Producto(cod, stock));
    System.out.println("Produto engadido.");
}catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
}
    }


    private static void listarProductos() {
        try {

            if (tenda.isEmpty()) {
                System.out.println("Non hai productos.");
            } else {
                System.out.println("\nLista de productos y stock:");
                for (Map.Entry<String, Producto> stock : tenda.entrySet()) {
                    System.out.println("- " + stock.getValue());
                }
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarCant(Scanner sc) {

        if (tenda.isEmpty()) {
            System.out.println("Non hai productos.");
            return;
        }

        listarProductos();
        System.out.print("Escribe el codigo del producto que quieres actualizar: ");
        String cod = sc.nextLine();

        if (!tenda.containsKey(cod)) {
            System.out.println("O código introducido non existe.");
            return;
        }

        Producto producto = tenda.get(cod);

        System.out.print("Introduce a nova cantidade: ");
        int novaCant;
        try {
            novaCant = Integer.parseInt(sc.nextLine());
            if (novaCant < 0) {
                System.out.println("A cantidade non pode ser negativa.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: tes que introducir un número enteiro.");
            return;
        }

        producto.setCantidade(novaCant);
        System.out.println("Cantidade actualizada correctamente.");
    }
    private static void borrarProducto(Scanner sc) {
        try {

            if (tenda.isEmpty()) {
                System.out.println("Non hai productos que borrar.");
                return;
            }

            listarProductos();
            System.out.print("Escribe el codigo del producto que quieres eliminar: ");
            String cod = sc.nextLine();

            if (tenda.remove(cod) != null) {
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("Ese codigo no existe.");
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void cargarTienda() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))) {
            String liña;
            while ((liña = br.readLine()) != null) {
                String[] partes = liña.split(":");
                if (partes.length == 2) {
                    try {
                        String codigo = partes[0];
                        int cantidade = Integer.parseInt(partes[1]);
                        tenda.put(codigo, new Producto(codigo, cantidade));
                    } catch (NumberFormatException e) {
                        System.out.println("Liña inválida no ficheiro: " + liña);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("O ficheiro non existe. Crearase un novo ao gardar.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro.");
        }
    }

    private static void guardar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO))) {
            for (Producto p : tenda.values()) {
                bw.write(p.getCodigo() + ":" + p.getCantidade());
                bw.newLine();
            }
            System.out.println("Datos gardados correctamente.");
        } catch (IOException e) {
            System.out.println("Erro ao gardar.");
        }
    }
}
