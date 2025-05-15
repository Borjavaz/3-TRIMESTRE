package Boletin12_1;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int id;
    private String nome;
    private String telefono;

    public Cliente(int id, String nome, String telefono) {
        this.id = id;
        this.nome = nome;
        this.telefono = telefono;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getNome() { return nome; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Teléfono: " + telefono;
    }

}
