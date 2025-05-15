package Boletin11_2;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) throws FormatoDataErroneo {
        setAno(ano);
        setMes(mes);
        setDia(dia);
    }

    // Getters
    public int getDia() { return dia; }
    public int getMes() { return mes; }
    public int getAno() { return ano; }

    // Setters con validación
    public void setDia(int dia) throws FormatoDataErroneo {
        int maxDias = obterDiasDoMes(this.mes, this.ano);
        if (dia < 1 || dia > maxDias) {
            throw new FormatoDataErroneo("Día incorrecto para o mes/ano indicado.");
        }
        this.dia = dia;
    }

    public void setMes(int mes) throws FormatoDataErroneo {
        if (mes < 1 || mes > 12) {
            throw new FormatoDataErroneo("Mes debe estar entre 1 e 12.");
        }
        this.mes = mes;
    }

    public void setAno(int ano) throws FormatoDataErroneo {
        if (ano < 1970 || ano > 2999) {
            throw new FormatoDataErroneo("Ano debe estar entre 1970 e 2999.");
        }
        this.ano = ano;
    }

    // Comprobar se o ano é bisiesto
    public boolean eBisiesto() {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }

    // Comparar dúas datas
    public boolean dataIgualQue(Data outra) {
        return this.dia == outra.dia && this.mes == outra.mes && this.ano == outra.ano;
    }

    // Mostrar a data como cadea
    public String amosarData() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    // Incrementar o ano
    public void incrementarAno() throws FormatoDataErroneo {
        setAno(this.ano + 1);
        if (dia > obterDiasDoMes(mes, ano)) {
            setDia(obterDiasDoMes(mes, ano));
        }
    }

    // Incrementar o mes
    public void incrementarMes() throws FormatoDataErroneo {
        if (mes == 12) {
            setMes(1);
            incrementarAno();
        } else {
            setMes(mes + 1);
        }
        if (dia > obterDiasDoMes(mes, ano)) {
            setDia(obterDiasDoMes(mes, ano));
        }
    }

    // Incrementar o día
    public void incrementarDia() throws FormatoDataErroneo {
        if (dia < obterDiasDoMes(mes, ano)) {
            setDia(dia + 1);
        } else {
            setDia(1);
            incrementarMes();
        }
    }

    // Número de días do mes
    private int obterDiasDoMes(int mes, int ano) {
        return switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0) ? 29 : 28;
            default -> 0;
        };
    }
}
