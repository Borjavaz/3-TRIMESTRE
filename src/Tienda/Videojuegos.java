package Tienda;

public class Videojuegos {

    private String codigo;
    private String cantidad;
    private double precio;
    private int stock;


    public Videojuegos(String codigo, int cantidade) {
        this.codigo = codigo;
        this.cantidade = cantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCantidade() {
        return cantidade;
    }

    public void setCantidade(int cantidade) {
        this.cantidade = cantidade;
    }

    @Override
    public String toString() {
        return codigo + " - Stock: " + cantidade;
    }
}
