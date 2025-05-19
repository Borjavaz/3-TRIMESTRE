package Boletin12_4;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class XestorTarefas {
    private static final String FICHEIRO = "tarefas.txt";
    private static ArrayList<Tarefa> tarefas = new ArrayList<>();

    public static void main(String[] args) {
        cargarTarefas();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Engadir tarefa");
            System.out.println("2. Borrar tarefa");
            System.out.println("3. Modificar tarefa");
            System.out.println("4. Listar tarefas");
            System.out.println("0. Saír");
            System.out.print("Elixe unha opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // consumir nova liña

            switch (opcion) {
                case 1 -> engadirTarefa(sc);
                case 2 -> borrarTarefa(sc);
                case 3 -> modificarTarefa(sc);
                case 4 -> listarTarefas();
                case 0 -> System.out.println("Saíndo...");
                default -> System.out.println("Opción incorrecta.");
            }

        } while (opcion != 0);

        gardarTarefas();
        sc.close();
    }

    private static void engadirTarefa(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrición: ");
        String descricion = sc.nextLine();
        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(sc.nextLine());
        System.out.print("Hora (HH:MM): ");
        LocalTime hora = LocalTime.parse(sc.nextLine());
        System.out.print("Duración (min): ");
        int duracion = sc.nextInt();
        sc.nextLine();
        System.out.print("Feita? (s/n): ");
        boolean feita = sc.nextLine().equalsIgnoreCase("s");

        tarefas.add(new Tarefa(nome, descricion, data, hora, duracion, feita));
        System.out.println("Tarefa engadida.");
    }

    private static void borrarTarefa(Scanner sc) {
        listarTarefas();
        System.out.print("Número da tarefa a borrar: ");
        int indice = sc.nextInt() - 1;
        sc.nextLine();
        if (indice >= 0 && indice < tarefas.size()) {
            tarefas.remove(indice);
            System.out.println("Tarefa borrada.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private static void modificarTarefa(Scanner sc) {
        listarTarefas();
        System.out.print("Número da tarefa a modificar: ");
        int indice = sc.nextInt() - 1;
        sc.nextLine();

        if (indice >= 0 && indice < tarefas.size()) {
            Tarefa t = tarefas.get(indice);
            System.out.print("Novo nome (" + t.getNome() + "): ");
            String nome = sc.nextLine();
            if (!nome.isEmpty()) t.setNome(nome);

            System.out.print("Nova descrición (" + t.getDescricion() + "): ");
            String descricion = sc.nextLine();
            if (!descricion.isEmpty()) t.setDescricion(descricion);

            System.out.print("Nova data (" + t.getData() + "): ");
            String dataStr = sc.nextLine();
            if (!dataStr.isEmpty()) t.setData(LocalDate.parse(dataStr));

            System.out.print("Nova hora (" + t.getHora() + "): ");
            String horaStr = sc.nextLine();
            if (!horaStr.isEmpty()) t.setHora(LocalTime.parse(horaStr));

            System.out.print("Nova duración (" + t.getDuracion() + "): ");
            String durStr = sc.nextLine();
            if (!durStr.isEmpty()) t.setDuracion(Integer.parseInt(durStr));

            System.out.print("Feita? (" + (t.isFeita() ? "s" : "n") + "): ");
            String feitaStr = sc.nextLine();
            if (!feitaStr.isEmpty()) t.setFeita(feitaStr.equalsIgnoreCase("s"));

            System.out.println("Tarefa modificada.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private static void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Non hai tarefas.");
        } else {
            for (int i = 0; i < tarefas.size(); i++) {
                System.out.println((i + 1) + ". " + tarefas.get(i));
            }
        }
    }

    private static void gardarTarefas() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FICHEIRO))) {
            out.writeObject(tarefas);
        } catch (IOException e) {
            System.out.println("Erro ao gardar as tarefas: " + e.getMessage());
        }
    }

    private static void cargarTarefas() {
        File f = new File(FICHEIRO);
        if (f.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
                tarefas = (ArrayList<Tarefa>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao cargar as tarefas: " + e.getMessage());
            }
        }
    }
}
