package Boletin11_1;

public class Persoa {
    private String nome;
    private String direccion;
    private String dni;

    // Getter e Setter para o nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para a dirección
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Getter e Setter para o DNI
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws DniNonValido {
        // Validación do formato do DNI (8 números seguidos dunha letra)
        if (!dni.matches("\\d{8}[A-Za-z]")) {
            throw new DniNonValido("DNI non válido. O formato debe ser 8 números seguidos dunha letra.");
        }
        this.dni = dni;
    }
}
