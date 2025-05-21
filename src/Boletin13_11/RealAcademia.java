package Boletin13_11;


import java.util.*;

public class RealAcademia {
    public static void main(String[] args) {

        TreeMap<Character, Academico> academia = new TreeMap<>();

        // Crear 5 académicos
        Academico a1 = new Academico("Xosé", 2005);
        Academico a2 = new Academico("Ana", 1998);
        Academico a3 = new Academico("Luis", 2010);
        Academico a4 = new Academico("Bea", 2015);
        Academico a5 = new Academico("Carlos", 2000);

        // Inserir no mapa
        UtilidadeAcademia.nuevoAcademico(academia, a1, 'X');
        UtilidadeAcademia.nuevoAcademico(academia, a2, 'A');
        UtilidadeAcademia.nuevoAcademico(academia, a3, 'L');
        UtilidadeAcademia.nuevoAcademico(academia, a4, 'B');
        UtilidadeAcademia.nuevoAcademico(academia, a5, 'C');

        // Listado sen letra: por nome
        System.out.println("\n🔤 Listado por nome:");
        List<Academico> lista = new ArrayList<>(academia.values());
        Collections.sort(lista);
        for (Academico a : lista) {
            System.out.println(a);
        }

        // Listado sen letra: por ano de ingreso
        System.out.println("\n📅 Listado por ano de ingreso:");
        lista.sort(Comparator.comparingInt(Academico::getAnoIngreso));
        for (Academico a : lista) {
            System.out.println(a);
        }

        // Listado con letra: por orde de clave
        System.out.println("\n🔡 Listado con letra e datos:");
        for (Map.Entry<Character, Academico> entrada : academia.entrySet()) {
            System.out.println("Letra: " + entrada.getKey() + " → " + entrada.getValue());
        }
    }
}
