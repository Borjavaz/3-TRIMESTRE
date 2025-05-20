package Boletin13_11;

import java.util.Date;

public class Academico {

    private Character letra;
    private Date anoNacemento;

    public Academico(Character letra, Date anoNacemento) {
        this.letra = letra;
        this.anoNacemento = anoNacemento;
    }

    public String getLetra() {
        return letra.toString();
    }

    public int getAnoNacemento() {
        return anoNacemento.getYear();
    }

    @Override
    public String toString() {
        return letra + " - Ano de nacemento: " + anoNacemento.getYear();
    }
}
