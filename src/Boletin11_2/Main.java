package Boletin11_2;

public class Main {
    public static void main(String[] args) {
        try {
            Data hoxe = new Data(10, 5, 2025);
            System.out.println("Data actual: " + hoxe.amosarData());

            hoxe.incrementarDia(); // 29 feb
            System.out.println("Día seguinte: " + hoxe.amosarData());

            hoxe.incrementarDia(); // 1 mar
            System.out.println("Outro día máis: " + hoxe.amosarData());

        } catch (FormatoDataErroneo e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
