package Boletin12_4;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Tarefa implements Serializable {
    private String nome;
    private String descricion;
    private LocalDate data;
    private LocalTime hora;
    private int duracion; // en minutos
    private boolean feita;

    public Tarefa(String nome, String descricion, LocalDate data, LocalTime hora, int duracion, boolean feita) {
        this.nome = nome;
        this.descricion = descricion;
        this.data = data;
        this.hora = hora;
        this.duracion = duracion;
        this.feita = feita;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricion() { return descricion; }
    public void setDescricion(String descricion) { this.descricion = descricion; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public boolean isFeita() { return feita; }
    public void setFeita(boolean feita) { this.feita = feita; }

    @Override
    public String toString() {
        return "Tarefa: " + nome + "\nDescrición: " + descricion +
                "\nData: " + data + " Hora: " + hora +
                "\nDuración: " + duracion + " min" +
                "\nEstado: " + (feita ? "Feita" : "Non feita") + "\n";
    }
}
