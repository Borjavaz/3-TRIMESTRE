package Boletin13_7;

import java.util.HashSet;
import java.util.Set;

public class OperacionsConxuntos {

    // Método xenérico para facer a unión de dous conxuntos
    public static <E> Set<E> union(Set<E> conxunto1, Set<E> conxunto2) {
        Set<E> resultado = new HashSet<>(conxunto1); // Copiamos o primeiro
        resultado.addAll(conxunto2); // Engadimos os elementos do segundo
        return resultado;
    }

    // Exemplo de uso
    public static void main(String[] args) {
        Set<Integer> a = new HashSet<>();
        Set<Integer> b = new HashSet<>();

        a.add(1);
        a.add(2);
        a.add(3);

        b.add(3);
        b.add(4);
        b.add(5);

        Set<Integer> unionTotal = union(a, b);
        System.out.println("Unión dos conxuntos: " + unionTotal);
    }
}
