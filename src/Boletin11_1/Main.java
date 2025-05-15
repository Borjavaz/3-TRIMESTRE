package Boletin11_1;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear un obxecto Persoa
            Persoa persoa = new Persoa();

            // Establecer valores para nome, dirección e DNI
            persoa.setNome("Juan Pérez");
            persoa.setDireccion("Calle Ficticia, 123");
            persoa.setDni("12345678A");  // DNI válido

            // Mostrar os valores establecidos
            System.out.println("Nome: " + persoa.getNome());
            System.out.println("Dirección: " + persoa.getDireccion());
            System.out.println("DNI: " + persoa.getDni());

        } catch (DniNonValido e) {
            System.out.println("Error no DNI: " + e.getMessage());
        }

            try {
                // Crear un obxecto Deportista
                Deportista deportista = new Deportista();

                // Establecer valores para deporte, club e licenza
                deportista.setDeporte("Futbol");
                deportista.setClub("FC Barcelona");
                deportista.setLicenza("2023fut123456");  // Licenza válida

                // Mostrar os valores establecidos
                System.out.println("Deporte: " + deportista.getDeporte());
                System.out.println("Club: " + deportista.getClub());
                System.out.println("Licenza: " + deportista.getLicenza());

            } catch (IllegalArgumentException e) {
                System.out.println("Error na licenza: " + e.getMessage());
            }
        }
    }

