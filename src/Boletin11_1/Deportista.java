package Boletin11_1;

public class Deportista {
    private String deporte;
    private String club;
    private String licenza;

    // Getter e Setter para o deporte
    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    // Getter e Setter para o club
    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    // Getter e Setter para a licenza
    public String getLicenza() {
        return licenza;
    }

    public void setLicenza(String licenza) throws IllegalArgumentException {
        // Validación do formato da licenza (aaaa, ddd, nnnnnn)
        if (!licenza.matches("\\d{4}[a-zA-Z]{3}\\d{6}")) {
            throw new IllegalArgumentException("Formato de licenza incorrecto. O formato debe ser aaaadddnnnnnn.");
        }
        this.licenza = licenza;
    }
}
