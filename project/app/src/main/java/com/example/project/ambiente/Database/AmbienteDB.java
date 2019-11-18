package com.example.project.ambiente.Database;

public class AmbienteDB {

    public String nomeAmbiente;
    public String tipoAmbiente;
    public String notaD;
    public String notaI;
    public String notaS;
    public String notaC;

    public AmbienteDB(String nomeAmbiente, String tipoAmbiente, String notaD, String notaI, String notaS, String notaC) {
        this.nomeAmbiente = nomeAmbiente;
        this.tipoAmbiente = tipoAmbiente;
        this.notaD = notaD;
        this.notaI = notaI;
        this.notaS = notaS;
        this.notaC = notaC;
    }

    public String getNomeAmbiente() {
        return nomeAmbiente;
    }
}
