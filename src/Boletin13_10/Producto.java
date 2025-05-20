package Boletin13_10;

public class Producto {

        private String codigo;
        private int cantidade;

        public Producto(String codigo, int cantidade) {
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

