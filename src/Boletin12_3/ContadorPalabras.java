package Boletin12_3;

import java.io.*;
import java.text.Normalizer;
import java.util.*;

public class ContadorPalabras {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce o nome do ficheiro .txt: ");
            String nomeFicheiro = sc.nextLine();

            Map<String, Integer> contador = new TreeMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(nomeFicheiro))) {
                String liña;
                while ((liña = br.readLine()) != null) {
                    // Elimina tildes e normaliza a liña
                    liña = Normalizer.normalize(liña, Normalizer.Form.NFD)
                            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

                    // Elimina signos de puntuación e pasa a minúsculas
                    liña = liña.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}\\s]", "").toLowerCase();

                    // Divide en palabras
                    String[] palabras = liña.split("\\s+");

                    for (String palabra : palabras) {
                        if (!palabra.isEmpty()) {
                            contador.put(palabra, contador.getOrDefault(palabra, 0) + 1);
                        }
                    }
                }

                // Mostrar resultados por consola
                System.out.println("Frecuencia das palabras:");
                for (Map.Entry<String, Integer> entrada : contador.entrySet()) {
                    System.out.println(entrada.getKey() + ": " + entrada.getValue());
                }

                // Gardar no ficheiro de saída
                try (PrintWriter pw = new PrintWriter("resumo_palabras.txt")) {
                    for (Map.Entry<String, Integer> entrada : contador.entrySet()) {
                        pw.println(entrada.getKey() + ": " + entrada.getValue());
                    }
                }

                System.out.println("Resumo gardado en resumo_palabras.txt");

            } catch (FileNotFoundException e) {
                System.out.println(" Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println(" Error: " + e.getMessage());
            }
        }
    }

