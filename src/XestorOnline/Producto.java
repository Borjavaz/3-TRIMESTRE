package XestorOnline;

import java.io.Serializable;

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
        private String nombre;
        private double precio;
        private int stock;


        public Producto(String codigo, String nombre, double precio, int stock) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
        }

        public String getCodigo() {
            return codigo;
        }
        public int getStock() { return stock;}
        public String getNombre() { return nombre;}
        public double getPrecio() { return precio;}

        public void setCodigo(String codigo) { this.codigo = codigo; }
        public void setStock(int stock) { this.stock = stock;}
        public void setNombre(String nombre) { this.nombre = nombre;}
        public void setPrecio(double precio) { this.precio = precio;}


        @Override
        public String toString() {
            return "-Codigo: " + codigo + " - Nombre: " + nombre + " - Precio: " + precio + " - Stock: " + stock;
        }
    }
