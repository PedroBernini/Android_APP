package com.example.project.ambiente.Database;


public class EquipeDB {

    public String nome;
    public String notaD;
    public String notaI;
    public String notaS;
    public String notaC;

    public EquipeDB (){}

    public EquipeDB(String nome, String notaD, String notaI, String notaS, String notaC) {
        this.nome = nome;
        this.notaD = notaD;
        this.notaI = notaI;
        this.notaS = notaS;
        this.notaC = notaC;
    }

    public String getNome() {
        return nome;
    }
}
