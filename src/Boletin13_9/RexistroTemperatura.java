package Boletin13_9;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RexistroTemperatura implements Serializable {
    private double temperatura;
    private Date hora;

    public RexistroTemperatura(double temperatura) {
        this.temperatura = temperatura;
        this.hora = new Date(); // Hora do rexistro actual
    }

    public double getTemperatura() {
        return temperatura;
    }

    public Date getHora() {
        return hora;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return "Temperatura: " + temperatura + "°C ás " + sdf.format(hora);
    }
}
