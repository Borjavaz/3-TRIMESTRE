package Boletin13_11;

import java.util.Map;

public class UtilidadeAcademia {
    /**
     * Intenta engadir un novo académico ao mapa.
     * Non se engade se a clave xa existe ou se non é unha letra.
     */
    public static boolean nuevoAcademico(Map<Character, Academico> academia, Academico novo, Character letra) {
        if (!Character.isLetter(letra)) return false;
        if (academia.containsKey(letra)) return false;

        academia.put(letra, novo);
        return true;
    }
}
