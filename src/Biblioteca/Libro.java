package Biblioteca;

import java.io.Serializable;

public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private String titulo;
    private String autor;
    private int anoPublicacion;
    private boolean prestado;

    public Libro(String codigo, String titulo, String autor, int anoPublicacion, boolean estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.prestado = prestado;
    }

    public String getCodigo() {
        return codigo;
    }
    public String getTitulo() {return titulo;}
    public String getAutor() {return autor;}
    public int getAnoPublicacion() {return anoPublicacion;}
    public boolean isPrestado() {return prestado;}

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setAutor(String autor) {this.autor = autor;}
    public void setAnoPublicacion(int anoPublicacion) { this.anoPublicacion = anoPublicacion; }
    public void setPrestado(boolean prestado) {this.prestado = prestado; }


    public String toString() {
        String estadoTexto = prestado ? "Prestado" : "Disponible";
        return "- Código: " + codigo +
                " | Título: " + titulo +
                " | Autor: " + autor +
                " | Ano publicación: " + anoPublicacion +
                " | Estado: " + estadoTexto;
    }
}
