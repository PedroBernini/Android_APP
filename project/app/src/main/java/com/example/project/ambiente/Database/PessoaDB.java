package com.example.project.ambiente.Database;

public class PessoaDB {

    public String nome;
    public String email;
    public String notaD;
    public String notaI;
    public String notaS;
    public String notaC;

    public PessoaDB() {}

    public PessoaDB(String nome, String notaD, String notaI, String notaS, String notaC) {
        this.nome = nome;
        this.email = "";
        this.notaD = notaD;
        this.notaI = notaI;
        this.notaS = notaS;
        this.notaC = notaC;
    }

    public PessoaDB(String nome, String email, String notaD, String notaI, String notaS, String notaC) {
        this.nome = nome;
        this.email = email;
        this.notaD = notaD;
        this.notaI = notaI;
        this.notaS = notaS;
        this.notaC = notaC;
    }

    public String getNome() {
        return nome;
    }
}
