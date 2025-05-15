package Boletin12_1;

import java.io.*;
import java.util.*;

public class XestionClientes {
    private static final String FICHEIRO = "clientes.txt";
    private static List<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        cargarClientes();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("""
                --- MENÚ ---
                1. Engadir novo cliente
                2. Modificar cliente
                3. Dar de baixa cliente
                4. Listar clientes
                5. Saír
                Elixe unha opción:
            """);
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> engadirCliente(sc);
                case 2 -> modificarCliente(sc);
                case 3 -> eliminarCliente(sc);
                case 4 -> listarClientes();
                case 5 -> gardarClientes();
                default -> System.out.println("Opción non válida.");
            }

        } while (opcion != 5);
    }

    private static void engadirCliente(Scanner sc) {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        clientes.add(new Cliente(id, nome, telefono));
        System.out.println("Cliente engadido.");
    }

    private static void modificarCliente(Scanner sc) {
        System.out.print("ID do cliente a modificar: ");
        int id = Integer.parseInt(sc.nextLine());

        for (Cliente c : clientes) {
            if (c.getId() == id) {
                System.out.print("Novo nome: ");
                c.setNome(sc.nextLine());
                System.out.print("Novo teléfono: ");
                c.setTelefono(sc.nextLine());
                System.out.println("Cliente modificado.");
                return;
            }
        }
        System.out.println("Cliente non atopado.");
    }

    private static void eliminarCliente(Scanner sc) {
        System.out.print("ID do cliente a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean eliminado = clientes.removeIf(c -> c.getId() == id);
        if (eliminado)
            System.out.println("Cliente eliminado.");
        else
            System.out.println("Cliente non atopado.");
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Non hai clientes gardados.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    private static void gardarClientes() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHEIRO))) {
            for (Cliente c : clientes) {
                pw.println(c.getId() + ";" + c.getNome() + ";" + c.getTelefono());
            }
            System.out.println("Datos gardados como texto.");
        } catch (IOException e) {
            System.out.println("Erro ao gardar os datos.");
            e.printStackTrace();
        }
    }

    private static void cargarClientes() {
        File f = new File(FICHEIRO);
        if (!f.exists()) return;

        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String liña = sc.nextLine();
                String[] partes = liña.split(";");
                if (partes.length == 3) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    String telefono = partes[2];
                    clientes.add(new Cliente(id, nome, telefono));
                }
            }
            System.out.println("Datos cargados desde ficheiro de texto.");
        } catch (IOException e) {
            System.out.println("Erro ao cargar os datos.");
            e.printStackTrace();
        }
    }
}
