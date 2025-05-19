package Boletin13_1;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Concatena {

    // Método xenérico estático que concatena dúas táboas
    public static <T> T[] concatenar(T[] array1, T[] array2) {
        @SuppressWarnings("unchecked")
        T[] resultado = (T[]) Array.newInstance(array1.getClass().getComponentType(), array1.length + array2.length);

        System.arraycopy(array1, 0, resultado, 0, array1.length);
        System.arraycopy(array2, 0, resultado, array1.length, array2.length);

        return resultado;
    }

    // Proba do método
    public static void main(String[] args) {
        // Exemplo con String
        String[] nomes1 = {"Ana", "Pedro"};
        String[] nomes2 = {"Laura", "Xosé"};
        String[] nomesConcatenados = concatenar(nomes1, nomes2);
        System.out.println("Nomes: " + Arrays.toString(nomesConcatenados));

        // Exemplo con Integer
        Integer[] nums1 = {1, 2, 3};
        Integer[] nums2 = {4, 5};
        Integer[] numsConcatenados = concatenar(nums1, nums2);
        System.out.println("Números: " + Arrays.toString(numsConcatenados));
    }
}
